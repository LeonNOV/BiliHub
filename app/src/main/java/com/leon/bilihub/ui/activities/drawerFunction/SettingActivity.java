package com.leon.bilihub.ui.activities.drawerFunction;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.leon.bilihub.base.baseActivity.BaseActivity;
import com.leon.bilihub.databinding.ActivitySettingBinding;
import com.leon.bilihub.model.SettingModel;
import com.leon.bilihub.ui.activities.drawerFunction.setting.SettingQualityActivity;
import com.leon.bilihub.ui.dialogs.AboutDialog;
import com.leon.bilihub.ui.dialogs.DonationDialog;
import com.leon.bilihub.ui.dialogs.RecommendStyleDialog;
import com.leon.bilihub.utils.FileUtils;
import com.leon.bilihub.utils.PreferenceUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/6/16
 * @Desc
 */
public class SettingActivity extends BaseActivity<ActivitySettingBinding> {
    public static SettingModel settingModel;
    private Observer<String> videoQualityObserver;
    private Observer<String> liveQualityObserver;
    private Observer<Integer> recommendStyleObserver;

    @Override
    public ActivitySettingBinding getViewBinding() {
        return ActivitySettingBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        settingModel = new ViewModelProvider(this).get(SettingModel.class);

        videoQualityObserver = display -> binding.videoQuality.setText(display);
        settingModel.getVideoQualityDisplay().observeForever(videoQualityObserver);
        settingModel.getVideoQualityDisplay().setValue(PreferenceUtils.getVideoQualityDisplay(context));

        liveQualityObserver = display -> binding.liveQuality.setText(display);
        settingModel.getLiveQualityDisplay().observeForever(liveQualityObserver);
        settingModel.getLiveQualityDisplay().setValue(PreferenceUtils.getLiveQualityDisplay(context));

        recommendStyleObserver = style -> {
            if (style == 1) {
                binding.recommend.setText("单列");
            } else if (style == 2) {
                binding.recommend.setText("双列");
            }
        };
        settingModel.getRecommendStyle().observeForever(recommendStyleObserver);
        settingModel.getRecommendStyle().setValue(PreferenceUtils.getRecommendStyle(context));

        binding.videoQualityContainer.setOnClickListener(v -> startActivity(SettingQualityActivity.class,
                Map.of(SettingQualityActivity.PARAM_TYPE, SettingQualityActivity.PARAM_VIDEO)));

        binding.liveQualityContainer.setOnClickListener(v -> startActivity(SettingQualityActivity.class,
                Map.of(SettingQualityActivity.PARAM_TYPE, SettingQualityActivity.PARAM_LIVE)));

        binding.cacheSize.setText(FileUtils.getTotalCacheSize(context));
        binding.cleanCache.setOnClickListener(v -> {
            FileUtils.clearAllCache(context);
            binding.cacheSize.setText(FileUtils.getTotalCacheSize(context));
        });

        binding.imgModeSwitch.setChecked(PreferenceUtils.getImgMode(context));
        binding.imgMode.setOnClickListener(v -> {
            boolean isChecked = binding.imgModeSwitch.isChecked();
            binding.imgModeSwitch.setChecked(!isChecked);
            PreferenceUtils.setImgMode(context, !isChecked);
        });
        binding.about.setOnClickListener(v -> new AboutDialog(context).show());
        binding.donation.setOnClickListener(v -> new DonationDialog(context).show());
        binding.recommendStyle.setOnClickListener(v -> new RecommendStyleDialog(context).show());
    }

    @Override
    protected void onDestroy() {
        settingModel.getVideoQualityDisplay().removeObserver(videoQualityObserver);
        settingModel.getLiveQualityDisplay().removeObserver(liveQualityObserver);
        settingModel.getRecommendStyle().removeObserver(recommendStyleObserver);
        settingModel = null;

        super.onDestroy();
    }
}