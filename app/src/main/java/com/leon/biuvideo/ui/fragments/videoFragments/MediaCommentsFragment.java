package com.leon.biuvideo.ui.fragments.videoFragments;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.databinding.FragmentMediaCommentsBinding;

/**
 * @Author Leon
 * @Time 2022/08/04
 * @Desc
 */
public class MediaCommentsFragment extends BaseLazyFragment<FragmentMediaCommentsBinding> {
    private final String bvid;

    public MediaCommentsFragment(String bvid) {
        this.bvid = bvid;
    }

    @Override
    public FragmentMediaCommentsBinding getViewBinding() {
        return FragmentMediaCommentsBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onLazyLoad() {

    }
}
