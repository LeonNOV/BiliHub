package com.leon.bilihub.utils.donation;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author Leon
 * @Time 2021/5/18
 * @Desc 微信捐赠
 */
public class WeChatDonation {
    private static final String TAG = WeChatDonation.class.getSimpleName();
    private static final String TENCENT_PACKAGE_NAME = "com.tencent.mm";
    private static final String TENCENT_ACTIVITY_BIZSHORTCUT = "com.tencent.mm.action.BIZSHORTCUT";
    private static final String TENCENT_EXTRA_ACTIVITY_BIZSHORTCUT = "LauncherUI.From.Scaner.Shortcut";

    /**
     * 微信捐赠
     */
    public static void donateViaWeiXin(Context context, File qrCodeFile) {
        sendPictureStoredBroadcast(context, qrCodeFile);
        openWeChatQrScan(context);
    }

    /**
     * 检查是否已安装微信
     */
    public static boolean hasInstalledWeiXinClient(Context context) {
        PackageManager pm = context.getPackageManager();

        try {
            PackageInfo info = pm.getPackageInfo("com.tencent.mm", 0);
            return info != null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 保存支付码至本地存储中
     */
    public static void saveDonateQrImage2SDCard(@NonNull String qrSavePath, @NonNull Bitmap qrBitmap) {
        File qrFile = new File(qrSavePath);
        if (qrFile.exists()) {
            qrFile.delete();
        }

        File parentFile = qrFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        try {
            FileOutputStream fos = new FileOutputStream(qrFile);
            qrBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通知相册进行扫描并更新图片
     */
    private static void sendPictureStoredBroadcast(Context context, File qrCodeFile) {
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), qrCodeFile.getAbsolutePath(), qrCodeFile.getName(), null);
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "保存QRCode失败", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(qrCodeFile));
        context.sendBroadcast(intent);
    }

    /**
     * 打开微信扫一扫
     */
    private static void openWeChatQrScan(@NonNull Context context) {
        Intent intent = new Intent(TENCENT_ACTIVITY_BIZSHORTCUT);
        intent.setPackage(TENCENT_PACKAGE_NAME);
        intent.putExtra(TENCENT_EXTRA_ACTIVITY_BIZSHORTCUT, true);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "你好像没有安装微信", Toast.LENGTH_SHORT).show();
        }
    }
}
