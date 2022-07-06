package com.leon.biuvideo.ui.adapters;

import android.content.Context;
import android.widget.Toast;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseActivity.ActivityManager;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.search.SearchSuggestion;
import com.leon.biuvideo.databinding.SearchSuggestionItemBinding;
import com.leon.biuvideo.ui.activities.SearchResultActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/06/30
 * @Desc
 */
public class SearchSuggestionAdapter extends BaseViewBindingAdapter<SearchSuggestion.Result.Tag, SearchSuggestionItemBinding> {
    public SearchSuggestionAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayout(int viewType) {
        return R.layout.search_suggestion_item;
    }

    @Override
    protected SearchSuggestionItemBinding getItemViewBinding() {
        return SearchSuggestionItemBinding.bind(itemView);
    }

    @Override
    protected void onBindViewHolder(SearchSuggestion.Result.Tag data, SearchSuggestionItemBinding binding, int position) {
        binding.item.setText(data.getTerm());
        binding.item.setOnClickListener(v -> {
            ActivityManager.startActivity(context, SearchResultActivity.class, Map.of("keyword", data.getValue()));
        });
    }
}
