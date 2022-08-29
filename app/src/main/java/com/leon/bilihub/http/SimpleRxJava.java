package com.leon.bilihub.http;

import androidx.annotation.NonNull;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @Author Leon
 * @Time 2021/10/02
 * @Desc 对RxJava的简单封装
 */
public class SimpleRxJava<T> {
    private SimpleRxJavaCallback<T> simpleRxJavaCallback = null;

    public void setSimpleRxJavaCallback(SimpleRxJavaCallback<T> simpleRxJavaCallback) {
        this.simpleRxJavaCallback = simpleRxJavaCallback;
    }

    public void create() {
        Observable.create((ObservableOnSubscribe<T>) emitter -> {
            if (simpleRxJavaCallback != null) {
                simpleRxJavaCallback.subscribe(emitter);
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull T t) {
                        if (simpleRxJavaCallback != null) {
                            simpleRxJavaCallback.onNext(t);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (simpleRxJavaCallback != null) {
                            simpleRxJavaCallback.onError(e);
                        }

                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        if (simpleRxJavaCallback != null) {
                            simpleRxJavaCallback.onComplete();
                        }
                    }
                });
    }

    public interface SimpleRxJavaCallback<T> {
        void subscribe(@NonNull ObservableEmitter<T> emitter);

        void onNext(@NonNull T t);

        void onError(Throwable throwable);

        void onComplete();
    }
}
