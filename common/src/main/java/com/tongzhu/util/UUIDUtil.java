package com.tongzhu.util;

import java.util.UUID;

public class UUIDUtil {

    public static String generateOriginalUUID(){
        return UUID.randomUUID().toString();
    }

    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
