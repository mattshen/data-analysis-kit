package io.github.mattshen.dakit.utils;


public class StringUtils {

    public static String trim(Object obj) {
        if (obj == null) {
            return null;
        } else {
            return String.valueOf(obj).trim();
        }
    }

    public static boolean isNumber(String s) {
        return isNumber(s,10);
    }

    private static boolean isNumber(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }

}
