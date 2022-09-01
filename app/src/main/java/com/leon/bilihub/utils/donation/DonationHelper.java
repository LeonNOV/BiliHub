package com.leon.bilihub.utils.donation;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.leon.bilihub.R;
import com.leon.bilihub.utils.ViewUtils;

import java.io.File;
import java.io.InputStream;

/**
 * @Author Leon
 * @Time 2022/09/01
 * @Desc
 */
public class DonationHelper {
    public static void Ali(Context context) {
        boolean status = AliDonation.hasInstalledAlipayClient(context);
        if (status) {
            AliDonation.startAlipayClient(ViewUtils.scanForActivity(context), "a6x01339a0oa09cw0ds024b");
        }
    }

    public static void WeChat(Context context) {
        InputStream weChatPayQrcode = context.getResources().openRawResource(R.raw.we_chat_pay_qrcode);

        File resourcesPath = context.getExternalFilesDir("resources");
        File picturesPath = new File(resourcesPath, "pictures");

        if (!picturesPath.exists()) {
            picturesPath.mkdirs();
        }

        File qrCodeFile = new File(picturesPath, "WeChatDonation.png");
        WeChatDonation.saveDonateQrImage2SDCard(qrCodeFile.getAbsolutePath(), BitmapFactory.decodeStream(weChatPayQrcode));
        Toast.makeText(context, "正在跳转中，请不要进行任何操作", Toast.LENGTH_SHORT).show();
        WeChatDonation.donateViaWeiXin(context, qrCodeFile);
    }
}
