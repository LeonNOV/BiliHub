package com.leon.biuvideo.utils;

import android.content.Context;

import com.leon.biuvideo.http.DataStoreKey;
import com.leon.biuvideo.http.Quality;

/**
 * @Author Leon
 * @Time 2022/08/25
 * @Desc
 */
public class PreferenceUtils {


    public static Quality getVideoQuality(Context context) {
        String data = DataStoreUtils.INSTANCE.getData(context, DataStoreKey.VIDEO_QUALITY, Quality.Q80.toString());
        return Quality.valueOf("Q" + data);
    }

    public static void setVideoQuality(Context context, Quality quality, String display) {
        DataStoreUtils.INSTANCE.putData(context, DataStoreKey.VIDEO_QUALITY, quality.toString());
        DataStoreUtils.INSTANCE.putData(context, DataStoreKey.VIDEO_QUALITY_DISPLAY, display);
    }

    public static int getLiveQuality(Context context) {
        return DataStoreUtils.INSTANCE.getData(context, DataStoreKey.LIVE_QUALITY, 10000);
    }

    public static void setLiveQuality(Context context, int qn, String display) {
        DataStoreUtils.INSTANCE.putData(context, DataStoreKey.LIVE_QUALITY, qn);
        DataStoreUtils.INSTANCE.putData(context, DataStoreKey.LIVE_QUALITY_DISPLAY, display);
    }
}
