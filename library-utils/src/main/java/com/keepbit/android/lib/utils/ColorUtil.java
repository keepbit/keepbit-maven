package com.keepbit.android.lib.utils;

import android.graphics.Color;
import android.support.annotation.ColorInt;

/***
 * Created by CoderMario on 2019-03-28.
 */
public class ColorUtil {

    /**
     * Converts the color value to a #XXXXXXXX string
     */
    public static String color2String(@ColorInt int color) {
        return String.format("#%08X", color);
    }

    /**
     * Sets the transparency of the given color
     * */
    public static int setColorAlpha(@ColorInt int color, float alpha) {
        return color & 0x00FFFFFF | (int) (alpha * 255) << 24;
    }

    /**
     * Calculates a color value between two color values based on the ratio
     * Note that the method is scaled separately by the ARGB channel
     */
    public static int computeColor(@ColorInt int colorA, @ColorInt int colorB, float fraction) {
        fraction = Math.max(Math.min(fraction, 1), 0);

        int alphaColorA = Color.alpha(colorA);
        int alphaColorB = Color.alpha(colorB);
        int resultA = (int) ((alphaColorB - alphaColorA) * fraction) + alphaColorA;

        int rColorA = Color.red(colorA);
        int rColorB = Color.red(colorB);
        int resultR = (int) ((rColorB - rColorA) * fraction) + rColorA;

        int gColorA = Color.green(colorA);
        int gColorB = Color.green(colorB);
        int resultG = (int) ((gColorB - gColorA) * fraction) + gColorA;

        int bColorA = Color.blue(colorA);
        int bColorB = Color.blue(colorB);
        int resultB = (int) ((bColorB - bColorA) * fraction) + bColorA;

        return Color.argb(resultA, resultR, resultG, resultB);
    }
}
