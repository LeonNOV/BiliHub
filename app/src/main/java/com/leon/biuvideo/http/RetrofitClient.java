package com.leon.biuvideo.http;

import android.content.Context;

import androidx.annotation.NonNull;

import com.leon.biuvideo.utils.PreferenceUtils;

import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author Leon
 * @Time 2021/10/30
 * @Desc
 *
 */
public class RetrofitClient {
    private Retrofit retrofit;

    public RetrofitClient(@BaseUrl String baseUrl) {
        createRetrofitClient(getClient(), baseUrl);
    }

    public RetrofitClient(@BaseUrl String baseUrl, Context context) {
        this(baseUrl, Map.of(HttpApi.COOKIE, PreferenceUtils.getCookie(context)));
    }

    public RetrofitClient(@BaseUrl String baseUrl, Map<String, String> headers) {
        if (headers != null) {
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
                Request.Builder builder = chain.request().newBuilder();
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    builder.addHeader(entry.getKey(), entry.getValue());
                }

                return chain.proceed(builder.build());
            }).build();
            createRetrofitClient(client, baseUrl);
        }

    }

    private void createRetrofitClient(OkHttpClient client, String baseUrl) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @NonNull
    private OkHttpClient getClient() {
        return new OkHttpClient.Builder().build();
    }

    public HttpApi getHttpApi() {
        return retrofit.create(HttpApi.class);
    }

    public HttpApi.HttpRaw getHttpRaw() {
        return retrofit.create(HttpApi.HttpRaw.class);
    }
}
