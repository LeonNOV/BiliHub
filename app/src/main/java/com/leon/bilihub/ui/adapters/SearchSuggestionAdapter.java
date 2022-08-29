package com.leon.bilihub.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseActivity.ActivityManager;
import com.leon.bilihub.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.bilihub.beans.search.SearchSuggestion;
import com.leon.bilihub.databinding.ItemSearchSuggestionBinding;
import com.leon.bilihub.ui.activities.search.SearchResultActivity;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/06/30
 * @Desc
 */
public class SearchSuggestionAdapter extends BaseViewBindingAdapter<SearchSuggestion.Result.Tag, ItemSearchSuggestionBinding> {
    public SearchSuggestionAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemSearchSuggestionBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemSearchSuggestionBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_search_suggestion, parent, false));
    }

    @Override
    protected void onBindViewHolder(SearchSuggestion.Result.Tag data, ItemSearchSuggestionBinding binding, int position) {
        binding.item.setText(data.getTerm());
        binding.item.setOnClickListener(v -> ActivityManager.startWithFinishActivity(context,
                SearchResultActivity.class, Map.of(SearchResultActivity.PARAM, data.getValue())));
    }
}
