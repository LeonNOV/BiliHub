package com.leon.bilihub.ui.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AlertDialog;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseActivity.ActivityManager;
import com.leon.bilihub.databinding.DialogLoginBinding;
import com.leon.bilihub.ui.activities.LoginActivity;

import org.jetbrains.annotations.NotNull;

/**
 * @Author Leon
 * @Time 2021/5/16
 * @Desc 关于弹窗
 */
public class LoginDialog extends AlertDialog {
    private DialogLoginBinding binding;

    public LoginDialog(@NotNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DialogLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        window.setWindowAnimations(R.style.paning_anim_style);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        setCanceledOnTouchOutside(false);

        initView();
    }

    private void initView() {
        binding.cancel.setOnClickListener(v -> ActivityManager.BackPressed());
        binding.confirm.setOnClickListener(v -> ActivityManager.startWithFinishActivity(getContext(), LoginActivity.class));
        setTitle("未登录");
        setContent("未登录无法获取到用户凭证，是否前去登录？");
    }

    public void setTitle(String title) {
        binding.title.setVisibility(View.VISIBLE);
        binding.title.setText(title);
    }

    public void setContent(String content) {
        binding.content.setText(content);
    }
}
