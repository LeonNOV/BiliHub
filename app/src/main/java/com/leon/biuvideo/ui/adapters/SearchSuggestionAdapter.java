package com.leon.biuvideo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseActivity.ActivityManager;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.search.SearchSuggestion;
import com.leon.biuvideo.databinding.ItemSearchSuggestionBinding;
import com.leon.biuvideo.ui.activities.search.SearchResultActivity;

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
