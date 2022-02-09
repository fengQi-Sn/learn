package certificate;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    public static void main(String[] args) throws Exception {
        System.out.println(
                getPrivateKey("/Users/dz0400820/Downloads/2022/my.p8", "EC")
        );

    }



    /**
     * 按JWT规范要求生成token
     * 对于正式环境bid不同会报错401
     * 对于沙盒环境没有bid的限制，bid错误是查不到单子4040005
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
