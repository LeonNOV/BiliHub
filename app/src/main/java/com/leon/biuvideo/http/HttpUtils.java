package com.leon.biuvideo.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author Leon
 * @Time 2020/10/18
 * @Desc 网络数据请求工具类
 */
public class HttpUtils {
    private final String url;
    private final Headers headers;
    private final Map<String, String> params;

    public HttpUtils(String url, Map<String, String> params) {
        this.url = url;
        this.params = params;
        this.headers = Headers.of(getHeaders());
    }

    public HttpUtils(String url, Headers headers, Map<String, String> params) {
        this.url = url;
        this.headers = headers;
        this.params = params;
    }

    /**
     * 短链解析
     *
     * @return  返回短链解析结果
     */
    public String parseShortUrl() {
        try {
            return getInstance().request().url().url().toURI().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 向接口请求数据/获取网页源码

     * @return  返回响应数据
     */
    public String getData() {
        try {
            return getInstance().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @return  获取响应体字节码数据
     */
    public byte[] getByteArray () {
        try {
            return getInstance().body().bytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取实例对象
     *
     * @return  返回Response对象
     */
    private Response getInstance () {
        Request request;
        OkHttpClient okHttpClient = new OkHttpClient();

        Request.Builder requestBuilder = new Request.Builder();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();

        //添加参数
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }

        try {
            if (headers != null) {
                requestBuilder.headers(headers);
            } else {
                requestBuilder.headers(Headers.of(getHeaders()));
            }

            request = requestBuilder.url(urlBuilder.build()).get().build();

            Call call = okHttpClient.newCall(request);
            Response response = call.execute();

            //判断是否响应成功
            if (response.code() == HttpURLConnection.HTTP_OK) {
                return response;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取接口响应体
     *
     * @param path  接口地址
     * @param params    参数
     * @return  将响应体已JSONObject对象的形式返回
     */
    public static JSONObject getResponse(String path, Map<String, String> params) {
        String response = new HttpUtils(path, params).getData();

        return JSON.parseObject(response);
    }

    /**
     * 获取接口响应体
     *
     * @param path  接口地址
     * @param params    参数
     * @return  将响应体已JSONObject对象的形式返回
     */
    public static JSONObject getResponse(String path, Headers headers, Map<String, String> params) {
        String response = new HttpUtils(path, headers, params).getData();

        return JSON.parseObject(response);
    }

    /**
     * 解决视频播放失败的问题
     *
     * @param isBangumi 是否为番剧
     * @param id    视频bvid/番剧选集epId
     * @return  视频播放请求头
     */
    public static HashMap<String, String> getVideoPlayHeaders (boolean isBangumi, String id) {
        HashMap<String, String> headers = getHeaders();

        if (isBangumi) {
            headers.replace("Referer", "https://www.bilibili.com/bangumi/play/ep" + id);
        } else {
            headers.replace("Referer", "https://www.bilibili.com/video/" + id);
        }

        return headers;
    }

    /**
     * 设置头信息
     *
     * @return  返回头信息
     */
    public static HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<>(3);
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36 Edg/86.0.622.51");
        headers.put("Connection", "keep-alive");
        headers.put("Referer", "https://www.bilibili.com/");

        return headers;
    }
}
