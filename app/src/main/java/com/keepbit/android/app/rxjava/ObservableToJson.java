package com.keepbit.android.app.rxjava;

import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by CoderMario on 2019-05-15.
 */
class ObservableToJson<T> extends Observable<String> {

    private T t;

    ObservableToJson(T t) {
        this.t = t;
    }

    @Override
    protected void subscribeActual(Observer<? super String> observer) {
        ToJsonDisposable disposable = new ToJsonDisposable(this.t, observer);
        observer.onSubscribe(disposable);
        disposable.run();
    }

    class ToJsonDisposable implements Disposable {

        private final T t;
        private final Observer<? super String> mObserver;

        private boolean mDispose;

        ToJsonDisposable(T t, Observer<? super String> observer) {
            this.t = t;
            this.mObserver = observer;
        }

        void run() {
            String string = new Gson().toJson(this.t);
            dispatchCompleted(mObserver, string);
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
