package com.leon.bilihub.ui.fragments.searchResultFragments;

import androidx.recyclerview.widget.GridLayoutManager;

import com.leon.bilihub.base.baseFragment.BaseLazyFragment;
import com.leon.bilihub.beans.home.searchResult.SearchResultLive;
import com.leon.bilihub.databinding.FragmentResultLiveBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.searchResult.SearchResultLiveAdapter;
import com.leon.bilihub.ui.widget.loader.PaginationLoader;

/**
 * @Author Leon
 * @Time 2022/06/23
 * @Desc 搜索结果-直播
 */
public class SearchResultLiveFragment extends BaseLazyFragment<FragmentResultLiveBinding> {
    private final String keyword;
    private int pageNum = 0;

    private PaginationLoader<SearchResultLive, SearchResultLive.Data.Result.LiveRoom> loader;
    private HttpApi httpApi;

    public SearchResultLiveFragment(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public FragmentResultLiveBinding getViewBinding() {
        return FragmentResultLiveBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        httpApi = new RetrofitClient(BaseUrl.API, context).getHttpApi();
        loader = new PaginationLoader<>(binding.content, new SearchResultLiveAdapter(context), new GridLayoutManager(context, 2));
        loader.setGuide(searchResultLive -> searchResultLive.getData().getResult().getLiveRoom());
        loader.setUpdateInterface(loadType -> httpApi.getSearchResultLive(++pageNum, keyword, null, null));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }
}
