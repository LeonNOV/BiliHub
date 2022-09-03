package com.leon.bilihub.http;

import androidx.annotation.StringDef;

import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @Author Leon
 * @Time 2022/06/30
 * @Desc
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@StringDef(value = {BaseUrl.APP, BaseUrl.API, BaseUrl.SEARCH, BaseUrl.MAIN, BaseUrl.LIVE})
public @interface BaseUrl {

    /**
     * default baseUrl
     */
    String APP = "https://app.bilibili.com/";
    String API = "https://api.bilibili.com/";
    String SEARCH = "https://s.search.bilibili.com/";
    String MAIN = "https://www.bilibili.com/";
    String LIVE = "http://api.live.bilibili.com/";
}
