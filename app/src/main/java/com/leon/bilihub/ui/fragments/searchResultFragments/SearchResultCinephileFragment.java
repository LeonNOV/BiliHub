package com.leon.bilihub.ui.fragments.searchResultFragments;

import com.leon.bilihub.base.baseFragment.BaseLazyFragment;
import com.leon.bilihub.beans.home.searchResult.SearchResultMedia;
import com.leon.bilihub.databinding.FragmentResultCinephileBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.searchResult.SearchResultFtAdapter;
import com.leon.bilihub.ui.widget.loader.PaginationLoader;

/**
 * @Author Leon
 * @Time 2022/06/23
 * @Desc 搜索结果-影视
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
        httpApi = new RetrofitClient(BaseUrl.API, context).getHttpApi();
        loader = new PaginationLoader<>(binding.content, new SearchResultFtAdapter(context));
        loader.setGuide(searchResultMedia -> searchResultMedia.getData().getResult());
        loader.setUpdateInterface(loadType -> httpApi.getSearchResultFt(++pageNum, keyword));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }
}
