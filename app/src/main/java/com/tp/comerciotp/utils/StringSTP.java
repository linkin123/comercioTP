package com.tp.comerciotp.utils;

public class StringSTP {
    private static String ks = "VG90YWxwbGF5TW9iaWxlU2VjdXJpdHlMYXllcjIwMTk=";
    private static String method = "AES";
    private static int ksize = 256;
    private static byte[] IV = {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9};
    private static String valueCyper = "AES/CBC/PKCS5Padding";


    public static String getKs() {
        return ks;
    }

    public static String getMethod() {
        return method;
    }

    public static int getKsize() {
        return ksize;
    }

    public static byte[] getIV() {
        return IV;
    }

    public static String getValueCyper() {
        return valueCyper;
    }

}
