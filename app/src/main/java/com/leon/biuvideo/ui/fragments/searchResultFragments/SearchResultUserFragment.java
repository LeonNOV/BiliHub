package com.leon.biuvideo.ui.fragments.searchResultFragments;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.home.searchResult.SearchResultArticle;
import com.leon.biuvideo.beans.home.searchResult.SearchResultUser;
import com.leon.biuvideo.databinding.FragmentResultUserBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.searchResult.SearchResultArticleAdapter;
import com.leon.biuvideo.ui.adapters.searchResult.SearchResultUserAdapter;
import com.leon.biuvideo.ui.widget.loader.PaginationLoader;

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
