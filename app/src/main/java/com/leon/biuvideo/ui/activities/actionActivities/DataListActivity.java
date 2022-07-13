package com.leon.biuvideo.ui.activities.actionActivities;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivityDataListBinding;

/**
 * @Author Leon
 * @Time 2022/6/18
 * @Desc
 */
public class DataListActivity extends BaseActivity<ActivityDataListBinding> {
    @Override
    public ActivityDataListBinding getViewBinding() {
        return ActivityDataListBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {

    }
}