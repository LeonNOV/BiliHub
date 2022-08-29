package com.leon.bilihub.ui.fragments.searchResultFragments;

import com.leon.bilihub.base.baseFragment.BaseLazyFragment;
import com.leon.bilihub.beans.home.searchResult.SearchResultUser;
import com.leon.bilihub.databinding.FragmentResultUserBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.searchResult.SearchResultUserAdapter;
import com.leon.bilihub.ui.widget.loader.PaginationLoader;

/**
 * @Author Leon
 * @Time 2022/06/23
 * @Desc 搜索结果-用户
 */
public class SearchResultUserFragment extends BaseLazyFragment<FragmentResultUserBinding> {
    private final String keyword;
    private int pageNum = 0;

    private PaginationLoader<SearchResultUser, SearchResultUser.Data.Result> loader;
    private HttpApi httpApi;

    public SearchResultUserFragment(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public FragmentResultUserBinding getViewBinding() {
        return FragmentResultUserBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        httpApi = new RetrofitClient(BaseUrl.API, context).getHttpApi();
        loader = new PaginationLoader<>(binding.content, new SearchResultUserAdapter(context));
        loader.setGuide(searchResultUser -> searchResultUser.getData().getResult());
        loader.setUpdateInterface(loadType -> httpApi.getSearchResultUser(++pageNum, keyword, null, null, null));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }
}
