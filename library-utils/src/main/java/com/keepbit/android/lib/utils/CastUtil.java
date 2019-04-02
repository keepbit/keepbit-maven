package com.keepbit.android.lib.utils;

/***
 * Created by CoderMario on 2019-03-27.
 */
public class CastUtil {

    /**
     * Cast the given object to String
     * */
    public static String parse2String(Object object, String defValue) {
        if (object instanceof String) {
            return (String) object;
        } else {
            return defValue;
        }
    }

    /**
     * Cast the given object to Float
     * */
    public static float parse2Long(Object object, float defValue) {
        if (object instanceof Float) {
            return (float) object;
        } else {
            return defValue;
        }
    }

    /**
     * Cast the given object to Long
     * */
    public static long parse2Long(Object object, long defValue) {
        if (object instanceof Long) {
            return (long) object;
        } else {
            return defValue;
        }
    }

    /**
     * Cast the given object to Integer
     * */
    public static int parse2Int(Object object, int defValue) {
        if (object instanceof Integer) {
            return (int) object;
        } else {
            return defValue;
        }
    }
}
