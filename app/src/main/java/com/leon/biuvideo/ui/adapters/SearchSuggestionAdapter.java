package com.leon.biuvideo.ui.adapters;

import android.content.Context;

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
    public int getLayout(int viewType) {
        return R.layout.item_search_suggestion;
    }

    @Override
    protected ItemSearchSuggestionBinding getItemViewBinding() {
        return ItemSearchSuggestionBinding.bind(itemView);
    }

    @Override
    protected void onBindViewHolder(SearchSuggestion.Result.Tag data, ItemSearchSuggestionBinding binding, int position) {
        binding.item.setText(data.getTerm());
        binding.item.setOnClickListener(v -> {
            ActivityManager.startActivity(context, SearchResultActivity.class, Map.of("keyword", data.getValue()));
        });
    }
}
