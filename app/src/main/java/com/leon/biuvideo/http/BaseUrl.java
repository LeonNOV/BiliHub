package com.leon.biuvideo.http;

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
@StringDef(value = {BaseUrl.API, BaseUrl.SEARCH, BaseUrl.MAIN})
public @interface BaseUrl {

    /**
     * default baseUrl
     */
    String API = "https://api.bilibili.com/";
    String SEARCH = "https://s.search.bilibili.com/";
    String MAIN = "https://www.bilibili.com/";
}
