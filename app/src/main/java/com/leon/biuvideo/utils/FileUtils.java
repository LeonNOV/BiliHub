package com.leon.biuvideo.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author Leon
 * @Time 2022/07/28
 * @Desc
 */
public class FileUtils {
    /**
     * 获取Assets文件内容
     *
     * @param context   context
     * @param assetsName    assets文件名称
     * @return  内容
     */
    public static String getAssetsContent(Context context, String assetsName) {
        try {
            InputStream inputStream = context.getAssets().open(assetsName);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String temp;
            StringBuilder stringBuilder = new StringBuilder();
            while ((temp = bufferedReader.readLine()) != null) {
                stringBuilder.append(temp);
            }

            bufferedReader.close();
            inputStream.close();

            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
