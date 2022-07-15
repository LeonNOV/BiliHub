package com.leon.biuvideo.http;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @Author Leon
 * @Time 2022/07/05
 * @Desc
 */
public class ApiHelper<T> {
    private final Observable<T> observable;
    private OnResult<T> onResult;

    public ApiHelper(Observable<T> observable) {
        this.observable = observable;
    }

    public void doIt() {
        if (onResult != null) {
            observable
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull T t) {
                            onResult.call(t);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    public ApiHelper<T> setOnResult(OnResult<T> onResult) {
        this.onResult = onResult;

        return this;
    }

    public interface OnResult<T> {
        void call(T t);
    }
}
