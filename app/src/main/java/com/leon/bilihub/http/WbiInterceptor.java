package com.leon.bilihub.http;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.leon.bilihub.utils.ValueUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author Leon
 * @Time 2023/10/20
 * @Desc
 */
public class WbiInterceptor implements Interceptor {
    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";
    private final Context context;

    public WbiInterceptor(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();

        String scheme = request.url().scheme();

        if (("https".equals(scheme) || "http".equals(scheme)) && request.url().url().getPath().contains("/wbi")) {
            Request.Builder requestBuilder = request.newBuilder();
            HttpUrl.Builder urlBuilder = request.url().newBuilder();

            if (METHOD_GET.equals(request.method())) {
                Set<String> paramKey = request.url().queryParameterNames();

                HashMap<String, String> map = new HashMap<>();
                for (String key : paramKey) {
                    map.put(key, request.url().queryParameter(key));
                }

                map.put("wts", String.valueOf(System.currentTimeMillis() / 1000));
                map.put("w_rid", ValueUtils.genWbi(context, map));

                for (Map.Entry<String, String> entry : map.entrySet()) {
                    urlBuilder.setEncodedQueryParameter(entry.getKey(), entry.getValue());
                }

                requestBuilder.url(urlBuilder.build());
            } else if (METHOD_POST.equals(request.method())) {
                // FormBody和url不太一样，若需添加公共参数，需要新建一个构造器
                FormBody.Builder bodyBuilder = new FormBody.Builder();
                // 把已有的post参数添加到新的构造器
                if (request.body() instanceof FormBody) {
                    FormBody formBody = (FormBody) request.body();
                    for (int i = 0; i < formBody.size(); i++) {
                        bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
                    }
                }

                // 这里可以添加一些公共post参数
                bodyBuilder.addEncoded("key_xxx", "value_xxx");
                FormBody newBody = bodyBuilder.build();

                // 打印所有post参数
                for (int i = 0; i < newBody.size(); i++) {
                    Log.d("TEST", newBody.name(i) + " " + newBody.value(i));
                }

                // 将最终的表单body填充到request中
                requestBuilder.post(newBody);
            }

            return chain.proceed(requestBuilder.build());
        }

        return chain.proceed(request);
    }
}
