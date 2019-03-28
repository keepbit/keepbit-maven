package com.keepbit.android.lib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

/**
 * Created by CoderMario on 2019-03-28.
 */
public class NetworkUtil {

    private static ConnectivityManager getConnectivityManagerInner(@NonNull Context context) {
        Object service = context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (service instanceof ConnectivityManager) {
            return (ConnectivityManager) service;
        }
        return null;
    }

    public static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager connectivityManager = getConnectivityManagerInner(context);
        if (null == connectivityManager) {
            return null;
        }
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
    }
}
