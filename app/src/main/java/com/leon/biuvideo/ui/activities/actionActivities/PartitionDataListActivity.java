package com.leon.biuvideo.ui.activities.actionActivities;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.beans.Partition;
import com.leon.biuvideo.databinding.ActivityPartitionDataListBinding;
import com.leon.biuvideo.ui.fragments.PartitionDataListFragment;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Leon
 * @Time 2022/6/22
 * @Desc
 */
public class PartitionDataListActivity extends BaseActivity<ActivityPartitionDataListBinding> {
    @Override
    public ActivityPartitionDataListBinding getViewBinding() {
        return ActivityPartitionDataListBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {

    }
}