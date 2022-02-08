package certificate;

import lombok.*;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

/**
 * p12证书提取的信息
 * @author gaomin
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SignedPack {
    private byte[] signData;
    private PublicKey pubKey;
    private X509Certificate cert;
    private PrivateKey priKey;
}
