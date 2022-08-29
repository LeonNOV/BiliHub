package com.leon.bilihub.http;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author Leon
 * @Time 2022/07/05
 * @Desc
 */
public class ApiHelper<T> {
    private Observable<T> observable;
    private Call<T> call;

    private OnResult<T> onResult;

    public ApiHelper(Observable<T> observable) {
        this.observable = observable;
    }

    public ApiHelper(Call<T> call) {
        this.call = call;
    }

    public void doIt() {
        if (onResult != null) {
            if (observable != null) {
                observable
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull T t) {
                                try {
                                    onResult.call(t);
                                } catch (IOException e) {
                                    onError(e);
                                }
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            } else {
                call.enqueue(new Callback<>() {
                    @Override
                    public void onResponse(@NotNull Call<T> call, @NotNull Response<T> response) {
                        try {
                            onResult.call(response.body());
                        } catch (IOException e) {
                            onFailure(call, e);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<T> call, @NotNull Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        }
    }

    public ApiHelper<T> setOnResult(OnResult<T> onResult) {
        this.onResult = onResult;

        return this;
    }

    public interface OnResult<T> {
        /**
         * 响应回调
         *
         * @param t t
         * @throws IOException  error
         */
        void call(T t) throws IOException;
    }
}
