package com.leon.biuvideo.ui.fragments.searchResultFragments;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.databinding.FragmentResultAnimeBinding;

/**
 * @Author Leon
 * @Time 2022/06/23
 * @Desc
 */
public class AnimeResultResultFragment extends BaseLazyFragment<FragmentResultAnimeBinding> {
    private final String keyword;

    public AnimeResultResultFragment(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public FragmentResultAnimeBinding getViewBinding() {
        return FragmentResultAnimeBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onLazyLoad() {

    }
}
