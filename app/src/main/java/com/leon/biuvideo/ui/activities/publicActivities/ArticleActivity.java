package com.leon.biuvideo.ui.activities.publicActivities;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivityArticleBinding;

/**
 * @Author Leon
 * @Time 2022/6/18
 * @Desc
 */
public class ArticleActivity extends BaseActivity<ActivityArticleBinding> {

    @Override
    public ActivityArticleBinding getViewBinding() {
        return ActivityArticleBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        binding.articleBack.setOnClickListener(v -> backPressed());
    }
}