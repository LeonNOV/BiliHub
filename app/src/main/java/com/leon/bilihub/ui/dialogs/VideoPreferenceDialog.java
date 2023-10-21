package com.leon.bilihub.ui.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDialog;
import androidx.viewbinding.ViewBinding;

import com.leon.bilihub.R;
import com.leon.bilihub.databinding.DialogVideoParameterBinding;

/**
 * @Author Leon
 * @Time 2023/05/06
 * @Desc
 */
public abstract class VideoPreferenceDialog extends AppCompatDialog {
    protected Context context;
    protected DialogVideoParameterBinding binding;

    public VideoPreferenceDialog(Context context) {
        super(context);

        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DialogVideoParameterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        if (window != null) {
            window.setGravity(Gravity.END);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setLayout(getContext().getResources().getDimensionPixelSize(R.dimen.dialog_width), WindowManager.LayoutParams.MATCH_PARENT);
            window.setDimAmount(0f);
        }

        setCancelable(true);

        init();
    }

    protected abstract void init();
}
