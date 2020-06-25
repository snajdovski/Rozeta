package com.snajdovski.textencryptor;

import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

class AES {
    private static String AES = "AES";

    AES() {
    }

    private static SecretKeySpec generateKey(String str) throws Exception {
        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        instance.update(bytes, 0, bytes.length);
        return new SecretKeySpec(instance.digest(), AES);
    }

    static String encrypt(String str, String str2) throws Exception {
        Cipher instance = Cipher.getInstance(AES);
        instance.init(1, generateKey(str2));
        return Base64.encodeToString(instance.doFinal(str.getBytes()), 0);
    }

    static String decrypt(String str, String str2) throws Exception {
        Cipher instance = Cipher.getInstance(AES);
        instance.init(2, generateKey(str2));
        return new String(instance.doFinal(Base64.decode(str, 0)));
    }
}