package com.test.userservicetest.domain.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    public static String pwdTransform(String pwd) {
        StringBuilder sb = new StringBuilder();

        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] pwdArray = pwd.getBytes();
            byte[] digestArray = md5.digest(pwdArray);
            char[] charArray = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                                            'A', 'B', 'C', 'D', 'E', 'F'};
            for (byte b : digestArray) {
                sb.append(charArray[(b >> 4) & 15]);
                sb.append(charArray[b & 15]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

}
