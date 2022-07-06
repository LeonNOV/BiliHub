package com.leon.biuvideo.ui.fragments.searchResultFragments;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.databinding.FragmentResultCinephileBinding;

/**
 * @Author Leon
 * @Time 2022/06/23
 * @Desc
 */
public class CinephileResultFragment extends BaseLazyFragment<FragmentResultCinephileBinding> {
    private final String keyword;

    public CinephileResultFragment(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public FragmentResultCinephileBinding getViewBinding() {
        return FragmentResultCinephileBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onLazyLoad() {

    }
}
