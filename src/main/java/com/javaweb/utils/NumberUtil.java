package com.javaweb.utils;

public class NumberUtil {
    public static boolean isNumber(String s){
        try{
            Long tmp = Long.parseLong(s);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
