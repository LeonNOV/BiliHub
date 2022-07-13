package com.leon.biuvideo.ui.fragments;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.Partition;
import com.leon.biuvideo.databinding.FragmentPartitionDataListBinding;

/**
 * @Author Leon
 * @Time 2022/06/22
 * @Desc
 */
public class PartitionDataListFragment extends BaseLazyFragment<FragmentPartitionDataListBinding> {
    private final Partition partition;

    public PartitionDataListFragment(Partition partition) {
        this.partition = partition;
    }

    @Override
    public FragmentPartitionDataListBinding getViewBinding() {
        return FragmentPartitionDataListBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onLazyLoad() {

    }
}
