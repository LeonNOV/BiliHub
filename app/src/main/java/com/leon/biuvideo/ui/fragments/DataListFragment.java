package com.leon.biuvideo.ui.fragments;

import com.leon.biuvideo.base.baseAction.BaseAction;
import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.databinding.FragmentDataListBinding;

/**
 * @Author Leon
 * @Time 2022/6/18
 * @Desc
 */
public class DataListFragment extends BaseLazyFragment<FragmentDataListBinding> {
    private final BaseAction action;
    private final int pageIndex;

    public DataListFragment(BaseAction action, int pageIndex) {
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
        action.createAdapter(pageIndex);
    }
}