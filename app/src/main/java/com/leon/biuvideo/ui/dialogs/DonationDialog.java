package com.leon.biuvideo.ui.dialogs;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.leon.biuvideo.R;
import com.leon.biuvideo.databinding.DialogDonationBinding;
import com.leon.biuvideo.utils.ViewUtils;
import com.leon.biuvideo.utils.donation.AliDonation;
import com.leon.biuvideo.utils.donation.WeChatDonation;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.InputStream;

/**
 * @Author Leon
 * @Time 2021/5/16
 * @Desc 捐赠弹窗
 */
public class DonationDialog extends BottomSheetDialog {
    private DialogDonationBinding binding;

    public DonationDialog(@NotNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DialogDonationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(attributes);

        initView();
    }

    private void initView() {
        binding.aliPay.setOnClickListener(v -> {
            boolean status = AliDonation.hasInstalledAlipayClient(getContext());
            if (status) {
                AliDonation.startAlipayClient(ViewUtils.scanForActivity(getContext()), "a6x01339a0oa09cw0ds024b");
            }
        });

        binding.weChatPay.setOnClickListener(v -> {
            InputStream weChatPayQrcode = getContext().getResources().openRawResource(R.raw.we_chat_pay_qrcode);

            File resourcesPath = getContext().getExternalFilesDir("resources");
            File picturesPath = new File(resourcesPath, "pictures");

            if (!picturesPath.exists()) {
                picturesPath.mkdirs();
            }

            File qrCodeFile = new File(picturesPath, "WeChatDonation.png");
            WeChatDonation.saveDonateQrImage2SDCard(qrCodeFile.getAbsolutePath(), BitmapFactory.decodeStream(weChatPayQrcode));
            Toast.makeText(getContext(), "正在跳转中，请不要进行任何操作", Toast.LENGTH_SHORT).show();
            WeChatDonation.donateViaWeiXin(getContext(), qrCodeFile);
        });
    }
}