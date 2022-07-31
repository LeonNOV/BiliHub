package com.leon.biuvideo.ui.fragments.searchResultFragments;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.databinding.PageFilterRefreshBinding;

/**
 * @Author Leon
 * @Time 2022/06/23
 * @Desc
 */
public class ComprehensiveResultFragment extends BaseLazyFragment<PageFilterRefreshBinding> {
    private final String keyword;

    public ComprehensiveResultFragment(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public PageFilterRefreshBinding getViewBinding() {
        return PageFilterRefreshBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onLazyLoad() {

    }
}
