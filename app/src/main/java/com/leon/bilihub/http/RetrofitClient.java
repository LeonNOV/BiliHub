package com.leon.bilihub.http;

import android.content.Context;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.leon.bilihub.utils.PreferenceUtils;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author Leon
 * @Time 2021/10/30
 * @Desc
 */
public class RetrofitClient {
    private Retrofit retrofit;

    public RetrofitClient(@BaseUrl String baseUrl, Context context) {
        this(context, baseUrl, Map.of(HttpApi.COOKIE, PreferenceUtils.getCookie(context),
                "Origin", "https://www.bilibili.com/",
                "User-Agent", RequestData.USER_AGENT), null);
    }

    public RetrofitClient(Context context, String host, Converter.Factory converterFactory) {
        this(context, PreferenceUtils.getProxy(context), Map.of(HttpApi.COOKIE, "buvid3=3900CA9F-18A2-CEA5-01E3-C79734F4212D26051infoc; _uuid=38DEF21E-92A8-10F66-65410-5D4333DD122431219infoc; buvid4=17C9AC60-6297-82EC-403C-A20179C4AD2726950-022081210-Ei5K3XdcNIj1gF6v5UJjEA==; rpdid=|(YYRm~u|~J0J'uYY|k~|uk~; sid=7ov0gikx; fingerprint=c7eb6389ec5e657a7d4b0e91e7305cf1; buvid_fp_plain=undefined; buvid_fp=c7eb6389ec5e657a7d4b0e91e7305cf1; LIVE_BUVID=AUTO8616605753357850; i-wanna-go-back=-1; b_ut=7; is-2022-channel=1; PVID=2; nostalgia_conf=-1; CURRENT_FNVAL=4048; innersign=0; b_lsid=4F5C2BC1_182E3D15CA2",
                "biliHost", host,
                "Origin", "https://www.bilibili.com/",
                "User-Agent", RequestData.USER_AGENT), converterFactory);
    }

    public RetrofitClient(Context context, @BaseUrl String baseUrl, Map<String, String> headers, Converter.Factory converterFactory) {
        if (headers != null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new WbiInterceptor(context))
                    .addInterceptor(chain -> {
                        Request.Builder builder = chain.request().newBuilder();

                        for (Map.Entry<String, String> entry : headers.entrySet()) {
                            builder.addHeader(entry.getKey(), entry.getValue());
                        }

                        return chain.proceed(builder.build());
                    }).build();
            createRetrofitClient(client, baseUrl, converterFactory);
        }

    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory sSLSocketFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()}, new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
            Log.d("WwwW", e.getMessage());
        }

        return sSLSocketFactory;
    }

    private static class TrustAllManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) {

        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) {

        }


        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private void createRetrofitClient(OkHttpClient client, String baseUrl, Converter.Factory converterFactory) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create());

        if (converterFactory != null) {
            builder.addConverterFactory(converterFactory);
        } else {
            builder.addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()));
        }

        this.retrofit = builder.client(client).build();
    }

    public HttpApi getHttpApi() {
        return retrofit.create(HttpApi.class);
    }

    public HttpApi.ApiGet ApiGet() {
        return retrofit.create(HttpApi.ApiGet.class);
    }

    public HttpApi.HttpRaw getHttpRaw() {
        return retrofit.create(HttpApi.HttpRaw.class);
    }

    public PostApi getPostApi() {
        return retrofit.create(PostApi.class);
    }
}
