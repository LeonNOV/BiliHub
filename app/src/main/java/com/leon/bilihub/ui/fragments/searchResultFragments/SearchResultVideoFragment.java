package com.leon.bilihub.ui.fragments.searchResultFragments;

import com.leon.bilihub.base.baseFragment.BaseLazyFragment;
import com.leon.bilihub.beans.home.searchResult.SearchResultVideo;
import com.leon.bilihub.databinding.FragmentVideoResultBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.Condition;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.searchResult.SearchResultVideoAdapter;
import com.leon.bilihub.ui.widget.loader.PaginationLoader;

/**
 * @Author Leon
 * @Time 2022/08/02
 * @Desc
 */
public class SearchResultVideoFragment extends BaseLazyFragment<FragmentVideoResultBinding> {
    private final String keyword;
    private int pageNum = 0;
    private final Condition.VideoOrder videoOrder = null;
    private final Condition.VideoDuration videoDuration = null;
    private final Condition.VideoTids videoTids = null;

    private SearchResultVideoAdapter adapter;
    private HttpApi httpApi;
    private PaginationLoader<SearchResultVideo, SearchResultVideo.Data.Result> loader;

    public SearchResultVideoFragment(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public FragmentVideoResultBinding getViewBinding() {
        return FragmentVideoResultBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        adapter = new SearchResultVideoAdapter(context);
        httpApi = new RetrofitClient(BaseUrl.API, context).getHttpApi();
        loader = new PaginationLoader<>(binding.content, adapter);
        loader.setGuide(searchResultVideo -> searchResultVideo.getData().getResult());
        loader.setUpdateInterface(loadType -> httpApi.getSearchResultVideo(++pageNum, keyword, videoOrder, videoDuration, videoTids));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }

    private void reload() {
        pageNum = 0;
        adapter.removeAll();
        loader.setUpdateInterface(loadType -> httpApi.getSearchResultVideo(++pageNum, keyword, videoOrder, videoDuration, videoTids)).obtain();
    }
}
