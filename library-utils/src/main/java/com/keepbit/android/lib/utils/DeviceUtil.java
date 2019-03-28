package com.keepbit.android.lib.utils;

import android.hardware.Camera;
import android.os.Build;

import java.util.UUID;

/**
 * Created by CoderMario on 2019-03-27.
 */
public class DeviceUtil {

    /**
     * The method used to determine whether the camera is available.
     * */
    public static boolean isCameraAvailable() {
        boolean available = true;
        Camera camera = null;
        try {
            camera = Camera.open();
            Camera.Parameters parameters = camera.getParameters();
            camera.setParameters(parameters);
        } catch (Exception e) {
            e.printStackTrace();
            available = false;
        } finally {
            if (null != camera) {
                camera.release();
            }
        }
        return available;
    }

    /***
     * Generates a unique identity for the device.
     */
    public static String getUniqueIdentify() {
        String identify = initUniqueIdentify();
        return identify.toUpperCase();
    }

    /***
     * Generates a unique identity for the device.
     *
     * {@see <a href="https://github.com/giantray/stackoverflow-java-top-qa/blob/master/contents/is-there-a-unique-android-device-id.md">}
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
