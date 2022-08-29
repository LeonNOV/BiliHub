package com.leon.bilihub.utils.donation;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.net.URISyntaxException;

/**
 * @Author Leon
 * @Time 2021/5/18
 * @Desc 支付宝捐赠
 */
public class AliDonation {
    private static final String ALIPAY_PACKAGE_NAME = "com.eg.android.AlipayGphone";
    private static final String INTENT_URL_FORMAT = "intent://platformapi/startapp?saId=10000007&clientVersion=3.7.0.0718&qrcode=https%3A%2F%2Fqr.alipay.com%2F{payCode}%3F_s%3Dweb-other&_t=1472443966571#Intent;scheme=alipayqr;package=com.eg.android.AlipayGphone;end";

    /**
     * 打开支付宝
     */
    public static void startAlipayClient(Activity activity, String payCode) {
        startIntentUrl(activity, INTENT_URL_FORMAT.replace("{payCode}", payCode));
    }

    /**
     * 检查是否已安装支付宝
     */
    public static boolean hasInstalledAlipayClient(Context context) {
        PackageManager pm = context.getPackageManager();

        try {
            PackageInfo info = pm.getPackageInfo(ALIPAY_PACKAGE_NAME, 0);
            return info != null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 打开支付宝转账
     */
    public static boolean startIntentUrl(Activity activity, String intentFullUrl) {
        try {
            Intent intent = Intent.parseUri(intentFullUrl, Intent.URI_INTENT_SCHEME);
            activity.startActivity(intent);
            return true;
        } catch (URISyntaxException | ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
