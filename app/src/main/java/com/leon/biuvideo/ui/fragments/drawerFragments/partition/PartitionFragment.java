package com.leon.biuvideo.ui.fragments.drawerFragments.partition;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.partition.PartitionTag;
import com.leon.biuvideo.databinding.FragmentPartitionBinding;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/07/13
 * @Desc
 */
public class PartitionFragment extends BaseLazyFragment<FragmentPartitionBinding> {
    private final List<PartitionTag> partitionTags;

    public PartitionFragment(List<PartitionTag> partitionTags) {
        this.partitionTags = partitionTags;
    }

    @Override
    public FragmentPartitionBinding getViewBinding() {
        return FragmentPartitionBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onLazyLoad() {

    }
}
