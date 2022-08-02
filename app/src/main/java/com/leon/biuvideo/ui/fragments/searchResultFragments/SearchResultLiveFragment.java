package com.leon.biuvideo.ui.fragments.searchResultFragments;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.databinding.FragmentResultLiveBinding;

/**
 * @Author Leon
 * @Time 2022/06/23
 * @Desc
 */
public class SearchResultLiveFragment extends BaseLazyFragment<FragmentResultLiveBinding> {
    private final String keyword;

    public SearchResultLiveFragment(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public FragmentResultLiveBinding getViewBinding() {
        return FragmentResultLiveBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onLazyLoad() {

    }
}
