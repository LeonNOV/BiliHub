package com.leon.bilihub.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import com.leon.bilihub.http.ApiHelper;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.dialogs.TipDialog;

/**
 * @Author Leon
 * @Time 2023/02/28
 * @Desc
 */
public class Utils {
    public static void CheckUpdate(Context context, boolean showToast) {
        HttpApi.ApiGet httpApi = new RetrofitClient(BaseUrl.CONFIG, context).ApiGet();
        new ApiHelper<>(httpApi.getConfig()).setOnResult(config -> {
//            Log.d("WwwW", config.toString());

            try {
                int nowVersionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;

                // 将反代理baseurl保存至preference
                PreferenceUtils.setProxy(context, config.getProxy().getUrl());

                if (config.getVersion().getVersionCode() > nowVersionCode) {
                    TipDialog.ShowUpdateDialog(context, config);
                } else if (showToast) {
                    Toast.makeText(context, "当前版本为最新版本", Toast.LENGTH_SHORT).show();
                }
            } catch (PackageManager.NameNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).doIt();
    }
}
