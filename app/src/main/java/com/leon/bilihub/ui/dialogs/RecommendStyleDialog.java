package com.leon.bilihub.ui.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.leon.bilihub.R;
import com.leon.bilihub.databinding.DialogRecommendStyleBinding;
import com.leon.bilihub.model.SettingModel;
import com.leon.bilihub.utils.PreferenceUtils;
import com.leon.bilihub.utils.ViewUtils;

import org.jetbrains.annotations.NotNull;

/**
 * @Author Leon
 * @Time 2021/5/16
 * @Desc
 */
public class RecommendStyleDialog extends BottomSheetDialog {
    private DialogRecommendStyleBinding binding;

    public RecommendStyleDialog(@NotNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DialogRecommendStyleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(attributes);

        initView();
    }

    private void initView() {
        SettingModel settingModel = new ViewModelProvider(ViewUtils.scanForActivity(getContext())).get(SettingModel.class);

        int recommendStyle = PreferenceUtils.getRecommendStyle(getContext());
        if (recommendStyle == 1) {
            binding.singleRadio.setChecked(true);
        } else if (recommendStyle == 2) {
            binding.doubleRadio.setChecked(true);
        } else {
            dismiss();
            return;
        }

        binding.singleContainer.setOnClickListener(v -> {
            binding.singleRadio.setChecked(true);
            binding.doubleRadio.setChecked(false);
            settingModel.getRecommendStyle().setValue(1);
            PreferenceUtils.setRecommendStyle(getContext(), 1);
            Toast.makeText(getContext(), R.string.setting_recommend_style_tip, Toast.LENGTH_SHORT).show();
        });

        binding.doubleContainer.setOnClickListener(v -> {
            binding.singleRadio.setChecked(false);
            binding.doubleRadio.setChecked(true);
            settingModel.getRecommendStyle().setValue(2);
            PreferenceUtils.setRecommendStyle(getContext(), 2);
            Toast.makeText(getContext(), R.string.setting_recommend_style_tip, Toast.LENGTH_SHORT).show();
        });
    }
}