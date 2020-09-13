package study.java;

import org.bouncycastle.crypto.generators.SCrypt;

import java.security.SecureRandom;

import static java.nio.charset.StandardCharsets.UTF_16;

public class KeyGenerator {

    private static final int SCRYPT_COST = 16384;
    private static final int SCRYPT_BLOCK_SIZE = 8;
    private static final int SCRYPT_PARALLELISM = 1;
    private static final int KEY_LENGTH = 20;

    private static final int SALT_LENGTH = 16;

    private static final SecureRandom secureRandom = new SecureRandom();

    public static byte[] hash(String password, byte[] salt) {
        byte[] passwordBytes = password.getBytes(UTF_16);
        return SCrypt.generate(
                passwordBytes,
                salt,
                SCRYPT_COST,
                SCRYPT_BLOCK_SIZE,
                SCRYPT_PARALLELISM,
                KEY_LENGTH);
    }

    // 해싱 함수에 임의의 랜덤 값을 추가해서 조작되는 것을 막는다
    public static byte[] newSalt() {
        final byte[] salt = new byte[SALT_LENGTH];
        secureRandom.nextBytes(salt);
        return salt;
    }
}
