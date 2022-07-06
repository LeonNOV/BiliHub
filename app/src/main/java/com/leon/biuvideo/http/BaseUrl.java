package com.leon.biuvideo.http;

import androidx.annotation.StringDef;

import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import retrofit2.http.Field;

/**
 * @Author Leon
 * @Time 2022/06/30
 * @Desc
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@StringDef(value = {BaseUrl.API, BaseUrl.SEARCH})
public @interface BaseUrl {

    /**
     * default baseUrl
     */
    String API = "https://api.bilibili.com/";
    String SEARCH = "https://s.search.bilibili.com/";
}
