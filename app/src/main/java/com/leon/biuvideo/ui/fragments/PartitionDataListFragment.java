package com.leon.biuvideo.ui.fragments;

import com.leon.biuvideo.base.baseAction.BaseAction;
import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.Partition;
import com.leon.biuvideo.beans.PartitionTag;
import com.leon.biuvideo.databinding.FragmentDataListBinding;

/**
 * @Author Leon
 * @Time 2022/06/22
 * @Desc
 */
public class PartitionDataListFragment extends BaseLazyFragment<FragmentDataListBinding> {
    private final Partition partition;
    private final BaseAction action;
    private final int pageIndex;

    public PartitionDataListFragment(Partition partition, BaseAction action, int pageIndex) {
        this.partition = partition;
        this.action = action;
        this.pageIndex = pageIndex;
    }

    @Override
    public FragmentDataListBinding getViewBinding() {
        return FragmentDataListBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onLazyLoad() {
//        action.createAdapter(pageIndex);

        StringBuilder text = new StringBuilder();
        for (PartitionTag tag : partition.getTags()) {
            text.append(tag.getTitle()).append("\n");
        }
//        binding.dataListAction.setText(text);
    }
}
