package com.kzadha.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by nurlan on 2/12/17.
 */
public class CryptUtil {
    public static String cryptData(String value){
        MessageDigest md = null;
        String result = null;
        try {
            SecureRandom random = new SecureRandom();
            String salt = new BigInteger(130, random).toString(32);
            md = MessageDigest.getInstance("SHA-256");
            value = value + salt;
            md.update(value.getBytes("UTF-8"));
            byte[] digest = md.digest();
            result = String.format("%064x", new BigInteger(1, digest));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
