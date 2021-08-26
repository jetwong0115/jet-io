package org.jet.io.common.utils.common;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class SecurityUtil {
    private static final String DES = "DES";
    public static final String key = "wang!@#$%";

    public SecurityUtil() {
    }

    public static int compareVersion(String version1, String version2) throws Exception {
        if (version1 != null && version2 != null) {
            String[] versionArray1 = version1.split("\\.");
            String[] versionArray2 = version2.split("\\.");
            int idx = 0;
            int minLength = Math.min(versionArray1.length, versionArray2.length);

            int diff;
            for (diff = 0; idx < minLength && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0 && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0; ++idx) {
            }

            diff = diff != 0 ? diff : versionArray1.length - versionArray2.length;
            return diff;
        } else {
            throw new Exception("compareVersion error:illegal params.");
        }
    }

    public static String generateNonce(int num) {
        Random ran = new Random();
        StringBuilder cc = new StringBuilder();
        String code = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        for (int i = 0; i < num; ++i) {
            cc.append(code.charAt(ran.nextInt(code.length())));
        }

        return cc.toString();
    }

    public static String generateNumbericCheckcode(int num) {
        Random ran = new Random();
        StringBuilder cc = new StringBuilder();
        String code = "0123456789";

        for (int i = 0; i < num; ++i) {
            cc.append(code.charAt(ran.nextInt(code.length())));
        }

        return cc.toString();
    }

    public static String generateCheckcode(int num) {
        Random ran = new Random();
        StringBuilder cc = new StringBuilder();
        String code = "abcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < num; ++i) {
            cc.append(code.charAt(ran.nextInt(code.length())));
        }

        return cc.toString();
    }

    public static String generateCheckcode() {
        Random ran = new Random();
        StringBuilder cc = new StringBuilder();
        int num = 32;
        String code = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < num; ++i) {
            cc.append(code.charAt(ran.nextInt(code.length())));
        }

        return cc.toString() + "_" + System.currentTimeMillis() / 1000L;
    }

    public static String str2MD5(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("md5");
        md.update(s.getBytes(), 0, s.length());
        return (new BigInteger(1, md.digest())).toString(16);
    }

    public static String MD5(String sourceStr) {
        String result = "";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte[] b = md.digest();
            StringBuilder buf = new StringBuilder("");

            for (int offset = 0; offset < b.length; ++offset) {
                int i = b[offset];
                if (i < 0) {
                    i += 256;
                }

                if (i < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(i));
            }

            result = buf.toString();
            System.out.println("MD5(" + sourceStr + ",32) = " + result);
            System.out.println("MD5(" + sourceStr + ",16) = " + buf.toString().substring(8, 24));
        } catch (NoSuchAlgorithmException var7) {
            System.out.println(var7);
        }

        return result;
    }

    public static String MD5(byte[] sourceStr) {
        String result = "";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr);
            byte[] b = md.digest();
            StringBuilder buf = new StringBuilder("");

            for (int offset = 0; offset < b.length; ++offset) {
                int i = b[offset];
                if (i < 0) {
                    i += 256;
                }

                if (i < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(i));
            }

            result = buf.toString();
        } catch (NoSuchAlgorithmException var7) {
            System.out.println(var7.toString());
        }

        return result;
    }

    public static String SHA1(String decript) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte[] messageDigest = digest.digest();
            StringBuilder hexString = new StringBuilder();
            byte[] var4 = messageDigest;
            int var5 = messageDigest.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                byte aMessageDigest = var4[var6];
                String shaHex = Integer.toHexString(aMessageDigest & 255);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }

                hexString.append(shaHex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException var9) {
            var9.printStackTrace();
            return "";
        }
    }

    public static String standard(String data, String key) {
        Object var2 = null;

        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKeySpec spec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            mac.init(spec);
            byte[] byteHMAC = mac.doFinal(data.getBytes());
            return new String(byteHMAC);
        } catch (InvalidKeyException var5) {
            var5.printStackTrace();
        } catch (NoSuchAlgorithmException var6) {
        }

        return "";
    }

    public static String hamcsha1(byte[] data, byte[] key) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            return byte2hex(mac.doFinal(data));
        } catch (InvalidKeyException | NoSuchAlgorithmException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();

        for (int n = 0; b != null && n < b.length; ++n) {
            String stmp = Integer.toHexString(b[n] & 255);
            if (stmp.length() == 1) {
                hs.append('0');
            }

            hs.append(stmp);
        }

        return hs.toString().toUpperCase();
    }

    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(1, securekey, sr);
        return cipher.doFinal(data);
    }

    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(2, securekey, sr);
        return cipher.doFinal(data);
    }
}
