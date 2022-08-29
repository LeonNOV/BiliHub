package com.leon.bilihub.ui.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialog;

import com.leon.bilihub.R;
import com.leon.bilihub.databinding.DialogPictureViewerBinding;

/**
 * @Author Leon
 * @Time 2022/07/29
 * @Desc
 */
public class PictureViewerDialog extends AppCompatDialog {
    private final Context context;

    public PictureViewerDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DialogPictureViewerBinding binding = DialogPictureViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.all.setOnClickListener(v -> {
            Toast.makeText(context, "save all", Toast.LENGTH_SHORT).show();
            dismiss();
        });

        binding.current.setOnClickListener(v -> {
            Toast.makeText(context, "save current", Toast.LENGTH_SHORT).show();
            dismiss();
        });

        Window window = this.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setLayout(context.getResources().getDimensionPixelSize(R.dimen.dialog_width), WindowManager.LayoutParams.WRAP_CONTENT);
            window.setDimAmount(0f);
        }
    }
}
