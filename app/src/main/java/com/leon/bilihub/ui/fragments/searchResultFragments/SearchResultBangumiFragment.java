package com.leon.bilihub.ui.fragments.searchResultFragments;

import com.leon.bilihub.base.baseFragment.BaseLazyFragment;
import com.leon.bilihub.beans.home.searchResult.SearchResultMedia;
import com.leon.bilihub.databinding.FragmentResultAnimeBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.searchResult.SearchResultBangumiAdapter;
import com.leon.bilihub.ui.widget.loader.PaginationLoader;

/**
 * @Author Leon
 * @Time 2022/06/23
 * @Desc 搜索结果-番剧
 */
public class SearchResultBangumiFragment extends BaseLazyFragment<FragmentResultAnimeBinding> {
    private final String keyword;
    private int pageNum = 0;

    private PaginationLoader<SearchResultMedia, SearchResultMedia.Data.Result> loader;
    private HttpApi httpApi;

    public SearchResultBangumiFragment(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public FragmentResultAnimeBinding getViewBinding() {
        return FragmentResultAnimeBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        httpApi = new RetrofitClient(BaseUrl.API, context).getHttpApi();
        loader = new PaginationLoader<>(binding.content, new SearchResultBangumiAdapter(context));
        loader.setGuide(searchResultMedia -> searchResultMedia.getData().getResult());
        loader.setUpdateInterface(loadType -> httpApi.getSearchResultBangumi(++pageNum, keyword));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }
}
