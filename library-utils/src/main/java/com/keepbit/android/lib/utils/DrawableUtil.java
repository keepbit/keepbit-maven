package com.keepbit.android.lib.utils;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;

/***
 * Created by CoderMario on 2019-03-28.
 */
public class DrawableUtil {

    /**
     * Specifies a tint for {@code drawable}.
     * */
    public static Drawable tint(@NonNull Drawable drawable, @ColorInt int color) {
        Drawable wrap = DrawableCompat.wrap(drawable.mutate());
        DrawableCompat.setTint(wrap, color);
        return wrap;
    }

    /**
     * Specifies a tint for {@code drawable} as a color state list.
     * */
    public static Drawable tint(@NonNull Drawable drawable, @NonNull ColorStateList colorStateList) {
        Drawable wrap = DrawableCompat.wrap(drawable.mutate());
        DrawableCompat.setTintList(wrap, colorStateList);
        return wrap;
    }
}
