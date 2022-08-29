package com.leon.bilihub.ui.adapters.drawer.setting;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.core.widget.TextViewCompat;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.bilihub.databinding.ItemSettingDialogBinding;
import com.leon.bilihub.ui.activities.drawerFunction.SettingActivity;
import com.leon.bilihub.ui.widget.player.PlayerController;
import com.leon.bilihub.utils.PreferenceUtils;
import com.leon.bilihub.values.SettingValueCreator;

/**
 * @Author Leon
 * @Time 2022/08/25
 * @Desc
 */
public class SettingLiveQualityAdapter extends BaseViewBindingAdapter<SettingValueCreator.SettingLiveQuality, ItemSettingDialogBinding> {
    private PlayerController.OnSelectedListener<Integer> onSelectedListener;
    private int selectedPosition;

    public SettingLiveQualityAdapter(Context context, int initSelectedPosition) {
        super(context);
        this.selectedPosition = initSelectedPosition;
    }

    @Override
    protected ItemSettingDialogBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemSettingDialogBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_setting_dialog, parent, false));
    }

    @Override
    protected void onBindViewHolder(SettingValueCreator.SettingLiveQuality data, ItemSettingDialogBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> {
            if (onSelectedListener != null && selectedPosition != position) {
                data.setSelected(true);

                PreferenceUtils.setLiveQuality(context, data.getQn(), data.getDisplay());
                onSelectedListener.onSelected(selectedPosition);
                selectedPosition = position;

                SettingActivity.settingModel.getLiveQualityDisplay().setValue(data.getDisplay());
                notifyItemChanged(position);
            }
        });

        binding.getRoot().setText(data.getDisplay());
        TextViewCompat.setCompoundDrawableTintList(binding.getRoot(), ColorStateList.valueOf(data.isSelected() ? context.getColor(R.color.pink) : Color.TRANSPARENT));
    }

    public void setOnSelectedListener(PlayerController.OnSelectedListener<Integer> onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }
}
