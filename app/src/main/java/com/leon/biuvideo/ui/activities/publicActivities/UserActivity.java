package com.leon.biuvideo.ui.activities.publicActivities;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivityUserBinding;

/**
 * @Author Leon
 * @Time 2022/6/19
 * @Desc
 */
public class UserActivity extends BaseActivity<ActivityUserBinding> {

    @Override
    public ActivityUserBinding getViewBinding() {
        return ActivityUserBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {

    }
}