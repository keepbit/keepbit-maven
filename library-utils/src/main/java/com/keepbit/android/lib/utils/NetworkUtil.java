package com.keepbit.android.lib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;

/***
 * Created by CoderMario on 2019-03-28.
 */
public class NetworkUtil {

    /***
     * */
    private static ConnectivityManager getConnectivityManagerInner(@NonNull Context context) {
        Object service = context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (service instanceof ConnectivityManager) {
            return (ConnectivityManager) service;
        }
        return null;
    }

    /***
     * */
    public static boolean isNetworkConnected(Context context, int type) {
        ConnectivityManager connectivityManager = getConnectivityManagerInner(context);
        if (null == connectivityManager) {
            return false;
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = connectivityManager.getAllNetworks();
            if (null == networks || 0 >= networks.length) {
                return false;
            }
            for (Network network : networks) {
                if (null == network) {
                    continue;
                }
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
                if (null != networkInfo && type == networkInfo.getType()) {
                    return networkInfo.isConnected();
                }
            }
        } else {
            NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
            if (null == networkInfos || 0 >= networkInfos.length) {
                return false;
            }
            for (NetworkInfo networkInfo : networkInfos) {
                if (null != networkInfo && type == networkInfo.getType()) {
                    return networkInfo.isConnected();
                }
            }
        }
        return false;
    }

    /***
     * */
    public static boolean isNetworkActive(Context context, int type) {
        ConnectivityManager connectivityManager = getConnectivityManagerInner(context);
        if (null == connectivityManager) {
            return false;
        }

        NetworkInfo networkInfo = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network network = connectivityManager.getActiveNetwork();
            if (null != network) {
                networkInfo = connectivityManager.getNetworkInfo(network);
            }
        } else {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        if (null == networkInfo) {
            return false;
        }
        return type == networkInfo.getType();
    }

    /***
     * */
    public static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager connectivityManager = getConnectivityManagerInner(context);
        if (null == connectivityManager) {
            return null;
        }

        NetworkInfo networkInfo = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network network = connectivityManager.getActiveNetwork();
            if (null != network) {
                networkInfo = connectivityManager.getNetworkInfo(network);
            }
        } else {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return networkInfo;
    }
}
