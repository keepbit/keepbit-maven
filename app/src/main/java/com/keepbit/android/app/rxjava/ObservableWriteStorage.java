package com.keepbit.android.app.rxjava;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by CoderMario on 2019-05-15.
 */
class ObservableWriteStorage extends Observable<Long> {

    private final Context mContext;
    private final String mString;
    private final String mFileName;
    private final int mMode;

    ObservableWriteStorage(Context context, String string, String fileName, int mode) {
        this.mContext = context;
        this.mString = string;
        this.mFileName = fileName;
        this.mMode = mode;
    }

    @Override
    protected void subscribeActual(Observer<? super Long> observer) {
        WriteStorageDisposable disposable = new WriteStorageDisposable(mContext, mString, mFileName, mMode, observer);
        observer.onSubscribe(disposable);
        disposable.run();
    }

    class WriteStorageDisposable implements Disposable {

        private final Context mContext;
        private final String mString;
        private final String mFileName;
        private final int mMode;
        private final Observer<? super Long> mObserver;

        private boolean mDispose;

        WriteStorageDisposable(Context context, String string, String fileName, int mode, Observer<? super Long> observer) {
            this.mContext = context;
            this.mString = string;
            this.mFileName = fileName;
            this.mMode = mode;
            this.mObserver = observer;
        }

        void run() {
            FileOutputStream outputStream = null;
            OutputStreamWriter streamWriter = null;
            BufferedWriter bufferedWriter = null;
            try {
                outputStream = mContext.openFileOutput(mFileName, mMode);
                streamWriter = new OutputStreamWriter(outputStream);
                bufferedWriter = new BufferedWriter(streamWriter);
                bufferedWriter.write(mString);
                dispatchCompleted(mObserver, 1L);
            } catch (FileNotFoundException exception) {
                dispatchError(mObserver, exception);
            } catch (IOException exception) {
                dispatchError(mObserver, exception);
            } finally {
                close(bufferedWriter);
                close(streamWriter);
                close(outputStream);
            }
        }

        private void dispatchError(Observer<? super Long> observer, Exception exception) {
            if (this.mDispose) {
                RxJavaPlugins.onError(exception);
            } else if (null != observer) {
                observer.onError(exception);
            }
        }

        private void dispatchCompleted(Observer<? super Long> observer, Long aLong) {
            if (!this.mDispose && null != observer) {
                observer.onNext(aLong);
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
