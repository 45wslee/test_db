package xyz.rootlab.common.crypto;

import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

class RsaAlgorithmTest {
    @Test
    public void test1() throws Exception {
        KeyPair keyPair = RsaAlgorithm.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String publicKeyStr = RsaAlgorithm.getPublicKey(keyPair);
        System.out.println("publicKeyStr = " + publicKeyStr);
        String privateKeyStr = RsaAlgorithm.getPrivateKey(keyPair);
        System.out.println("privateKeyStr = " + privateKeyStr);

        String plainText = "안녕하세요";
        String encryptedData = RsaAlgorithm.encrypt(plainText, publicKeyStr);
        System.out.println("encryptedData = " + encryptedData);

        String decrypt = RsaAlgorithm.decrypt(encryptedData, privateKeyStr);
        System.out.println("decrypt = " + decrypt);
    }
}