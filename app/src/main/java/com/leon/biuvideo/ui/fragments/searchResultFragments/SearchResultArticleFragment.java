package com.leon.biuvideo.ui.fragments.searchResultFragments;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.databinding.FragmentResultArticleBinding;

/**
 * @Author Leon
 * @Time 2022/06/23
 * @Desc
 */
public class SearchResultArticleFragment extends BaseLazyFragment<FragmentResultArticleBinding> {
    private final String keyword;

    public SearchResultArticleFragment(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public FragmentResultArticleBinding getViewBinding() {
        return FragmentResultArticleBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onLazyLoad() {

    }
}
