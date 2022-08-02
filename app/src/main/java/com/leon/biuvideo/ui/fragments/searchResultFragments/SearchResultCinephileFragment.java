package com.leon.biuvideo.ui.fragments.searchResultFragments;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.home.searchResult.SearchResultMedia;
import com.leon.biuvideo.beans.home.searchResult.SearchResultVideo;
import com.leon.biuvideo.databinding.FragmentResultCinephileBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.searchResult.SearchResultBangumiAdapter;
import com.leon.biuvideo.ui.adapters.searchResult.SearchResultFtAdapter;
import com.leon.biuvideo.ui.widget.loader.PaginationLoader;

/**
 * @Author Leon
 * @Time 2022/06/23
 * @Desc
 */
public class SearchResultCinephileFragment extends BaseLazyFragment<FragmentResultCinephileBinding> {
    private final String keyword;
    private int pageNum = 0;

    private PaginationLoader<SearchResultMedia, SearchResultMedia.Data.Result> loader;
    private HttpApi httpApi;

    public SearchResultCinephileFragment(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public FragmentResultCinephileBinding getViewBinding() {
        return FragmentResultCinephileBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
        loader = new PaginationLoader<>(binding.content, new SearchResultFtAdapter(context));
        loader.setGuide(searchResultMedia -> searchResultMedia.getData().getResult());
        loader.setUpdateInterface(loadType -> httpApi.getSearchResultFt(++pageNum, keyword));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }
}
