package com.leon.biuvideo.ui.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.home.HotSearch;
import com.leon.biuvideo.databinding.ItemHotSearchBinding;
import com.leon.biuvideo.ui.activities.search.SearchResultActivity;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/15
 * @Desc
 */
public class HotSearchAdapter extends BaseViewBindingAdapter<HotSearch.Data.Trending.Data, ItemHotSearchBinding> {
    public HotSearchAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemHotSearchBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemHotSearchBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_hot_search, parent, false));
    }

    @Override
    protected void onBindViewHolder(HotSearch.Data.Trending.Data data, ItemHotSearchBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(SearchResultActivity.class, Map.of(SearchResultActivity.PARAM, data.getKeyword())));

        if (position < 3) {
            binding.keyword.setTextColor(context.getColor(R.color.BiliBili_pink));
            binding.keyword.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            binding.rank.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
        binding.keyword.setText(data.getShowName());
        binding.rank.setText(String.valueOf(position + 1));
        if (!"".equals(data.getIcon())) {
            binding.icon.setVisibility(View.VISIBLE);
            ViewUtils.setImg(context, binding.icon, data.getIcon());
        }
    }
}
