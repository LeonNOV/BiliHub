package com.leon.bilihub.utils;

import android.content.Context;

import com.leon.bilihub.http.DataStoreKey;
import com.leon.bilihub.http.Quality;

/**
 * @Author Leon
 * @Time 2022/08/25
 * @Desc
 */
public class PreferenceUtils {
    private static final String DEF_COOKIE = "buvid3=3900CA9F-18A2-CEA5-01E3-C79734F4212D26051infoc; CURRENT_FNVAL=4048; _uuid=38DEF21E-92A8-10F66-65410-5D4333DD122431219infoc; buvid4=17C9AC60-6297-82EC-403C-A20179C4AD2726950-022081210-Ei5K3XdcNIj1gF6v5UJjEA==; rpdid=|(YYRm~u|~J0J'uYY|k~|uk~; sid=7ov0gikx; fingerprint=c7eb6389ec5e657a7d4b0e91e7305cf1; buvid_fp_plain=undefined; buvid_fp=c7eb6389ec5e657a7d4b0e91e7305cf1; LIVE_BUVID=AUTO8616605753357850; i-wanna-go-back=-1; b_ut=7; is-2022-channel=1; PVID=2; nostalgia_conf=-1; innersign=0; b_lsid=3DEC796A_182E25E5347";

    public static String getUid(Context context) {
        return DataStoreUtils.INSTANCE.getData(context, DataStoreKey.UID, "");
    }

    public static void setUid(Context context, String uid) {
        DataStoreUtils.INSTANCE.putData(context, DataStoreKey.UID, uid);
    }

    public static String getCookie(Context context) {
        String result = DataStoreUtils.INSTANCE.getData(context, DataStoreKey.COOKIE, "");
        if ("".equals(result)) {
            result = DEF_COOKIE;
        }
        return result;
    }

    public static void setCookie(Context context, String cookie) {
        DataStoreUtils.INSTANCE.putData(context, DataStoreKey.COOKIE, cookie);
    }

    public static boolean getVipStatus(Context context) {
        return DataStoreUtils.INSTANCE.getData(context, DataStoreKey.VIP_STATUS, false);
    }

    public static void setVipStatus(Context context, boolean vipStatus) {
        DataStoreUtils.INSTANCE.putData(context, DataStoreKey.VIP_STATUS, vipStatus);
    }

    public static boolean getLoginStatus(Context context) {
        return DataStoreUtils.INSTANCE.getData(context, DataStoreKey.LOGIN_STATUS, false);
    }

    public static void setLoginStatus(Context context, boolean status) {
        DataStoreUtils.INSTANCE.putData(context, DataStoreKey.LOGIN_STATUS, status);
    }

    public static Quality getVideoQuality(Context context) {
        String data = DataStoreUtils.INSTANCE.getData(context, DataStoreKey.VIDEO_QUALITY, Quality.Q80.toString());
        return Quality.valueOf("Q" + data);
    }

    public static String getVideoQualityDisplay(Context context) {
        return DataStoreUtils.INSTANCE.getData(context, DataStoreKey.VIDEO_QUALITY_DISPLAY, "1080P 高清");
    }

    public static void setVideoQuality(Context context, Quality quality, String display) {
        DataStoreUtils.INSTANCE.putData(context, DataStoreKey.VIDEO_QUALITY, quality.toString());
        DataStoreUtils.INSTANCE.putData(context, DataStoreKey.VIDEO_QUALITY_DISPLAY, display);
    }

    public static String getLiveQualityDisplay(Context context) {
        return DataStoreUtils.INSTANCE.getData(context, DataStoreKey.LIVE_QUALITY_DISPLAY, "原画");
    }

    public static int getLiveQuality(Context context) {
        return DataStoreUtils.INSTANCE.getData(context, DataStoreKey.LIVE_QUALITY, 10000);
    }

    public static void setLiveQuality(Context context, int qn, String display) {
        DataStoreUtils.INSTANCE.putData(context, DataStoreKey.LIVE_QUALITY, qn);
        DataStoreUtils.INSTANCE.putData(context, DataStoreKey.LIVE_QUALITY_DISPLAY, display);
    }

    public static int getRecommendStyle(Context context) {
        return DataStoreUtils.INSTANCE.getData(context, DataStoreKey.RECOMMEND_STYLE, 1);
    }

    public static void setRecommendStyle(Context context, int column) {
        DataStoreUtils.INSTANCE.putData(context, DataStoreKey.RECOMMEND_STYLE, column);
    }

    public static boolean getImgMode(Context context) {
        return DataStoreUtils.INSTANCE.getData(context, DataStoreKey.IMG_MODE, false);
    }

    public static void setImgMode(Context context, boolean isOriginal) {
        DataStoreUtils.INSTANCE.putData(context, DataStoreKey.IMG_MODE, isOriginal);
    }
}
