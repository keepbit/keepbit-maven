package com.keepbit.android.lib.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;

/***
 * Created by CoderMario on 2019-03-28.
 */
public class AttributeUtil {

    /***
     * Retrieve the value of an attribute in the Theme.
     * */
    public static ColorStateList getColorStateListByAttr(@NonNull Context context, @AttrRes int attr
            , @Nullable ColorStateList defColorStateList) {
        TypedValue typedValue = resolveAttribute(context, attr);
        if (null == typedValue) {
            return defColorStateList;
        }
        return ResourceUtil.getColorStateList(context, typedValue.resourceId);
    }

    /***
     * Retrieve the value of an attribute in the Theme.
     * */
    public static ColorStateList getColorStateListByAttr(@NonNull Context context, @AttrRes int attr) {
        TypedValue typedValue = resolveAttribute(context, attr);
        if (null == typedValue) {
            return null;
        }
        return ResourceUtil.getColorStateList(context, typedValue.resourceId);
    }

    /***
     * Retrieve the value of an attribute in the Theme.
     * */
    public static Drawable getDrawableByAttr(@NonNull Context context, @AttrRes int attr
            , @Nullable Drawable defDrawable) {
        TypedValue typedValue = resolveAttribute(context, attr);
        if (null == typedValue) {
            return defDrawable;
        }
        return ResourceUtil.getDrawable(context, typedValue.resourceId);
    }

    /***
     * Retrieve the value of an attribute in the Theme.
     * */
    public static Drawable getDrawableByAttr(@NonNull Context context, @AttrRes int attr) {
        TypedValue typedValue = resolveAttribute(context, attr);
        if (null == typedValue) {
            return null;
        }
        return ResourceUtil.getDrawable(context, typedValue.resourceId);
    }

    /***
     * Retrieve the value of an attribute in the Theme.
     * */
    public static int getColorByAttr(@NonNull Context context, @AttrRes int attr
            , @ColorInt int defColor) {
        TypedValue typedValue = resolveAttribute(context, attr);
        if (null == typedValue) {
            return defColor;
        }
        return ResourceUtil.getColor(context, typedValue.resourceId);
    }

    /***
     * Retrieve the value of an attribute in the Theme.
     * */
    public static int getColorByAttr(@NonNull Context context, @AttrRes int attr) {
        TypedValue typedValue = resolveAttribute(context, attr);
        if (null == typedValue) {
            return Color.BLACK;
        }
        return ResourceUtil.getColor(context, typedValue.resourceId);
    }

    /***
     * Retrieve the value of an attribute in the Theme.
     * */
    private static TypedValue resolveAttribute(@NonNull Context context, @AttrRes int attr) {
        Resources.Theme theme = context.getTheme();
        if (null == theme) {
            return null;
        }
        TypedValue typedValue = new TypedValue();
        if (theme.resolveAttribute(attr, typedValue, true)) {
            return typedValue;
        } else {
            return null;
        }
    }
}
