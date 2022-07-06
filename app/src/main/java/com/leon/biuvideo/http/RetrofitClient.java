package com.leon.biuvideo.http;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
//    private static RetrofitClient instance;
    private Retrofit retrofit;

    public RetrofitClient(@BaseUrl String baseUrl) {
        createRetrofitClient(getClient(), baseUrl);
    }

    public RetrofitClient(@BaseUrl String baseUrl, Map<String, String> headers) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request.Builder builder = chain.request().newBuilder();
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }

            return chain.proceed(builder.build());
        }).build();

        createRetrofitClient(client, baseUrl);
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

    /**
     * 使用单例创建
     *
     * @return RetrofitClient
     */
//    public static RetrofitClient getInstance() {
//        if (instance == null) {
//            instance = new RetrofitClient();
//        }
//        return instance;
//    }

    /**
     * 不使用单例创建，需指定Header内容
     *
     * @param headers headers
     * @return RetrofitClient
     */
//    public static RetrofitClient getInstance(Map<String, String> headers) {
//        return new RetrofitClient(headers);
//    }

    public HttpApi getHttpApi() {
        return retrofit.create(HttpApi.class);
    }
}
