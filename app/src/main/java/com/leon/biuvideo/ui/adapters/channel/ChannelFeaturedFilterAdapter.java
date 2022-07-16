package com.leon.biuvideo.ui.adapters.channel;

import android.content.Context;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.databinding.ItemFilterBinding;
import com.leon.biuvideo.utils.FilterUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/16
 * @Desc
 */
public class ChannelFeaturedFilterAdapter extends BaseViewBindingAdapter<Map.Entry<Integer, String>, ItemFilterBinding> {
    private final FilterUtils<Map.Entry<Integer, String>> filterUtils;

    public ChannelFeaturedFilterAdapter(Context context) {
        super(context);

        this.filterUtils = new FilterUtils<>(context, 0);
    }

    @Override
    public int getLayout(int viewType) {
        return R.layout.item_filter;
    }

    @Override
    protected ItemFilterBinding getItemViewBinding() {
        return ItemFilterBinding.bind(itemView);
    }

    @Override
    protected void onBindViewHolder(Map.Entry<Integer, String> data, ItemFilterBinding binding, int position) {
        this.filterUtils.addTextView(position, binding.filter);
        binding.getRoot().setOnClickListener(v -> this.filterUtils.selected(data, position));

        binding.filter.setText(data.getValue());
    }

    public ChannelFeaturedFilterAdapter setOnFilterCallback(FilterUtils.OnFilterCallback<Map.Entry<Integer, String>> onFilterCallback) {
        this.filterUtils.setOnFilterCallback(onFilterCallback);

        return this;
    }
}
