package com.tp.comerciotp.utils;

import android.util.Base64;

public class Base64Utils {
    public static byte[] base64Decode(String text){
        return Base64.decode(text,Base64.NO_WRAP);
    }
}
