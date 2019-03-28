package com.keepbit.android.lib.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.DisplayMetrics;

/**
 * Created by CoderMario on 2019-03-27.
 */
public class ResourceUtil {

    /**
     * Returns a color associated with a particular resource ID.
     *
     * @throws android.content.res.Resources.NotFoundException if the given ID
     * does not exist.
     * */
    @ColorInt
    public static int getColor(@NonNull Context context, @ColorRes int resourceIdentify) {
        return ContextCompat.getColor(context, resourceIdentify);
    }

    /**
     * Returns a themed color integer associated with a particular resource ID.
     * If the resource holds a complex {@link ColorStateList}, then the default
     * color from the set is returned.
     *
     * @throws Resources.NotFoundException Throws NotFoundException if the given ID does
     * not exist.
     * */
    @ColorInt
    public static int getColor(@NonNull Context context, @ColorRes int resourceIdentify, @Nullable Resources.Theme theme) {
        return ResourcesCompat.getColor(context.getResources(), resourceIdentify, theme);
    }

    /**
     * Returns a color associated with a particular resource name.
     *
     * @throws android.content.res.Resources.NotFoundException if the given ID
     * does not exist.
     * */
    @ColorInt
    public static int getColor(@NonNull Context context, @NonNull String resourceName) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier(resourceName, "color", context.getPackageName());
        return ContextCompat.getColor(context, identifier);
    }

    /**
     * Returns a themed color integer associated with a particular resource name.
     * If the resource holds a complex {@link ColorStateList}, then the default
     * color from the set is returned.
     *
     * @throws Resources.NotFoundException Throws NotFoundException if the given ID does
     * not exist.
     * */
    @ColorInt
    public static int getColor(@NonNull Context context, @NonNull String resourceName, @Nullable Resources.Theme theme) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier(resourceName, "color", context.getPackageName());
        return ResourcesCompat.getColor(resources, identifier, theme);
    }

    /**
     * Returns a color associated with a particular resource name.
     *
     * @throws android.content.res.Resources.NotFoundException if the given ID
     * does not exist.
     * */
    @ColorInt
    public static int getColor(@NonNull Context context, @NonNull String packageName, @NonNull String resourceName) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier(resourceName, "color", packageName);
        return ContextCompat.getColor(context, identifier);
    }

    /**
     * Returns a themed color integer associated with a particular resource name.
     * If the resource holds a complex {@link ColorStateList}, then the default
     * color from the set is returned.
     *
     * @throws Resources.NotFoundException Throws NotFoundException if the given ID does
     * not exist.
     * */
    @ColorInt
    public static int getColor(@NonNull Context context, @NonNull String packageName, @NonNull String resourceName, @Nullable Resources.Theme theme) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier(resourceName, "color", packageName);
        return ResourcesCompat.getColor(resources, identifier, theme);
    }

    /**
     * Returns a drawable object associated with a particular resource ID.
     * */
    @Nullable
    public static Drawable getDrawable(@NonNull Context context, @DrawableRes int resourceIdentify) {
        return ContextCompat.getDrawable(context, resourceIdentify);
    }

    /**
     * Return a drawable object associated with a particular resource ID and
     * styled for the specified theme. Various types of objects will be
     * returned depending on the underlying resource -- for example, a solid
     * color, PNG image, scalable image, etc.
     * */
    @Nullable
    public static Drawable getDrawable(@NonNull Context context, @DrawableRes int resourceIdentify,  @Nullable Resources.Theme theme) {
        return ResourcesCompat.getDrawable(context.getResources(), resourceIdentify, theme);
    }

    /**
     * Returns a drawable object associated with a particular resource name.
     * */
    @Nullable
    public static Drawable getDrawable(@NonNull Context context, @NonNull String resourceName) {
        Resources resources = context.getResources();
        int identify = resources.getIdentifier(resourceName, "drawable", context.getPackageName());
        return ContextCompat.getDrawable(context, identify);
    }

    /**
     * Return a drawable object associated with a particular resource name and
     * styled for the specified theme. Various types of objects will be
     * returned depending on the underlying resource -- for example, a solid
     * color, PNG image, scalable image, etc.
     * */
    @Nullable
    public static Drawable getDrawable(@NonNull Context context, @NonNull String resourceName, @Nullable Resources.Theme theme) {
        Resources resources = context.getResources();
        int identify = resources.getIdentifier(resourceName, "drawable", context.getPackageName());
        return ResourcesCompat.getDrawable(resources, identify, theme);
    }

    /**
     * Returns a drawable object associated with a particular resource name.
     * */
    @Nullable
    public static Drawable getDrawable(@NonNull Context context, @NonNull String packageName, @NonNull String resourceName) {
        Resources resources = context.getResources();
        int identify = resources.getIdentifier(resourceName, "drawable", packageName);
        return ContextCompat.getDrawable(context, identify);
    }

    /**
     * Return a drawable object associated with a particular resource name and
     * styled for the specified theme. Various types of objects will be
     * returned depending on the underlying resource -- for example, a solid
     * color, PNG image, scalable image, etc.
     * */
    @Nullable
    public static Drawable getDrawable(@NonNull Context context, @NonNull String packageName, @NonNull String resourceName, @Nullable Resources.Theme theme) {
        Resources resources = context.getResources();
        int identify = resources.getIdentifier(resourceName, "drawable", packageName);
        return ResourcesCompat.getDrawable(resources, identify, theme);
    }

    /**
     * Returns a color state list associated with a particular resource ID.
     *
     * @throws android.content.res.Resources.NotFoundException if the given ID
     * does not exist.
     * */
    @Nullable
    public static ColorStateList getColorStateList(@NonNull Context context, @ColorRes int resourceIdentify) {
        return ContextCompat.getColorStateList(context, resourceIdentify);
    }

    /**
     * Returns a themed color state list associated with a particular resource
     * ID. The resource may contain either a single raw color value or a
     * complex {@link ColorStateList} holding multiple possible colors.
     *
     * @throws Resources.NotFoundException Throws NotFoundException if the given ID does
     * not exist.
     * */
    @Nullable
    public static ColorStateList getColorStateList(@NonNull Context context, @ColorRes int resourceIdentify,  @Nullable Resources.Theme theme) {
        return ResourcesCompat.getColorStateList(context.getResources(), resourceIdentify, theme);
    }

    /**
     * Returns a color state list associated with a particular resource ID.
     *
     * @throws android.content.res.Resources.NotFoundException if the given ID
     * does not exist.
     * */
    @Nullable
    public static ColorStateList getColorStateList(@NonNull Context context, @NonNull String resourceName) {
        Resources resources = context.getResources();
        int identify = resources.getIdentifier(resourceName, "color", context.getPackageName());
        return ContextCompat.getColorStateList(context, identify);
    }

    /**
     * Returns a themed color state list associated with a particular resource
     * name. The resource may contain either a single raw color value or a
     * complex {@link ColorStateList} holding multiple possible colors.
     *
     * @throws android.content.res.Resources.NotFoundException if the given ID
     * does not exist.
     * */
    @Nullable
    public static ColorStateList getColorStateList(@NonNull Context context, @NonNull String resourceName,  @Nullable Resources.Theme theme) {
        Resources resources = context.getResources();
        int identify = resources.getIdentifier(resourceName, "color", context.getPackageName());
        return ResourcesCompat.getColorStateList(context.getResources(), identify, theme);
    }

    /**
     * Returns a color state list associated with a particular resource ID.
     *
     * @throws android.content.res.Resources.NotFoundException if the given ID
     * does not exist.
     * */
    @Nullable
    public static ColorStateList getColorStateList(@NonNull Context context, @NonNull String packageName, @NonNull String resourceName) {
        Resources resources = context.getResources();
        int identify = resources.getIdentifier(resourceName, "color", packageName);
        return ContextCompat.getColorStateList(context, identify);
    }

    /**
     * Returns a themed color state list associated with a particular resource
     * name. The resource may contain either a single raw color value or a
     * complex {@link ColorStateList} holding multiple possible colors.
     *
     * @throws android.content.res.Resources.NotFoundException if the given ID
     * does not exist.
     * */
    @Nullable
    public static ColorStateList getColorStateList(@NonNull Context context, @NonNull String packageName, @NonNull String resourceName,  @Nullable Resources.Theme theme) {
        Resources resources = context.getResources();
        int identify = resources.getIdentifier(resourceName, "color", packageName);
        return ResourcesCompat.getColorStateList(context.getResources(), identify, theme);
    }

    /**
     * Retrieve a dimensional for a particular resource name.  Unit
     * conversions are based on the current {@link DisplayMetrics} associated
     * with the resources.
     *
     * @throws Resources.NotFoundException Throws NotFoundException if the given ID
     * does not exist.
     * */
    public static float getDimension(@NonNull Context context, @NonNull String resourceName) {
        Resources resources = context.getResources();
        int identify = resources.getIdentifier(resourceName, "dimen", context.getPackageName());
        return resources.getDimension(identify);
    }

    /**
     * Retrieve a dimensional for a particular resource name.  Unit
     * conversions are based on the current {@link DisplayMetrics} associated
     * with the resources.
     *
     * @throws Resources.NotFoundException Throws NotFoundException if the given ID
     * does not exist.
     * */
    public static float getDimension(@NonNull Context context, @NonNull String packageName, @NonNull String resourceName) {
        Resources resources = context.getResources();
        int identify = resources.getIdentifier(resourceName, "dimen", packageName);
        return resources.getDimension(identify);
    }

    /**
     * Retrieve a dimensional for a particular resource name for use
     * as a size in raw pixels.  This is the same as
     * {@link #getDimension}, except the returned value is converted to
     * integer pixels for use as a size.  A size conversion involves
     * rounding the base value, and ensuring that a non-zero base value
     * is at least one pixel in size.
     *
     * @throws Resources.NotFoundException Throws NotFoundException if the given ID
     * does not exist.
     * */
    public static int getDimensionPixelSize(@NonNull Context context, @NonNull String resourceName) {
        Resources resources = context.getResources();
        int identify = resources.getIdentifier(resourceName, "dimen", context.getPackageName());
        return resources.getDimensionPixelSize(identify);
    }

    /**
     * Retrieve a dimensional for a particular resource name for use
     * as a size in raw pixels.  This is the same as
     * {@link #getDimension}, except the returned value is converted to
     * integer pixels for use as a size.  A size conversion involves
     * rounding the base value, and ensuring that a non-zero base value
     * is at least one pixel in size.
     *
     * @throws Resources.NotFoundException Throws NotFoundException if the given ID
     * does not exist.
     * */
    public static int getDimensionPixelSize(@NonNull Context context, @NonNull String packageName, @NonNull String resourceName) {
        Resources resources = context.getResources();
        int identify = resources.getIdentifier(resourceName, "dimen", packageName);
        return resources.getDimensionPixelSize(identify);
    }

    /**
     * Retrieve a dimensional for a particular resource name for use
     * as an offset in raw pixels.  This is the same as
     * {@link #getDimension}, except the returned value is converted to
     * integer pixels for you.  An offset conversion involves simply
     * truncating the base value to an integer.
     *
     * @throws Resources.NotFoundException Throws NotFoundException if the given ID
     * does not exist.
     * */
    public static int getDimensionPixelOffset(@NonNull Context context, @NonNull String resourceName) {
        Resources resources = context.getResources();
        int identify = resources.getIdentifier(resourceName, "dimen", context.getPackageName());
        return resources.getDimensionPixelOffset(identify);
    }

    /**
     * Retrieve a dimensional for a particular resource name for use
     * as an offset in raw pixels.  This is the same as
     * {@link #getDimension}, except the returned value is converted to
     * integer pixels for you.  An offset conversion involves simply
     * truncating the base value to an integer.
     *
     * @throws Resources.NotFoundException Throws NotFoundException if the given ID
     * does not exist.
     * */
    public static int getDimensionPixelOffset(@NonNull Context context, @NonNull String packageName, @NonNull String resourceName) {
        Resources resources = context.getResources();
        int identify = resources.getIdentifier(resourceName, "dimen", packageName);
        return resources.getDimensionPixelOffset(identify);
    }

    /***
     * Return a resource identifier for the given resource name.  A fully
     * qualified resource name is of the form "package:type/entry".  The first
     * two components (package and type) are optional if defType and
     * defPackage, respectively, are specified here.
     * */
    public static int getDimensionIdentifier(@NonNull Context context, @NonNull String resourceName) {
        Resources resources = context.getResources();
        return resources.getIdentifier(resourceName, "dimen", context.getPackageName());
    }

    /***
     * Return a resource identifier for the given resource name.  A fully
     * qualified resource name is of the form "package:type/entry".  The first
     * two components (package and type) are optional if defType and
     * defPackage, respectively, are specified here.
     * */
    public static int getDimensionIdentifier(@NonNull Context context, @NonNull String packageName, @NonNull String resourceName) {
        Resources resources = context.getResources();
        return resources.getIdentifier(resourceName, "dimen", packageName);
    }

    /***
     * Return a resource identifier for the given resource name.  A fully
     * qualified resource name is of the form "package:type/entry".  The first
     * two components (package and type) are optional if defType and
     * defPackage, respectively, are specified here.
     * */
    public static int getDrawableIdentifier(@NonNull Context context, @NonNull String resourceName) {
        Resources resources = context.getResources();
        return resources.getIdentifier(resourceName, "drawable", context.getPackageName());
    }

    /***
     * Return a resource identifier for the given resource name.  A fully
     * qualified resource name is of the form "package:type/entry".  The first
     * two components (package and type) are optional if defType and
     * defPackage, respectively, are specified here.
     * */
    public static int getDrawableIdentifier(@NonNull Context context, @NonNull String packageName, @NonNull String resourceName) {
        Resources resources = context.getResources();
        return resources.getIdentifier(resourceName, "drawable", packageName);
    }

    /***
     * Return a resource identifier for the given resource name.  A fully
     * qualified resource name is of the form "package:type/entry".  The first
     * two components (package and type) are optional if defType and
     * defPackage, respectively, are specified here.
     * */
    public static int getMipmapIdentifier(@NonNull Context context, @NonNull String resourceName) {
        Resources resources = context.getResources();
        return resources.getIdentifier(resourceName, "mipmap", context.getPackageName());
    }

    /***
     * Return a resource identifier for the given resource name.  A fully
     * qualified resource name is of the form "package:type/entry".  The first
     * two components (package and type) are optional if defType and
     * defPackage, respectively, are specified here.
     * */
    public static int getMipmapIdentifier(@NonNull Context context, @NonNull String packageName, @NonNull String resourceName) {
        Resources resources = context.getResources();
        return resources.getIdentifier(resourceName, "mipmap", packageName);
    }

    /***
     * Return a resource identifier for the given resource name.  A fully
     * qualified resource name is of the form "package:type/entry".  The first
     * two components (package and type) are optional if defType and
     * defPackage, respectively, are specified here.
     * */
    public static int getLayoutIdentifier(@NonNull Context context, @NonNull String resourceName) {
        Resources resources = context.getResources();
        return resources.getIdentifier(resourceName, "layout", context.getPackageName());
    }

    /***
     * Return a resource identifier for the given resource name.  A fully
     * qualified resource name is of the form "package:type/entry".  The first
     * two components (package and type) are optional if defType and
     * defPackage, respectively, are specified here.
     * */
    public static int getLayoutIdentifier(@NonNull Context context, @NonNull String packageName, @NonNull String resourceName) {
        Resources resources = context.getResources();
        return resources.getIdentifier(resourceName, "layout", packageName);
    }

    /***
     * Return a resource identifier for the given resource name.  A fully
     * qualified resource name is of the form "package:type/entry".  The first
     * two components (package and type) are optional if defType and
     * defPackage, respectively, are specified here.
     * */
    public static int getStringIdentifier(@NonNull Context context, @NonNull String resourceName) {
        Resources resources = context.getResources();
        return resources.getIdentifier(resourceName, "string", context.getPackageName());
    }

    /***
     * Return a resource identifier for the given resource name.  A fully
     * qualified resource name is of the form "package:type/entry".  The first
     * two components (package and type) are optional if defType and
     * defPackage, respectively, are specified here.
     * */
    public static int getStringIdentifier(@NonNull Context context, @NonNull String packageName, @NonNull String resourceName) {
        Resources resources = context.getResources();
        return resources.getIdentifier(resourceName, "string", packageName);
    }

    /***
     * Return a resource identifier for the given resource name.  A fully
     * qualified resource name is of the form "package:type/entry".  The first
     * two components (package and type) are optional if defType and
     * defPackage, respectively, are specified here.
     * */
    public static int getColorIdentifier(@NonNull Context context, @NonNull String resourceName) {
        Resources resources = context.getResources();
        return resources.getIdentifier(resourceName, "color", context.getPackageName());
    }

    /***
     * Return a resource identifier for the given resource name.  A fully
     * qualified resource name is of the form "package:type/entry".  The first
     * two components (package and type) are optional if defType and
     * defPackage, respectively, are specified here.
     * */
    public static int getColorIdentifier(@NonNull Context context, @NonNull String packageName, @NonNull String resourceName) {
        Resources resources = context.getResources();
        return resources.getIdentifier(resourceName, "color", packageName);
    }
}
