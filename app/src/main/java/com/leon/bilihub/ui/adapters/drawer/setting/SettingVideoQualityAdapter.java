package com.leon.bilihub.ui.adapters.drawer.setting;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.core.widget.TextViewCompat;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.databinding.ItemSettingDialogBinding;
import com.leon.bilihub.http.Quality;
import com.leon.bilihub.ui.activities.drawerFunction.SettingActivity;
import com.leon.bilihub.ui.widget.player.PlayerController;
import com.leon.bilihub.utils.PreferenceUtils;
import com.leon.bilihub.values.SettingValueCreator;

/**
 * @Author Leon
 * @Time 2022/08/25
 * @Desc
 */
public class SettingVideoQualityAdapter extends ViewBindingAdapter<SettingValueCreator.SettingVideoQuality, ItemSettingDialogBinding> {
    private PlayerController.OnSelectedListener<Integer> onSelectedListener;
    private int selectedPosition;

    public SettingVideoQualityAdapter(Context context, int initSelectedPosition) {
        super(context);
        this.selectedPosition = initSelectedPosition;
    }

    @Override
    protected ItemSettingDialogBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemSettingDialogBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_setting_dialog, parent, false));
    }

    @Override
    protected void onBindViewHolder(SettingValueCreator.SettingVideoQuality data, ItemSettingDialogBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> {
            if (onSelectedListener != null && selectedPosition != position) {
                data.setSelected(true);

                PreferenceUtils.setVideoQuality(context, data.getQuality(), data.getDisplay());
                onSelectedListener.onSelected(selectedPosition);
                selectedPosition = position;

                SettingActivity.settingModel.getVideoQualityDisplay().setValue(data.getDisplay());
                notifyItemChanged(position);
            }
        });

        if (data.getQuality() == Quality.Q80) {
            binding.getRoot().setText(R.string.setting_video_quality_default);
        } else {
            binding.getRoot().setText(data.getDisplay());
        }
        TextViewCompat.setCompoundDrawableTintList(binding.getRoot(), ColorStateList.valueOf(data.isSelected() ? context.getColor(R.color.pink) : Color.TRANSPARENT));

    }

    public void setOnSelectedListener(PlayerController.OnSelectedListener<Integer> onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }
}
