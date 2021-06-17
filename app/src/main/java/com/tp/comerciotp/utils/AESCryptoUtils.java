package com.tp.comerciotp.utils;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESCryptoUtils extends Base64Utils{

    private static int ksize = StringSTP.getKsize();
    private static byte[] IV = StringSTP.getIV();

    public static String encrypt(String plainText) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(StringSTP.getMethod());
            keyGenerator.init(ksize);
            byte[] decodedKey = base64Decode(StringSTP.getKs());//Base64.decode(StringSTP.getKs(),Base64.NO_WRAP);
            SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, StringSTP.getMethod());
            byte[] encrypt = encrypt(plainText.getBytes(), key, IV);
            return Base64.encodeToString(encrypt, Base64.DEFAULT).replaceAll("\n", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String encrypText){
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(StringSTP.getMethod());
            keyGenerator.init(ksize);
            byte[] decodedKey = base64Decode(StringSTP.getKs());
            SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, StringSTP.getMethod());
            return new String(desencrypt(encrypText.getBytes(), key, IV));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] encrypt(byte[] plaintext, SecretKey key, byte[] IV) throws Exception {
        Cipher cipher = Cipher.getInstance(StringSTP.getValueCyper());
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), StringSTP.getMethod());
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] cipherText = cipher.doFinal(plaintext);
        return cipherText;
    }

    private static byte[] desencrypt(byte[] plaintext, SecretKey key, byte[] IV) throws Exception {
        Cipher cipher = Cipher.getInstance(StringSTP.getValueCyper());
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), StringSTP.getMethod());
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        return cipher.doFinal(Base64.decode(plaintext, Base64.DEFAULT));
    }
}
