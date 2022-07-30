package com.leon.biuvideo.ui.activities;

import android.view.View;
import android.widget.Toast;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivityProfileBinding;

/**
 * @Author Leon
 * @Time 2022/07/15
 * @Desc todo 后期版本加入
 */
public class ProfileActivity extends BaseActivity<ActivityProfileBinding> {
    @Override
    public ActivityProfileBinding getViewBinding() {
        return ActivityProfileBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        binding.more.setOnClickListener(v -> binding.detailInfo.setVisibility(binding.detailInfo.getVisibility() == View.GONE ? View.VISIBLE : View.INVISIBLE));
        binding.edit.setOnClickListener(v -> Toast.makeText(context, "开发中…", Toast.LENGTH_SHORT).show());

    }
}
