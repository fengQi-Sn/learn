package certificate;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 证书缓存
 * @author gaomin
 */
public class CertificateCache {

    private CertificateCache() {
    }

    private static class SingletonHandler {
        private static ConcurrentHashMap<String, SignedPack> signedPacks = new ConcurrentHashMap();
    }


    public static ConcurrentHashMap<String, SignedPack> getInstance() {
        return SingletonHandler.signedPacks;
    }


    public static void addSignedPack(String path, SignedPack signedPack) {
        getInstance().put(path, signedPack);
    }


    public static SignedPack getSignedPack(String path) {
        return getInstance().get(path);
    }

}
