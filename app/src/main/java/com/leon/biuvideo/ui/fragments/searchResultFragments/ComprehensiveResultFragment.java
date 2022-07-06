package com.leon.biuvideo.ui.fragments.searchResultFragments;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.databinding.FragmentResultComprehensiveBinding;

/**
 * @Author Leon
 * @Time 2022/06/23
 * @Desc
 */
public class ComprehensiveResultFragment extends BaseLazyFragment<FragmentResultComprehensiveBinding> {
    private final String keyword;

    public ComprehensiveResultFragment(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public FragmentResultComprehensiveBinding getViewBinding() {
        return FragmentResultComprehensiveBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onLazyLoad() {

    }
}
