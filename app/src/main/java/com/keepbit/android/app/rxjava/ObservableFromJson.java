package com.keepbit.android.app.rxjava;

import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by CoderMario on 2019-05-15.
 */
class ObservableFromJson<T> extends Observable<T> {

    final String mJson;
    final Class<T> tClass;

    ObservableFromJson(String json, Class<T> tClass) {
        this.mJson = json;
        this.tClass = tClass;
    }

    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        FromJsonDisposable disposable = new FromJsonDisposable<T>(mJson, tClass, observer);
        observer.onSubscribe(disposable);
        disposable.run();
    }

    final class FromJsonDisposable<T> implements Disposable {

        final String mJson;
        final Class<T> tClass;
        private Observer<? super T> mObserver;

        private boolean mDispose;

        FromJsonDisposable(String json, Class<T> tClass, Observer<? super T> observer) {
            this.mJson = json;
            this.tClass = tClass;
            this.mObserver = observer;
        }

        @Override
        public void dispose() {
            this.mDispose = true;
        }

        @Override
        public boolean isDisposed() {
            return this.mDispose;
        }

        void run() {
            try {
                T t = (T) new Gson().fromJson(mJson, tClass);
                dispatchCompleted(this.mObserver, t);
            } catch (Exception exception) {
                dispatchError(this.mObserver, exception);
            }
        }

        void dispatchError(Observer<? super T> observer, Exception exception) {
            if (this.mDispose) {
                RxJavaPlugins.onError(exception);
            } else if (null != observer) {
                observer.onError(exception);
            }
        }

        void dispatchCompleted(Observer<? super T> observer, T t) {
            if (!this.mDispose && null != observer) {
                observer.onNext(t);
                observer.onComplete();
            }
        }
    }
}
