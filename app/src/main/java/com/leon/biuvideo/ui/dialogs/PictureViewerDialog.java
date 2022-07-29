package com.leon.biuvideo.ui.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDialog;

import com.leon.biuvideo.R;
import com.leon.biuvideo.databinding.DialogPictureViewerBinding;

/**
 * @Author Leon
 * @Time 2022/07/29
 * @Desc
 */
public class PictureViewerDialog extends AppCompatDialog {
    private Context context;

    public PictureViewerDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DialogPictureViewerBinding binding = DialogPictureViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setLayout(context.getResources().getDimensionPixelSize(R.dimen.dialog_width), WindowManager.LayoutParams.WRAP_CONTENT);
            window.setDimAmount(0f);
        }
//        ((View) binding.getRoot().getParent()).setBackgroundColor(context.getColor(android.R.color.transparent));
//        ViewUtils.setRipple(binding.all);
//        ViewUtils.setRipple(binding.current);
    }
}
