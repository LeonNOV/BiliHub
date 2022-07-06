package com.leon.biuvideo.ui.activities.publicActivities;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivityAudioBinding;

/**
 * @Author Leon
 * @Time 2022/6/19
 * @Desc
 */
public class AudioActivity extends BaseActivity<ActivityAudioBinding> {

    @Override
    public ActivityAudioBinding getViewBinding() {
        return ActivityAudioBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        binding.audioBack.setOnClickListener(v -> backPressed());
        binding.audioFavorite.setOnClickListener(v -> binding.audioFavorite.setSelected(!binding.audioFavorite.isSelected()));
    }
}