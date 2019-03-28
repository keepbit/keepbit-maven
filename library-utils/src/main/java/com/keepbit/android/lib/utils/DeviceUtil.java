package com.keepbit.android.lib.utils;

import android.os.Build;

import java.util.UUID;

/**
 * Created by CoderMario on 2019-03-27.
 */
public class DeviceUtil {

    /***
     * Generates a unique identity for the device.
     */
    public static String getUniqueIdentify() {
        String identify = initUniqueIdentify();
        return identify.toUpperCase();
    }

    /***
     * Generates a unique identity for the device.
     */
    private static String initUniqueIdentify() {
        String value = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10)
                + (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10)
                + (Build.PRODUCT.length() % 10);
        String serial = "serial";
        try {
            serial = Build.class.getField("SERIAL").get(null).toString();
            return new UUID(value.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            exception.printStackTrace();
            return new UUID(value.hashCode(), serial.hashCode()).toString();
        }
    }
}
