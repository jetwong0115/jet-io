package org.jet.io.common.utils.common;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class MyAesUtil {
    public final static String KEY_ALGORITHMS = "AES";
    public final static int KEY_SIZE = 128;


    /**
     * 生成AES密钥，base64编码格式 (128)
     *
     * @return
     * @throws Exception
     */
    public static String getKeyAES_128() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(KEY_ALGORITHMS);
        keyGen.init(KEY_SIZE);
        SecretKey key = keyGen.generateKey();
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * 生成AES密钥，base64编码格式 (256)
     *
     * @return
     * @throws Exception
     */
    public static String getKeyAES_256() throws Exception {
        // 256需要换jar包暂时用128
        String base64str = getKeyAES_128();
        return base64str;
    }


    /**
     * 根据base64Key获取SecretKey对象
     *
     * @param base64Key
     * @return
     */
    public static SecretKey loadKeyAES(String base64Key) {
        byte[] bytes = Base64.decodeBase64(base64Key);
        return new SecretKeySpec(bytes, KEY_ALGORITHMS);
    }

    /**
     * AES 加密字符串，SecretKey对象
     *
     * @param key
     * @param encryptData
     * @param encode
     * @return
     */
    public static String encrypt(SecretKey key, String encryptData, String encode) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {
        final Cipher cipher = Cipher.getInstance(KEY_ALGORITHMS);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptBytes = encryptData.getBytes(encode);
        byte[] result = cipher.doFinal(encryptBytes);
        return Base64.encodeBase64String(result);
    }

    /**
     * AES 加密字符串，base64Key对象
     *
     * @param base64Key
     * @param encryptData
     * @param encode
     * @return
     */
    public static String encrypt(String base64Key, String encryptData, String encode) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {
        SecretKey key = loadKeyAES(base64Key);
        final Cipher cipher = Cipher.getInstance(KEY_ALGORITHMS);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptBytes = encryptData.getBytes(encode);
        byte[] result = cipher.doFinal(encryptBytes);
        return Base64.encodeBase64String(result);
    }

    /**
     * AES 解密字符串，SecretKey对象
     *
     * @param key
     * @param decryptData
     * @param encode
     * @return
     */
    public static String decrypt(SecretKey key, String decryptData, String encode) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {
        final Cipher cipher = Cipher.getInstance(KEY_ALGORITHMS);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptBytes = Base64.decodeBase64(decryptData);
        byte[] result = cipher.doFinal(decryptBytes);
        return new String(result, encode);
    }

    /**
     * AES 解密字符串，base64Key对象
     *
     * @param base64Key
     * @param decryptData
     * @param encode
     * @return
     */
    public static String decrypt(String base64Key, String decryptData, String encode) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {
        SecretKey key = loadKeyAES(base64Key);

        final Cipher cipher = Cipher.getInstance(KEY_ALGORITHMS);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptBytes = Base64.decodeBase64(decryptData);
        byte[] result = cipher.doFinal(decryptBytes);
        return new String(result, encode);

    }


}
