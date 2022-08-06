package com.leon.biuvideo.ui.fragments.videoFragments;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.databinding.FragmentMediaInfoBinding;

/**
 * @Author Leon
 * @Time 2022/08/04
 * @Desc
 */
public class MediaInfoFragment extends BaseLazyFragment<FragmentMediaInfoBinding> {
    private final String bvid;

    public MediaInfoFragment(String bvid) {
        this.bvid = bvid;
    }

    @Override
    public FragmentMediaInfoBinding getViewBinding() {
        return FragmentMediaInfoBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onLazyLoad() {

    }
}
