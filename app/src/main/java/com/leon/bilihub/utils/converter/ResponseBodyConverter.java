package com.leon.bilihub.utils.converter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @Author Leon
 * @Time 2023/05/03
 * @Desc
 */
public class ResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;
    private final ConverterFactory.ConverterMiddleware converterMiddleware;

    public ResponseBodyConverter(TypeAdapter<T> adapter, ConverterFactory.ConverterMiddleware converterMiddleware) {
        this.adapter = adapter;
        this.converterMiddleware = converterMiddleware;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JsonObject jsonObject = JsonParser.parseString(value.string()).getAsJsonObject();
        return adapter.fromJsonTree(converterMiddleware.process(jsonObject));
    }
}
