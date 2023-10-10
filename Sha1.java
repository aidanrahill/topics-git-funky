import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1 {
    public static String toSHA1(String input) {
        try {
            MessageDigest sha1Digest = MessageDigest.getInstance("SHA-1");
            byte[] bytes = input.getBytes();
            sha1Digest.update(bytes);
            byte[] sha1HashBytes = sha1Digest.digest();
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : sha1HashBytes) {
                hexStringBuilder.append(String.format("%02x", b));
            }
            return hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
