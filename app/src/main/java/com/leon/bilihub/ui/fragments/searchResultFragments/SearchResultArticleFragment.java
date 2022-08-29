package com.leon.bilihub.ui.fragments.searchResultFragments;

import com.leon.bilihub.base.baseFragment.BaseLazyFragment;
import com.leon.bilihub.beans.home.searchResult.SearchResultArticle;
import com.leon.bilihub.databinding.FragmentResultArticleBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.searchResult.SearchResultArticleAdapter;
import com.leon.bilihub.ui.widget.loader.PaginationLoader;

/**
 * @Author Leon
 * @Time 2022/06/23
 * @Desc 搜索结果-专栏
 */
public class SearchResultArticleFragment extends BaseLazyFragment<FragmentResultArticleBinding> {
    private final String keyword;
    private int pageNum = 0;

    private PaginationLoader<SearchResultArticle, SearchResultArticle.Data.Result> loader;
    private HttpApi httpApi;

    public SearchResultArticleFragment(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public FragmentResultArticleBinding getViewBinding() {
        return FragmentResultArticleBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        httpApi = new RetrofitClient(BaseUrl.API, context).getHttpApi();
        loader = new PaginationLoader<>(binding.content, new SearchResultArticleAdapter(context));
        loader.setGuide(searchResultArticle -> searchResultArticle.getData().getResult());
        loader.setUpdateInterface(loadType -> httpApi.getSearchResultArticle(++pageNum, keyword, null, null));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }
}
