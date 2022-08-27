package com.leon.biuvideo.ui.activities.search;

import androidx.fragment.app.Fragment;

import com.leon.biuvideo.base.baseActivity.ActivityManager;
import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivitySearchResultBinding;
import com.leon.biuvideo.ui.fragments.searchResultFragments.SearchResultBangumiFragment;
import com.leon.biuvideo.ui.fragments.searchResultFragments.SearchResultArticleFragment;
import com.leon.biuvideo.ui.fragments.searchResultFragments.SearchResultCinephileFragment;
import com.leon.biuvideo.ui.fragments.searchResultFragments.SearchResultComprehensiveFragment;
import com.leon.biuvideo.ui.fragments.searchResultFragments.SearchResultLiveFragment;
import com.leon.biuvideo.ui.fragments.searchResultFragments.SearchResultUserFragment;
import com.leon.biuvideo.ui.fragments.searchResultFragments.SearchResultVideoFragment;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Leon
 * @Time 2022/6/23
 * @Desc 搜索结果页面
 */
public class SearchResultActivity extends BaseActivity<ActivitySearchResultBinding> {
    public static final String PARAM = "keyword";

    @Override
    public ActivitySearchResultBinding getViewBinding() {
        return ActivitySearchResultBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        String keyword = params.getString(PARAM, "");

        binding.searchResultKeyword.setText(keyword);
        binding.searchResultClear.setOnClickListener(v -> ActivityManager.startWithFinishActivity(context, SearchActivity.class));
        binding.searchResultCancel.setOnClickListener(v -> backPressed());
        binding.searchResultKeyword.setOnClickListener(v -> backPressed());

        /*
         * 综合
         * 番剧
         * 直播
         * 用户
         * 影视
         * 专栏
         * */
        List<Fragment> fragments = new ArrayList<>(6);
//        fragments.add(new SearchResultComprehensiveFragment(keyword));
        fragments.add(new SearchResultVideoFragment(keyword));
        fragments.add(new SearchResultBangumiFragment(keyword));
        fragments.add(new SearchResultLiveFragment(keyword));
        fragments.add(new SearchResultUserFragment(keyword));
        fragments.add(new SearchResultCinephileFragment(keyword));
        fragments.add(new SearchResultArticleFragment(keyword));

        ViewUtils.initTabLayout(this, binding.searchResultTabLayout, binding.searchResultViewPager,
                fragments, "视频", "番剧", "直播", "用户", "影视", "专栏");
    }
}