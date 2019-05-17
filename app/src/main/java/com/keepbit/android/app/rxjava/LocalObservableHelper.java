package com.keepbit.android.app.rxjava;

import android.content.Context;

import io.reactivex.Observable;

/**
 * Created by CoderMario on 2019-05-15.
 */
public class LocalObservableHelper {

    /**
     * */
    public static <T> Observable<T> fromJson(String json, Class<T> tClass) {
        return new ObservableFromJson<T>(json, tClass);
    }

    /**
     * */
    public static <T> Observable<String> toJson(T t) {
        return new ObservableToJson<>(t);
    }

    /**
     * */
    public static Observable<String> readStorage(Context context, String fileName) {
        return new ObservableReadStorage(context, fileName);
    }

    /**
     * */
    public static Observable<Long> writeStorage(Context context, String content, String fileName, int mode) {
        return new ObservableWriteStorage(context, content, fileName, mode);
    }
}
