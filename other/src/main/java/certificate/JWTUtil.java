package certificate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.http.client.fluent.Request;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;


public class JWTUtil {
    public static void main(String[] args) throws Exception {
        String token = iosToken("");
       String data = Request.Get("https://api.storekit-sandbox.itunes.apple.com/inApps/v1/lookup/{orderId}")
               .addHeader("Authorization", "Bearer " + token)
               .connectTimeout(1000).socketTimeout(3000)
               .execute()
               .returnContent().toString();
        JSONObject result = JSONObject.parseObject(data);
        List<JSONObject> transactionResponses = new ArrayList<>();
        //处理transactions数组，每一条jwt解码
        JSONArray transactions = result.getJSONArray("signedTransactions");
        for (int i=0; i<transactions.size(); i++) {
            DecodedJWT decodedJWT = JWT.decode(transactions.getString(i));
            byte[] base64 = Base64.getDecoder().decode(decodedJWT.getPayload());
            String decodePayLoad = new String(base64);
            transactionResponses.add(JSONObject.parseObject(decodePayLoad));
        }
        System.out.println(transactionResponses);
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
     * 证书里的是base64，也可以直接复制出来用
     */
    private static PrivateKey getPrivateKey(String certPath, String algorithm) throws Exception {
        SignedPack signedPack = CertificateCache.getSignedPack(certPath);
        if(signedPack != null) {
            return signedPack.getPriKey();
        }
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
}
