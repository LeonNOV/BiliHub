package com.leon.biuvideo.ui.fragments.popularFragments;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.databinding.FragmentPopularHotBinding;

/**
 * @Author Leon
 * @Time 2022/07/03
 * @Desc
 */
public class HotFragment extends BaseLazyFragment<FragmentPopularHotBinding> {
    @Override
    public FragmentPopularHotBinding getViewBinding() {
        return FragmentPopularHotBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onLazyLoad() {

    }
}
