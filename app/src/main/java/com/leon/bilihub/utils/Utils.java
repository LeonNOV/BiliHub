package com.leon.bilihub.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.GsonBuilder;
import com.leon.bilihub.beans.VersionTags;
import com.leon.bilihub.http.RequestData;
import com.leon.bilihub.ui.dialogs.TipDialog;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author Leon
 * @Time 2023/02/28
 * @Desc
 */
public class Utils {
    public static void CheckUpdate(Context context, boolean showToast) {
        Handler handler = new Handler(Looper.getMainLooper(), msg -> {
            if (msg.what == 1) {
                TipDialog.ShowUpdateDialog(context, msg.getData().getString("version"));
            } else if (msg.what == 0 && showToast) {
                Toast.makeText(context, "当前版本为最新版本", Toast.LENGTH_SHORT).show();
            }

            return true;
        });

        OkHttpClient build = new OkHttpClient.Builder().build();
        build.newCall(new Request(HttpUrl.get("https://gitcode.net/qq_36318722/bilihub/refs?sort=updated_desc"), "GET", Headers.of("User-Agent", RequestData.USER_AGENT), null, Map.of())).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                VersionTags versionTags = new GsonBuilder().setLenient().create().fromJson(response.body().string(), VersionTags.class);
                try {
                    String curVersion = "v" + context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                    Message message = new Message();
                    if (!curVersion.equals(versionTags.getTags().getFirst())) {
                        Bundle bundle = new Bundle();
                        bundle.putString("version", versionTags.getTags().getFirst());

                        message.what = 1;
                        message.setData(bundle);
                    } else {
                        message.what = 0;
                    }

                    handler.sendMessage(message);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
