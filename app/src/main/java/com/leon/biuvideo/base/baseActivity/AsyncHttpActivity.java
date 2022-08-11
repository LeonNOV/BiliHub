package com.leon.biuvideo.base.baseActivity;

import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.RequestData;
import com.leon.biuvideo.http.RetrofitClient;

import io.reactivex.rxjava3.core.Observable;

/**
 * @Author Leon
 * @Time 2022/07/06
 * @Desc
 */
public abstract class AsyncHttpActivity<V extends ViewBinding, P extends Parcelable> extends BaseActivity<V> {
    /**
     * 设置请求数据
     *
     * @return RequestData
     */
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

        new ApiHelper<>(createObservable(retrofitClient)).setOnResult(this::onAsyncResult).doIt();
    }

    /**
     * 创建{@link Observable<P>}实例
     *
     * @param retrofitClient retrofit
     * @return observable
     */
    protected abstract Observable<P> createObservable(RetrofitClient retrofitClient);

    /**
     * 响应数据
     *
     * @param p 响应数据
     */
    protected abstract void onAsyncResult(P p);
}
