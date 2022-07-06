package com.leon.biuvideo.ui.activities;

import androidx.fragment.app.Fragment;

import com.leon.biuvideo.base.baseActivity.ActivityManager;
import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivitySearchResultBinding;
import com.leon.biuvideo.ui.fragments.searchResultFragments.AnimeResultResultFragment;
import com.leon.biuvideo.ui.fragments.searchResultFragments.ArticleResultFragment;
import com.leon.biuvideo.ui.fragments.searchResultFragments.CinephileResultFragment;
import com.leon.biuvideo.ui.fragments.searchResultFragments.ComprehensiveResultFragment;
import com.leon.biuvideo.ui.fragments.searchResultFragments.LiveResultFragment;
import com.leon.biuvideo.ui.fragments.searchResultFragments.UserResultFragment;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Leon
 * @Time 2022/6/23
 * @Desc
 */
public class SearchResultActivity extends BaseActivity<ActivitySearchResultBinding> {

    @Override
    public ActivitySearchResultBinding getViewBinding() {
        return ActivitySearchResultBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        String keyword = params.getString("keyword", "");

        binding.searchResultKeyword.setText(keyword);
        binding.searchResultCancel.setOnClickListener(v -> cancel());
        binding.searchResultClear.setOnClickListener(v -> startActivity(SearchActivity.class));

        /*
         * 综合
         * 番剧
         * 直播
         * 用户
         * 影视
         * 专栏
         * */
        List<Fragment> fragments = new ArrayList<>(6);
        fragments.add(new ComprehensiveResultFragment(keyword));
        fragments.add(new AnimeResultResultFragment(keyword));
        fragments.add(new LiveResultFragment(keyword));
        fragments.add(new UserResultFragment(keyword));
        fragments.add(new CinephileResultFragment(keyword));
        fragments.add(new ArticleResultFragment(keyword));

        ViewUtils.initTabLayout(this, binding.searchResultTabLayout, binding.searchResultViewPager,
                fragments, "综合", "番剧", "直播", "用户", "影视", "专栏");
    }

    private void cancel() {
        backPressed();
        ActivityManager.popTo(SearchActivity.class);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        cancel();
    }
}