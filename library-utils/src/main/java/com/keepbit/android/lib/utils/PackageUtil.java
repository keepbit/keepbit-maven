package com.keepbit.android.lib.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;

/**
 * Created by CoderMario on 2019-03-27.
 */
public class PackageUtil {

    /***
     * The version name of this package, as specified by the manifest.
     * */
    public static String getVersionName(@NonNull Context context, @NonNull String packageName) {
        PackageInfo packageInfo = getPackageInfo(context, packageName);
        if (null == packageInfo) {
            return null;
        } else {
            return packageInfo.versionName;
        }
    }

    /***
     * The version name of this package, as specified by the manifest.
     * */
    public static String getVersionName(@NonNull Context context) {
        PackageInfo packageInfo = getPackageInfo(context, context.getPackageName());
        if (null == packageInfo) {
            return null;
        } else {
            return packageInfo.versionName;
        }
    }

    /***
     * The version number of this package, as specified by the manifest.
     * */
    public static int getVersionCode(@NonNull Context context, @NonNull String packageName) {
        PackageInfo packageInfo = getPackageInfo(context, packageName);
        if (null == packageInfo) {
            return 0 - 1;
        } else {
            return packageInfo.versionCode;
        }
    }

    /***
     * The version number of this package, as specified by the manifest.
     * */
    public static int getVersionCode(@NonNull Context context) {
        PackageInfo packageInfo = getPackageInfo(context, context.getPackageName());
        if (null == packageInfo) {
            return 0 - 1;
        } else {
            return packageInfo.versionCode;
        }
    }

    /***
     * */
    @SuppressLint("PackageManagerGetSignatures")
    public static String getPackageSignature(@NonNull Context context, @NonNull String packageName) {
        if (0 >= packageName.length()) {
            return null;
        }
        PackageManager manager = getPackageManager(context);
        if (null == manager) {
            return null;
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = manager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == packageInfo) {
            return null;
        }
        Signature signature = packageInfo.signatures[0];
        return signature.toCharsString();
    }

    /***
     * */
    @SuppressLint("PackageManagerGetSignatures")
    public static String getPackageSignature(@NonNull Context context) {
        String packageName = context.getPackageName();
        if (0 >= packageName.length()) {
            return null;
        }
        PackageManager manager = getPackageManager(context);
        if (null == manager) {
            return null;
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = manager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == packageInfo) {
            return null;
        }
        Signature signature = packageInfo.signatures[0];
        return signature.toCharsString();
    }

    /***
     * */
    @SuppressLint("PackageManagerGetSignatures")
    public static String getPackageSHA1(@NonNull Context context, @NonNull String packageName) {
        if (0 >= packageName.length()) {
            return null;
        }
        PackageManager manager = getPackageManager(context);
        if (null == manager) {
            return null;
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = manager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == packageInfo) {
            return null;
        }
        Signature signature = packageInfo.signatures[0];
        return sha1Encrypt(signature.toByteArray()).toUpperCase();
    }

    /***
     * */
    @SuppressLint("PackageManagerGetSignatures")
    public static String getPackageSHA1(@NonNull Context context) {
        String packageName = context.getPackageName();
        if (0 >= packageName.length()) {
            return null;
        }
        PackageManager manager = getPackageManager(context);
        if (null == manager) {
            return null;
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = manager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == packageInfo) {
            return null;
        }
        Signature signature = packageInfo.signatures[0];
        return sha1Encrypt(signature.toByteArray()).toUpperCase();
    }

    /***
     * */
    public static boolean isAvailableLocal(@NonNull Context context, @NonNull String packageName) {
        if (0 >= packageName.trim().length()) {
            return false;
        }
        PackageManager packageManager = getPackageManager(context);
        List<PackageInfo> installedList = packageManager.getInstalledPackages(0);
        if (null == installedList || 0 >= installedList.size()) {
            return false;
        }
        for (PackageInfo packageInfo : installedList) {
            if (null != packageInfo && packageName.equals(packageInfo.packageName)) {
                return true;
            }
        }
        return false;
    }

    /***
     * Return PackageManager instance to find global package information.
     * */
    private static PackageManager getPackageManager(@NonNull Context context) {
        return context.getPackageManager();
    }

    /***
     * Retrieve overall information about an application package that is
     * installed on the system.
     * */
    private static PackageInfo getPackageInfo(@NonNull Context context, @NonNull String packageName) {
        PackageManager manager = getPackageManager(context);
        if (null == manager) {
            return null;
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = manager.getPackageInfo(packageName, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    /**
     * */
    private static String sha1Encrypt(byte[] byteArray) {
        if (null == byteArray || 0 >= byteArray.length) {
            return null;
        }
        InputStream input = new ByteArrayInputStream(byteArray);
        CertificateFactory certificateFactory = null;
        try {
            certificateFactory = CertificateFactory.getInstance("X509");
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        if (null == certificateFactory) {
            return null;
        }
        X509Certificate certificate = null;
        try {
            certificate = (X509Certificate) certificateFactory.generateCertificate(input);
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        if (null == certificate) {
            return null;
        }
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-1");
            digest.update(certificate.getEncoded());
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (null == digest) {
            return null;
        }
        byte[] bytes = digest.digest();
        if (null == bytes || 0 >= bytes.length) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xFF & aByte);
            if (1 == hex.length()) {
                builder.append("0").append(hex);
            } else {
                builder.append(hex);
            }
        }
        return builder.toString();
    }
}
