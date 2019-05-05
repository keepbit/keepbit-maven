package com.keepbit.android.lib.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by CoderMario on 2019-04-29.
 */
public class ProcessUtil {

    public static String getCurrentProcessName(@NonNull Context context) {
        Object service = context.getSystemService(Context.ACTIVITY_SERVICE);
        if (!(service instanceof ActivityManager)) {
            return null;
        }
        ActivityManager manager = (ActivityManager) service;
        List<ActivityManager.RunningAppProcessInfo> processList = manager.getRunningAppProcesses();
        if (null == processList || processList.isEmpty()) {
            return null;
        }
        int pid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo process : processList) {
            if (null != process && process.pid == pid) {
                return process.processName;
            }
        }
        return null;
    }
}
