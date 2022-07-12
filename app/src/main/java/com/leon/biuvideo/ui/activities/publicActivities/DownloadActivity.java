package com.leon.biuvideo.ui.activities.publicActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivityDownloadBinding;

/**
 * @Author Leon
 * @Time 2022/7/12
 * @Desc
 */
public class DownloadActivity extends BaseActivity<ActivityDownloadBinding> {

    @Override
    public ActivityDownloadBinding getViewBinding() {
        return ActivityDownloadBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {

    }
}