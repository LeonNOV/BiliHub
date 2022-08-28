package com.leon.biuvideo.ui.activities.search;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.home.HotSearch;
import com.leon.biuvideo.beans.search.SearchSuggestion;
import com.leon.biuvideo.databinding.ActivitySearchBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.HotSearchAdapter;
import com.leon.biuvideo.ui.adapters.SearchSuggestionAdapter;
import com.leon.biuvideo.ui.widget.loader.PaginationLoader;
import com.leon.biuvideo.ui.widget.loader.RecyclerViewLoader;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Map;
import java.util.Objects;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;

/**
 * @Author Leon
 * @Time 2022/6/15
 * @Desc
 */
public class SearchActivity extends BaseActivity<ActivitySearchBinding> {
    private ViewGroup.LayoutParams layoutParams;
    private HttpApi httpApi;
    private RecyclerViewLoader<SearchSuggestion, SearchSuggestion.Result.Tag> loader;

    @Override
    public ActivitySearchBinding getViewBinding() {
        return ActivitySearchBinding.inflate(getLayoutInflater());
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void init() {
        binding.searchEditor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ((count + start) > 0) {
                    binding.searchClear.setVisibility(View.VISIBLE);

                    getSuggestion(Objects.requireNonNull(binding.searchEditor.getText()).toString());
                    resizeSearchBar(true);
                    binding.searchSuggestion.setVisibility(View.VISIBLE);
                } else {
                    resizeSearchBar(false);
                    binding.searchClear.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        binding.searchClear.setOnClickListener(v -> Objects.requireNonNull(binding.searchEditor.getText()).clear());

        binding.searchGo.setOnClickListener(v -> goSearch(Objects.requireNonNull(binding.searchEditor.getText()).toString()));
        binding.searchEditor.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                goSearch(Objects.requireNonNull(binding.searchEditor.getText()).toString());
                return true;
            }

            return false;
        });

        binding.back.setOnTouchListener((v, event) -> ViewUtils.zoom(event, binding.back));
        binding.back.setOnClickListener(v -> backPressed());

        RecyclerViewLoader<HotSearch, HotSearch.Data.Trending.Data> loader = new RecyclerViewLoader<>(binding.hotSearch, new HotSearchAdapter(context), (content, adapter) -> {
            content.setAdapter(new AlphaInAnimationAdapter(adapter));
            content.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
            content.setMotionEventSplittingEnabled(false);
            content.setHasFixedSize(true);
        });
        loader
                .setGuide(hotSearch -> hotSearch.getData().getTrending().getList())
                .setObservable(new RetrofitClient(BaseUrl.API, context).getHttpApi().getHotSearch())
                .obtain(false);
    }

    /**
     * get the suggestion
     *
     * @param keyword keyword
     */
    private void getSuggestion(String keyword) {
        if (httpApi == null) {
            httpApi = new RetrofitClient(BaseUrl.SEARCH, context).getHttpApi();
            loader = new RecyclerViewLoader<>(binding.searchSuggestion, new SearchSuggestionAdapter(context));
            loader.setGuide(searchSuggestion -> searchSuggestion.getResult().getTag());
        }

        loader.setObservable(httpApi.getSearchSuggestion(keyword)).obtain(true);
    }

    /**
     * search by keyword
     *
     * @param keyWord keyword
     */
    private void goSearch(String keyWord) {
        startActivity(SearchResultActivity.class, Map.of(SearchResultActivity.PARAM, keyWord));
    }

    /**
     * resize searchBar
     *
     * @param isExpand expand?
     */
    private void resizeSearchBar(boolean isExpand) {
        if (layoutParams == null) {
            layoutParams = binding.searchContainer.getLayoutParams();
        }

        if (isExpand) {
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        } else {
            layoutParams.height = getResources().getDimensionPixelSize(R.dimen.search_bar_default_height);
        }

        binding.searchContainer.setLayoutParams(layoutParams);
    }
}