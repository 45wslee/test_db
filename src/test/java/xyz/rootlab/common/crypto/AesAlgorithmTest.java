package xyz.rootlab.common.crypto;

import org.junit.jupiter.api.Test;

class AesAlgorithmTest {
    @Test
    public void test1() throws Exception {
        String enc = "d93MZZnGkNHywxPIB+bAEA==";
        String decrypt = AesAlgorithm.decrypt(enc, "ieetuWebSocket!@");
        System.out.println("decrypt = " + decrypt);

        String encrypt = AesAlgorithm.encrypt("안녕하세요", "ieetuWebSocket!@");
        System.out.println("encrypt = " + encrypt);
    }
}