package com.keepbit.android.app.user;

import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Created by CoderMario on 2019-05-15.
 */
public class UserManager {

    private static UserManager sUserManager;

    private WeakReference<Context> mContextWeakReference;

    public static synchronized UserManager getInstance() {
        if (null == sUserManager) synchronized (UserManager.class) {
            if ( null == sUserManager) {
                sUserManager = new UserManager();
            }
        }
        return sUserManager;
    }

    private UserManager() {
        //
    }

    public void init(Context context) {
        if (null != context) {
            mContextWeakReference = new WeakReference<>(context);
        }
    }
}
