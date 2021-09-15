package src;

import java.security.SecureRandom;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class KeyGen {
    private static byte[] randomKey = null;
    private static byte[] hmac256 = null;

    public static byte[] generateKey() {
        SecureRandom random = new SecureRandom();
        randomKey = random.generateSeed(16);
        return randomKey;
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] generateHmac256(byte[] secretKey, byte[] message) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec sks = new SecretKeySpec(secretKey, "HmacSHA256");
            mac.init(sks);
            hmac256 = mac.doFinal(message);
            return hmac256;
        } catch(Exception e) {
            throw new RuntimeException("Failed to generate HMACSHA256 encrypt.");
        }
    }

    public static String getHexKey() {
        return bytesToHex(randomKey);
    }

    public static String getHexHmac() {
        return bytesToHex(hmac256);
    }
}