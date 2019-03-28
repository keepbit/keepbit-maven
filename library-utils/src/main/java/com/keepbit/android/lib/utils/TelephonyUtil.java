package com.keepbit.android.lib.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;

/**
 * Created by CoderMario on 2019-03-28.
 */
public class TelephonyUtil {

    /***
     * Return the network type for current data connection.
     * */
    public static int getNetworkType(Context context) {
        TelephonyManager telephonyManager = getTelephonyManagerInner(context);
        if (null == telephonyManager) {
            return 0 - 1;
        }
        return telephonyManager.getNetworkType();
    }

    /***
     * Returns the Service Provider Name (SPN).
     * */
    public static String getNetworkOperatorName(Context context) {
        TelephonyManager telephonyManager = getTelephonyManagerInner(context);
        if (null == telephonyManager) {
            return null;
        }
        return telephonyManager.getNetworkOperatorName();
    }

    /***
     * Returns a constant indicating the device phone type.
     * */
    public static int getPhoneType(Context context) {
        TelephonyManager telephonyManager = getTelephonyManagerInner(context);
        if (null == telephonyManager) {
            return 0 - 1;
        }
        return telephonyManager.getPhoneType();
    }

    /***
     * Returns the Service Provider Name (SPN).
     * */
    public static String getSimOperatorName(Context context) {
        TelephonyManager telephonyManager = getTelephonyManagerInner(context);
        if (null == telephonyManager) {
            return null;
        }
        return telephonyManager.getSimOperatorName();
    }

    /***
     * Returns the MCC+MNC (mobile country code + mobile network code) of the
     * provider of the SIM. 5 or 6 decimal digits.
     * */
    public static String getSimOperator(Context context) {
        TelephonyManager telephonyManager = getTelephonyManagerInner(context);
        if (null == telephonyManager) {
            return null;
        }
        return telephonyManager.getSimOperator();
    }

    /***
     * Returns a constant indicating the type of activity on a data connection.
     * */
    public static int getDataActivity(Context context) {
        TelephonyManager telephonyManager = getTelephonyManagerInner(context);
        if (null == telephonyManager) {
            return 0 - 1;
        }
        return telephonyManager.getDataActivity();
    }

    /****/
    private static TelephonyManager getTelephonyManagerInner(@NonNull Context context) {
        Object service = context.getSystemService(Context.TELEPHONY_SERVICE);
        if (service instanceof TelephonyManager) {
            return (TelephonyManager) service;
        }
        return null;
    }
}
