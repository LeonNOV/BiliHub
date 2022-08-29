package com.leon.bilihub.http;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/06
 * @Desc
 */
public class RequestData {
    private final @BaseUrl String baseUrl;
    private Map<String, String> headers;

    public RequestData(@BaseUrl String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public RequestData(@BaseUrl String baseUrl, Map<String, String> headers) {
        this.baseUrl = baseUrl;
        this.headers = headers;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
