package com.keepbit.android.lib.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 * Created by CoderMario on 2019-03-27.
 */
public class DimensionUtil {
    
    /**
     * Gets display metrics that describe the size and density of this display.
     * The size returned by this method does not necessarily represent the
     * actual raw size (native resolution) of the display.
     * */
    public static DisplayMetrics getDisplayMetrics(@NonNull Context context) {
        return getDisplayMetricsInner(context);
    }

    /**
     * Converts an unpacked complex data value holding a dimension to its final floating
     * point value.
     * */
    public static float dp2valueFloat(@NonNull Context context, float dp) {
        DisplayMetrics displayMetrics = getDisplayMetricsInner(context);
        if (null == displayMetrics) {
            return 0;
        }
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
    }

    /**
     * Converts an unpacked complex data value holding a dimension to its final floating
     * point value.
     * */
    public static int dp2valueInt(@NonNull Context context, float dp) {
        DisplayMetrics displayMetrics = getDisplayMetricsInner(context);
        if (null == displayMetrics) {
            return 0;
        }
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
    }

    /**
     * Converts an unpacked complex data value holding a dimension to its final floating
     * point value.
     * */
    public static float px2valueFloat(@NonNull Context context, float px) {
        DisplayMetrics displayMetrics = getDisplayMetricsInner(context);
        if (null == displayMetrics) {
            return 0;
        }
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, displayMetrics);
    }

    /**
     * Converts an unpacked complex data value holding a dimension to its final floating
     * point value.
     * */
    public static int px2valueInt(@NonNull Context context, float px) {
        DisplayMetrics displayMetrics = getDisplayMetricsInner(context);
        if (null == displayMetrics) {
            return 0;
        }
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, displayMetrics);
    }

    /**
     * */
    public static int getStatusBarHeight(@NonNull Context context) {
        return ResourceUtil.getDimensionPixelOffset(context, "android", "status_bar_height");
    }


    /**
     * Gets display metrics that describe the size and density of this display.
     * The size returned by this method does not necessarily represent the
     * actual raw size (native resolution) of the display.
     * */
    private static DisplayMetrics getDisplayMetricsInner(@NonNull Context context) {
        Object service = context.getSystemService(Context.WINDOW_SERVICE);
        if (null == service) {
            return null;
        }
        WindowManager windowManager = (WindowManager) service;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
}
