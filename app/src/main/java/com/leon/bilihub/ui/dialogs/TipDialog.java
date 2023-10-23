package com.leon.bilihub.ui.dialogs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AlertDialog;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseActivity.ActivityManager;
import com.leon.bilihub.beans.Config;
import com.leon.bilihub.databinding.DialogTipBinding;
import com.leon.bilihub.ui.activities.LoginActivity;

import org.jetbrains.annotations.NotNull;

/**
 * @Author Leon
 * @Time 2021/5/16
 * @Desc
 */
public class TipDialog extends AlertDialog {
    private DialogTipBinding binding;
    private OnActionListener onActionListener;

    private String title = "";
    private String content = "";
    private String cancelStr = "";
    private String confirmStr = "";

    public TipDialog(@NotNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DialogTipBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        window.setWindowAnimations(R.style.paning_anim_style);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        setCanceledOnTouchOutside(false);

        initView();
    }

    private void initView() {
        binding.title.setText(title);
        binding.content.setText(content);

        if ("".equals(cancelStr)) {
            binding.confirmB.setText(confirmStr);

            binding.confirmB.setOnClickListener(v -> {
                if (onActionListener != null) {
                    onActionListener.onAction(true);
                }
            });

            binding.confirmB.setVisibility(View.VISIBLE);
        } else {
            binding.cancel.setText(cancelStr);
            binding.confirm.setText(confirmStr);

            binding.cancel.setOnClickListener(v -> {
                if (onActionListener != null) {
                    onActionListener.onAction(false);
                }
            });

            binding.confirm.setOnClickListener(v -> {
                if (onActionListener != null) {
                    onActionListener.onAction(true);
                }
            });

            binding.linearLayout.setVisibility(View.VISIBLE);
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setActionStrBehind(String confirmStr) {
        this.confirmStr = confirmStr;
    }

    public void setActionStr(String cancelStr, String confirmStr) {
        this.cancelStr = cancelStr;
        this.confirmStr = confirmStr;
    }

    public void setOnActionListener(OnActionListener onActionListener) {
        this.onActionListener = onActionListener;
    }

    public static void ShowTipDialog(Context context, String title, String content, OnActionListener onActionListener) {
        TipDialog tipDialog = new TipDialog(context);
        tipDialog.setTitle(title);
        tipDialog.setContent(content);
        tipDialog.setActionStr("返回", "确定");
        tipDialog.setOnActionListener(onActionListener);
        tipDialog.show();
    }

    /**
     * 未登陆提示
     *
     * @param context context
     */
    public static void ShowLoginTipDialog(Context context) {
        TipDialog tipDialog = new TipDialog(context);
        tipDialog.setTitle("未登录");
        tipDialog.setContent("未登录无法获取到用户凭证，是否前去登录？");
        tipDialog.setActionStr("返回", "确定");
        tipDialog.setOnActionListener(action -> {
            if (action) {
                ActivityManager.startWithFinishActivity(context, LoginActivity.class);
            } else {
                ActivityManager.BackPressed();
            }
        });
        tipDialog.show();
    }

    public static void ShowUpdateDialog(Context context, Config config) {
        TipDialog tipDialog = new TipDialog(context);
        tipDialog.setTitle("检测到新版本");
        tipDialog.setContent(String.format("检测到新的版本[%s]已发布，是否前去下载更新？", config.getVersion().getVersionName()));
        tipDialog.setActionStr("取消", "确定");
        tipDialog.setOnActionListener(action -> {
            if (action) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(config.getVersion().getApk()));
                context.startActivity(intent);
            }

            tipDialog.dismiss();
        });
        tipDialog.show();
    }

    public interface OnActionListener {
        /**
         * action
         *
         * @param action false：cancel；true：confirm
         */
        void onAction(boolean action);
    }
}
