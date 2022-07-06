package com.leon.biuvideo.base.baseActivity;

import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.RequestData;
import com.leon.biuvideo.http.RetrofitClient;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

/**
 * @Author Leon
 * @Time 2022/07/06
 * @Desc
 */
public abstract class AsyncHttpActivity<V extends ViewBinding, P extends Parcelable> extends BaseActivity<V> {
    protected abstract RequestData setRequestData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RetrofitClient retrofitClient;

        RequestData requestData = setRequestData();

        if (requestData.getHeaders() != null) {
            retrofitClient = new RetrofitClient(requestData.getBaseUrl(), requestData.getHeaders());
        } else {
            retrofitClient = new RetrofitClient(requestData.getBaseUrl());
        }

        Observable<P> observable = createObservable(retrofitClient);

        async(new ApiHelper<>(observable));
    }

    /**
     * 创建{@link Observable<P>}实例
     *
     * @return  observable
     */
    protected abstract Observable<P> createObservable(RetrofitClient retrofitClient);

    /**
     * 异步
     *
     * @param apiHelper    apiHelper
     */
    protected abstract void async(ApiHelper<P> apiHelper);
}
