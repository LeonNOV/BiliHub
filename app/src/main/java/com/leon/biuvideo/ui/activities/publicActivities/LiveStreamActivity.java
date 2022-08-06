package com.leon.biuvideo.ui.activities.publicActivities;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivityLiveStreamBinding;

/**
 * @Author Leon
 * @Time 2022/6/19
 * @Desc
 */
public class LiveStreamActivity extends BaseActivity<ActivityLiveStreamBinding> {
    public static final String PARAM = "roomId";

    @Override
    public ActivityLiveStreamBinding getViewBinding() {
        return ActivityLiveStreamBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        binding.liveBack.setOnClickListener(v -> backPressed());
    }
}