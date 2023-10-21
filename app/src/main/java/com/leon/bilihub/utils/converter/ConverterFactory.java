package com.leon.bilihub.utils.converter;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @Author Leon
 * @Time 2023/05/03
 * @Desc
 */
public class ConverterFactory extends Converter.Factory {
    private final Gson gson = new GsonBuilder().setLenient().create();

    private ConverterMiddleware converterMiddleware;

    public void setConverterMiddleware(ConverterMiddleware converterMiddleware) {
        this.converterMiddleware = converterMiddleware;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(@NonNull Type type, @NonNull Annotation[] annotations, @NonNull Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new ResponseBodyConverter<>(adapter, converterMiddleware);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(@NonNull Type type, @NonNull Annotation[] parameterAnnotations,
                                                          @NonNull Annotation[] methodAnnotations, @NonNull Retrofit retrofit) {

        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new RequestBodyConverter<>(gson, adapter);
    }

    public interface ConverterMiddleware {
        /**
         * 处理通过代理获取的数据
         *
         * @param jsonObject jsonObject
         * @return json
         */
        JsonElement process(JsonObject jsonObject);
    }
}
