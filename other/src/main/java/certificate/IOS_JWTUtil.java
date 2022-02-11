package certificate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.apache.http.client.fluent.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;


public class IOS_JWTUtil {
    /**
     * https://www.apple.com/certificateauthority/ 下载的证书
     * 把 .cer 转换成 .pem 格式
     * openssl x509 -inform der -in AppleRootCA-G3.cer -out AppleRootCA-G3.pem
     */
    private final static String APPLE_RootCA_G3_PEM_CONTEXT = "MIICQzCCAcmgAwIBAgIILcX8iNLFS5UwCgYIKoZIzj0EAwMwZzEbMBkGA1UEAwwSQXBwbGUgUm9vdCBDQSAtIEczMSYwJAYDVQQLDB1BcHBsZSBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eTETMBEGA1UECgwKQXBwbGUgSW5jLjELMAkGA1UEBhMCVVMwHhcNMTQwNDMwMTgxOTA2WhcNMzkwNDMwMTgxOTA2WjBnMRswGQYDVQQDDBJBcHBsZSBSb290IENBIC0gRzMxJjAkBgNVBAsMHUFwcGxlIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MRMwEQYDVQQKDApBcHBsZSBJbmMuMQswCQYDVQQGEwJVUzB2MBAGByqGSM49AgEGBSuBBAAiA2IABJjpLz1AcqTtkyJygRMc3RCV8cWjTnHcFBbZDuWmBSp3ZHtfTjjTuxxEtX/1H7YyYl3J6YRbTzBPEVoA/VhYDKX1DyxNB0cTddqXl5dvMVztK517IDvYuVTZXpmkOlEKMaNCMEAwHQYDVR0OBBYEFLuw3qFYM4iapIqZ3r6966/ayySrMA8GA1UdEwEB/wQFMAMBAf8wDgYDVR0PAQH/BAQDAgEGMAoGCCqGSM49BAMDA2gAMGUCMQCD6cHEFl4aXTQY2e3v9GwOAEZLuN+yRhHFD/3meoyhpmvOwgPUnPWTxnS4at+qIxUCMG1mihDK1A3UT82NQz60imOlM27jbdoXt2QfyFMm+YhidDkLF1vLUagM6BgD56KyKA==";


    public static void main(String[] args) throws Exception {
        String token = iosToken("");
       String data = Request.Get("https://api.storekit-sandbox.itunes.apple.com/inApps/v1/lookup/{orderId}")
               .addHeader("Authorization", "Bearer " + token)
               .connectTimeout(1000).socketTimeout(3000)
               .execute()
               .returnContent().toString();
        JSONObject jsonObject = JSONObject.parseObject(data);
        List<JSONObject> result = new ArrayList<>();

        JSONArray transactions = jsonObject.getJSONArray("signedTransactions");
        for (int i=0; i<transactions.size(); i++) {
            DecodedJWT decodedJWT = JWT.decode(transactions.getString(i));
            //验证签名
            ECPublicKey publicKey = (ECPublicKey) getFirstPublicKey(decodedJWT.getHeader());
            Verification verification = JWT.require(Algorithm.ECDSA256(publicKey, null));
            verification.build().verify(decodedJWT);


            byte[] base64 = Base64.getDecoder().decode(decodedJWT.getPayload());
            String decodePayLoad = new String(base64);
            result.add(JSONObject.parseObject(decodePayLoad));
        }
        System.out.println(result);
    }



    /**
     * 按JWT规范要求生成token
     * 对于正式环境bid不同可能会报错401
     * todo:token redis缓存
     */
    public static String iosToken(String bundleId) throws Exception {
        // 设置头部信息
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "ES256");
        header.put("kid", "");
        header.put("typ", "JWT");

        Map<String, Object> payload = new HashMap<>();
        payload.put("iss", "");
        payload.put("aud", "appstoreconnect-v1");
        Long iat = System.currentTimeMillis()/1000;
        payload.put("iat", iat);
        payload.put("exp", iat + 60 * 60);
        payload.put("nonce", "6edffe66-b482-11eb-8529-0242ac130003");
        payload.put("bid", bundleId);

        PrivateKey privateKey = getPrivateKey("", "EC");
        Algorithm algorithm = Algorithm.ECDSA256(null, (ECPrivateKey) privateKey);
        return JWT.create().withHeader(header).withPayload(payload).sign(algorithm);
    }

    /**
     * 根据证书获取PrivateKey
     */
    private static PrivateKey getPrivateKey(String certPath, String algorithm) throws Exception {
        SignedPack signedPack = CertificateCache.getSignedPack(certPath);
        if(signedPack != null) {
            return signedPack.getPriKey();
        }
        //证书里的是base64，也可以直接复制出来用
        String content = new String(Files.readAllBytes(Paths.get(certPath)), "utf-8");
        String privateKey = content.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");

        KeyFactory kf = KeyFactory.getInstance(algorithm);
        PrivateKey priKey = kf.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));

        signedPack = new SignedPack();
        signedPack.setPriKey(priKey);
        CertificateCache.addSignedPack(certPath, signedPack);

        return priKey;
    }

    /**
     * 返回x5c证书链中的第一个证书的公钥，用来验证JWT
     */
    private static PublicKey getFirstPublicKey(String encodeHeader) throws NoSuchProviderException, CertificateException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException {
        String decode = new String(Base64.getDecoder().decode(encodeHeader));
        JSONObject object = JSONObject.parseObject(decode);
        JSONArray x5c = object.getJSONArray("x5c");
        int size = x5c.size();
        //用苹果提供的AppleRootCA-G3.cer证书内容验证JWT x5c证书链中最后一个证书
        if(!APPLE_RootCA_G3_PEM_CONTEXT.equals(x5c.getString(size - 1 ))) {
            throw new RuntimeException("");
        }

        //获取x5c对应的X509Certificate
        X509Certificate[] certificates = new X509Certificate[size];
        for (int i=0; i<size; i++) {
            X509Certificate certificate = (X509Certificate) getCertificate(x5c.getString(i), "X.509");
            certificates[i] = certificate;
        }

        //利用x509证书链规范，验证剩下的每个证书链
        for (int i=0; i<size-2; i++) {
            certificates[i].verify(certificates[i+1].getPublicKey());
        }
        return certificates[0].getPublicKey();
    }

    private static Certificate getCertificate(String context, String algorithm) throws CertificateException {
        CertificateFactory cFactory = CertificateFactory.getInstance(algorithm);
        return cFactory.generateCertificate(new ByteArrayInputStream(Base64.getDecoder().decode(context)));
    }

}
