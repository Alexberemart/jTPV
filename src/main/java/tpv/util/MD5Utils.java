package tpv.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Utils {

    public static String md5(String content) {
        try {
            byte[] byteArray = MessageDigest.getInstance("MD5")
                    .digest(content.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : byteArray) {
                String hexPart = Integer.toHexString(0xFF & b);
                hexString.append(((hexPart.length() == 1) ? "0" : "") +
                        hexPart);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String md5(byte[] file) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(file);
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
        //Now we need to zero pad it if you actually want the full 32 chars.
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }

}
