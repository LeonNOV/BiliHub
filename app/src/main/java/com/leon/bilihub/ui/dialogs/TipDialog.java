package com.leon.bilihub.ui.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AlertDialog;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseActivity.ActivityManager;
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
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setActionStr(String cancelStr, String confirmStr) {
        this.cancelStr = cancelStr;
        this.confirmStr = confirmStr;
    }

    public void setOnActionListener(OnActionListener onActionListener) {
        this.onActionListener = onActionListener;
    }

    /**
     * ???????????????
     *
     * @param context context
     */
    public static void showLoginTipDialog(Context context) {
        TipDialog tipDialog = new TipDialog(context);
        tipDialog.setTitle("?????????");
        tipDialog.setContent("????????????????????????????????????????????????????????????");
        tipDialog.setActionStr("??????", "??????");
        tipDialog.setOnActionListener(action -> {
            if (action) {
                ActivityManager.startWithFinishActivity(context, LoginActivity.class);
            } else {
                ActivityManager.BackPressed();
            }
        });
        tipDialog.show();
    }

    public interface OnActionListener {
        /**
         * action
         *
         * @param action false???cancel???true???confirm
         */
        void onAction(boolean action);
    }
}
