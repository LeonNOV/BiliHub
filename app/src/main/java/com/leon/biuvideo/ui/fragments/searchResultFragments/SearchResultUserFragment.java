package com.leon.biuvideo.ui.fragments.searchResultFragments;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.databinding.FragmentResultUserBinding;

/**
 * @Author Leon
 * @Time 2022/06/23
 * @Desc
 */
public class SearchResultUserFragment extends BaseLazyFragment<FragmentResultUserBinding> {
    private final String keyword;

    public SearchResultUserFragment(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public FragmentResultUserBinding getViewBinding() {
        return FragmentResultUserBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onLazyLoad() {

    }
}
