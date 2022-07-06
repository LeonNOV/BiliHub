package com.leon.biuvideo.ui.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseActivity.ActivityManager;
import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.beans.search.SearchSuggestion;
import com.leon.biuvideo.databinding.ActivitySearchBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.SearchSuggestionAdapter;
import com.leon.biuvideo.utils.RefreshLoader;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Objects;

/**
 * @Author Leon
 * @Time 2022/6/15
 * @Desc
 */
public class SearchActivity extends BaseActivity<ActivitySearchBinding> {

    private ViewGroup.LayoutParams layoutParams;
    private HttpApi httpApi;
    private SearchSuggestionAdapter adapter;
    private RefreshLoader<SearchSuggestion, SearchSuggestion.Result.Tag> loader;

    @Override
    public ActivitySearchBinding getViewBinding() {
        return ActivitySearchBinding.inflate(getLayoutInflater());
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void init() {
        binding.searchBarEditor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ((count + start) > 0) {
                    binding.searchBarClean.setVisibility(View.VISIBLE);

                    getSuggestion(Objects.requireNonNull(binding.searchBarEditor.getText()).toString());
                    resizeSearchBar(true);
                    binding.searchBarSuggestion.setVisibility(View.VISIBLE);
                } else {
                    resizeSearchBar(false);
                    binding.searchBarClean.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        binding.searchBarClean.setOnClickListener(v -> Objects.requireNonNull(binding.searchBarEditor.getText()).clear());

        binding.searchBarGo.setOnClickListener(v -> goSearch(Objects.requireNonNull(binding.searchBarEditor.getText()).toString()));
        binding.searchBarEditor.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                goSearch(Objects.requireNonNull(binding.searchBarEditor.getText()).toString());
                return true;
            }

            return false;
        });

        binding.topBarBack.setOnTouchListener((v, event) -> ViewUtils.Zoom(event, binding.topBarBack));
        binding.topBarBack.setOnClickListener(v -> backPressed());

        adapter = new SearchSuggestionAdapter(context);
    }

    /**
     * get the suggestion
     *
     * @param keyword keyword
     */
    private void getSuggestion(String keyword) {
        Log.d(TAG, "getSuggestion: " + keyword);

        if (httpApi == null) {
            httpApi = new RetrofitClient(BaseUrl.SEARCH).getHttpApi();
            loader = new RefreshLoader<>(binding.searchBarSuggestion);
            loader.init(adapter);
            loader.setGuide(searchSuggestion -> searchSuggestion.getResult().getTag());
        }

        if (adapter.hasData()) {
            adapter.removeAll();
        }

        loader.setObservable(httpApi.getSearchSuggestion(keyword));
        loader.obtain();

        Log.d(TAG, "getSuggestion: obtain done");
    }

    /**
     * search by keyword
     *
     * @param keyWord keyword
     */
    private void goSearch(String keyWord) {
        Bundle bundle = new Bundle();
        bundle.putString("keyword", keyWord);
        ActivityManager.startActivity(context, SearchResultActivity.class, bundle);
    }

    /**
     * resize searchBar
     *
     * @param isExpand expand?
     */
    private void resizeSearchBar(boolean isExpand) {
        if (layoutParams == null) {
            layoutParams = binding.searchBarContainer.getLayoutParams();
        }

        if (isExpand) {
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        } else {
            layoutParams.height = getResources().getDimensionPixelSize(R.dimen.search_bar_default_height);
        }

        binding.searchBarContainer.setLayoutParams(layoutParams);
    }
}