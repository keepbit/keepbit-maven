package com.keepbit.android.lib.utils;

/**
 * Created by CoderMario on 2019-03-27.
 */
public class ParseUtil {

    /**
     * Read {@link Long#parseLong(String, int)}
     * */
    public static long parse2Long(String string, int radix, long defValue) {
        if (null == string || 0 >= string.trim().length()) {
            return defValue;
        }
        try {
            defValue = Long.parseLong(string, radix);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defValue;
    }

    /**
     * Read {@link Long#parseLong(String, int)}
     * */
    public static long parse2Long(String string, long defValue) {
        if (null == string || 0 >= string.trim().length()) {
            return defValue;
        }
        try {
            defValue = Long.parseLong(string, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defValue;
    }

    /**
     * Read {@link Integer#parseInt(String)}
     * */
    public static int parse2Int(String string, int radix, int defValue) {
        if (null == string || 0 >= string.trim().length()) {
            return defValue;
        }
        try {
            defValue = Integer.parseInt(string, radix);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defValue;
    }

    /**
     * Read {@link Integer#parseInt(String, int)}
     * */
    public static int parse2Int(String string, int defValue) {
        if (null == string || 0 >= string.trim().length()) {
            return defValue;
        }
        try {
            defValue = Integer.parseInt(string, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defValue;
    }

    /**
     * Read {@link Float#parseFloat(String)}
     * */
    public static float parse2Float(String string, float defValue) {
        if (null == string || 0 >= string.trim().length()) {
            return defValue;
        }
        try {
            defValue = Float.parseFloat(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defValue;
    }
}
