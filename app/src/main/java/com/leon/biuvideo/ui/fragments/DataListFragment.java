package com.leon.biuvideo.ui.fragments;

import android.content.Context;
import android.view.View;

import com.leon.biuvideo.base.baseAction.BaseAction;
import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.databinding.FragmentDataListBinding;
import com.leon.biuvideo.databinding.RefreshContentBinding;

/**
 * @Author Leon
 * @Time 2022/6/18
 * @Desc
 */
public class DataListFragment extends BaseLazyFragment<FragmentDataListBinding> {
    private ExternalInterface externalInterface;
    private final int index;
    private final String[] filterItems;

    public DataListFragment(int index, String[] filterItems) {

        this.index = index;
        this.filterItems = filterItems;
    }

    @Override
    public FragmentDataListBinding getViewBinding() {
        return FragmentDataListBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        if (filterItems != null) {
            binding.filter.setVisibility(View.VISIBLE);
            binding.filter.setFilterItem(this.filterItems).setOnSelected(itemIndex -> {
                if (externalInterface != null) {
                    externalInterface.onReload(index, itemIndex);
                }
            });
        }

        if (externalInterface != null) {
            externalInterface.onInit(index, binding.content, context);
        }
    }

    @Override
    protected void onLazyLoad() {
        if (externalInterface != null) {
            externalInterface.onLazyLoad();
        }
    }

    public DataListFragment setExternalInterface(BaseAction externalInterface) {
        this.externalInterface = externalInterface;

        return this;
    }

    public interface ExternalInterface {
        void onInit(int index, RefreshContentBinding binding, Context context);

        void onLazyLoad();

        /**
         * 加载新的数据
         *
         * @param index 当前Fragment index
         * @param tag   加载标记{@link com.leon.biuvideo.ui.widget.DataFilter}
         */
        void onReload(int index, int tag);
    }
}