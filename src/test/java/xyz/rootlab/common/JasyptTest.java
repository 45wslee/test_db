package xyz.rootlab.common;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

public class JasyptTest {
    String key = "lhmp";

    @Test
    public void 암호화() throws Exception {
        StandardPBEStringEncryptor pbeStringEncryptor = new StandardPBEStringEncryptor();
        pbeStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        pbeStringEncryptor.setPassword(key);

        String url = "jdbc:sqlserver://220.117.241.107:1433;databaseName=gcuai;encrypt=true;trustServerCertificate=true";
        String username = "lhmp";
        String password = "Q5Gy54wrMIo9e6s";

        String encryptedUrl = pbeStringEncryptor.encrypt(url);
        System.out.println("encryptedUrl = ENC(" + encryptedUrl + ")");

        String encryptedUsername = pbeStringEncryptor.encrypt(username);
        System.out.println("encryptedUsername = ENC(" + encryptedUsername + ")");

        String encryptedPassword = pbeStringEncryptor.encrypt(password);
        System.out.println("encryptedPassword = ENC(" + encryptedPassword + ")");
    }

    @Test
    public void 복호화() throws Exception {
        String encryptedMessage = "BGTaPeA6RT/kvBVbPbwloH67Qb1dbvvk";

        StandardPBEStringEncryptor pbeStringEncryptor = new StandardPBEStringEncryptor();
        pbeStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        pbeStringEncryptor.setPassword(key);
        String decryptedValue = pbeStringEncryptor.decrypt(encryptedMessage);
        System.out.println("decryptedValue = " + decryptedValue);
    }
}