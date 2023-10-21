package com.leon.bilihub.ui.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.leon.bilihub.R;
import com.leon.bilihub.databinding.DialogDonationBinding;
import com.leon.bilihub.utils.donation.DonationHelper;

import org.jetbrains.annotations.NotNull;

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

        FrameLayout bottom = findViewById(R.id.design_bottom_sheet);
        if (bottom != null) {
            bottom.setBackgroundResource(android.R.color.transparent);
        }

        initView();
    }

    private void initView() {
        binding.aliPay.setOnClickListener(v -> DonationHelper.Ali(getContext()));
        binding.weChatPay.setOnClickListener(v -> DonationHelper.WeChat(getContext()));
    }
}