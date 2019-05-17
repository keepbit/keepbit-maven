package com.keepbit.android.app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.keepbit.android.app.R;
import com.keepbit.android.app.bean.LocalUserInfo;
import com.keepbit.android.app.rxjava.LocalObservableHelper;

import java.io.File;
import java.io.FileNotFoundException;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by CoderMario on 2019-05-15.
 */
public class Fragment201905150001 extends BaseFragmentV4 {

    private ReadDisposableObserver mReadDisposableObserver;
    private WriteDisposableObserver mWriteDisposableObserver;

    public static Fragment201905150001 newInstance(Bundle arguments) {
        Fragment201905150001 fragment = new Fragment201905150001();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    protected void onInitAllViews(@NonNull View view) {
        view.findViewById(R.id.user_storage_read).setOnClickListener(new OnBtnStorageReadClickListener());
        view.findViewById(R.id.user_storage_write).setOnClickListener(new OnBtnStorageWriteClickListener());
    }

    @Override
    protected void onInitAllDatum() {
        mReadDisposableObserver = new ReadDisposableObserver();
        mWriteDisposableObserver = new WriteDisposableObserver();

        File cacheFile = getContext().getExternalCacheDir();
        Log.e(" ******* CoderMario ", String.format("cacheFile = %s", cacheFile.getAbsolutePath()));
        File cacheDir = getContext().getCacheDir();
        Log.e(" ******* CoderMario ", String.format("cacheDir = %s", cacheDir));
    }

    private class OnBtnStorageWriteClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            LocalUserInfo userInfo = new LocalUserInfo();
            userInfo.setName("KeepBit-Mario");
            userInfo.setAge(24);
            userInfo.setAvatar("http://bmob-cdn-16431.b0.upaiyun.com/2018/12/27/9ab0226640986d47809b604e6a02cb33.png");
            userInfo.setPlatform(1);
            userInfo.setGender(1);
            userInfo.setUid(1);
            userInfo.setSignature("这个人很懒，什么都没有留下");

            mWriteDisposableObserver = new WriteDisposableObserver();
            LocalObservableHelper.toJson(userInfo).flatMap(new Function<String, ObservableSource<Long>>() {

                @Override
                public ObservableSource<Long> apply(String s) throws Exception {
                    return LocalObservableHelper.writeStorage(getContext(), s, "user.storage", Context.MODE_PRIVATE);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(mWriteDisposableObserver);
        }
    }

    private class OnBtnStorageReadClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            mReadDisposableObserver = new ReadDisposableObserver();
            LocalObservableHelper.readStorage(getContext(), "user.storage").flatMap(new Function<String, ObservableSource<LocalUserInfo>>() {

                @Override
                public ObservableSource<LocalUserInfo> apply(String string) throws Exception {
                    return LocalObservableHelper.fromJson(string, LocalUserInfo.class);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(mReadDisposableObserver);
        }
    }

    private class WriteDisposableObserver extends DisposableObserver<Long> {

        @Override
        public void onStart() {
            Log.e(" ******* CoderMario ", String.format("%s", "用户个人信息写入开始"));
        }

        @Override
        public void onNext(Long aLong) {
            //
        }

        @Override
        public void onError(Throwable throwable) {
            Log.e(" ******* CoderMario ", String.format("%s", "用户个人信息写入失败"));
        }

        @Override
        public void onComplete() {
            Log.e(" ******* CoderMario ", String.format("%s", "用户个人信息写入完成"));
        }
    }

    private class ReadDisposableObserver extends DisposableObserver<LocalUserInfo> {

        @Override
        public void onStart() {
            Log.e(" ******* CoderMario ", String.format("%s", "用户个人信息读取开始"));
        }

        @Override
        public void onNext(LocalUserInfo userInfo) {
            Log.e(" ******* CoderMario ", String.format("userInfo: %s", userInfo.toString()));
        }

        @Override
        public void onError(Throwable throwable) {
            if (throwable instanceof FileNotFoundException) {
                Log.e(" ******* CoderMario ", String.format("%s", "用户个人信息读取失败：文件不存在"));
            } else {
                Log.e(" ******* CoderMario ", String.format("%s", "用户个人信息读取失败：未知"));
            }
        }

        @Override
        public void onComplete() {
            Log.e(" ******* CoderMario ", String.format("%s", "用户个人信息读取完成"));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_201905150001;
    }
}
