package com.leon.biuvideo.ui.activities.publicActivities;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivityVideoBinding;

/**
 * @Author Leon
 * @Time 2022/6/19
 * @Desc
 */
public class VideoActivity extends BaseActivity<ActivityVideoBinding> {
    public static final String PARAM = "bvid";

    @Override
    public ActivityVideoBinding getViewBinding() {
        return ActivityVideoBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {

    }
}