package com.leon.bilihub.ui.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.leon.bilihub.R;
import com.leon.bilihub.databinding.DialogEditorBinding;

/**
 * @Author Leon
 * @Time 2022/09/15
 * @Desc
 */
public class EditorDialog extends AlertDialog {
    private final String hint;
    private final OnConfirmCallback onConfirmCallback;

    private DialogEditorBinding binding;

    protected EditorDialog(@NonNull Context context, String hint, OnConfirmCallback onConfirmCallback) {
        super(context);
        this.hint = hint;

        this.onConfirmCallback = onConfirmCallback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DialogEditorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.editor.setHint(hint);

        Window window = getWindow();
        window.setWindowAnimations(R.style.paning_anim_style);
        window.setBackgroundDrawableResource(android.R.color.transparent);

        initView();
    }

    private void initView() {
        binding.confirm.setOnClickListener(v -> {
            onConfirmCallback.onBack(binding.editor.getText().toString());
            dismiss();
        });
    }

    public interface OnConfirmCallback {
        /**
         * 回调输入框内容
         *
         * @param content content
         */
        void onBack(String content);
    }
}
