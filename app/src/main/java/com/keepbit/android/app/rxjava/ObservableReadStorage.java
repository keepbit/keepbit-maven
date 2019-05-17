package com.keepbit.android.app.rxjava;

import android.content.Context;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by CoderMario on 2019-05-15.
 */
class ObservableReadStorage extends Observable<String> {

    private Context mContext;
    private String mFileName;

    ObservableReadStorage(Context context, String fileName) {
        this.mContext = context;
        this.mFileName = fileName;
    }

    @Override
    protected void subscribeActual(Observer<? super String> observer) {
        ReadStorageFromJsonDisposable disposable = new ReadStorageFromJsonDisposable(mContext, mFileName, observer);
        observer.onSubscribe(disposable);
        disposable.run();
    }

    class ReadStorageFromJsonDisposable implements Disposable {

        private final Context mContext;
        private final String mFileName;
        private final Observer<? super String> mObserver;

        private boolean mDispose;

        ReadStorageFromJsonDisposable(Context context, String fileName, Observer<? super String> observer) {
            this.mContext = context;
            this.mFileName = fileName;
            this.mObserver = observer;
        }

        void run() {
            FileInputStream inputStream = null;
            InputStreamReader streamReader = null;
            BufferedReader bufferedReader = null;
            try {
                inputStream = this.mContext.openFileInput(this.mFileName);
                streamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while (null != (line = bufferedReader.readLine())) {
                    stringBuilder.append(line);
                }
                dispatchCompleted(mObserver, stringBuilder.toString());
            } catch (FileNotFoundException exception) {
                dispatchError(mObserver, exception);
            } catch (IOException exception) {
                dispatchError(mObserver, exception);
            } finally {
                close(bufferedReader);
                close(streamReader);
                close(inputStream);
            }
        }

        private void dispatchError(Observer<? super String> observer, Exception exception) {
            if (this.mDispose) {
                RxJavaPlugins.onError(exception);
            } else if (null != observer) {
                observer.onError(exception);
            }
        }

        private void dispatchCompleted(Observer<? super String> observer, String string) {
            if (!this.mDispose && null != observer) {
                observer.onNext(string);
                observer.onComplete();
            }
        }

        private void close(Closeable closeable) {
            if (null == closeable) {
                return;
            }
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void dispose() {
            this.mDispose = true;
        }

        @Override
        public boolean isDisposed() {
            return this.mDispose;
        }
    }
}
