package com.leon.bilihub.http;

import android.content.Context;

import com.leon.bilihub.utils.PreferenceUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/06
 * @Desc
 */
public class RequestData {
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36 Edg/105.0.1343.50";

    private final @BaseUrl String baseUrl;
    private Map<String, String> headers;

    public RequestData(@BaseUrl String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public RequestData(@BaseUrl String baseUrl, Map<String, String> headers) {
        this.baseUrl = baseUrl;
        this.headers = headers;
    }

    public RequestData(@BaseUrl String baseUrl, Context context) {
        this.baseUrl = baseUrl;
        headers = Map.of(HttpApi.COOKIE, PreferenceUtils.getCookie(context),
                "Origin", "https://www.bilibili.com/",
                "User-Agent", USER_AGENT);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
