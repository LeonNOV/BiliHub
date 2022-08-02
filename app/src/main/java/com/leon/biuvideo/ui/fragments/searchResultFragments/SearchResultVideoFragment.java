package com.leon.biuvideo.ui.fragments.searchResultFragments;

import android.os.Parcelable;
import android.provider.MediaStore;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.home.searchResult.SearchResultVideo;
import com.leon.biuvideo.databinding.FragmentVideoResultBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.Condition;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.searchResult.SearchResultVideoAdapter;
import com.leon.biuvideo.ui.widget.loader.PaginationLoader;

/**
 * @Author Leon
 * @Time 2022/08/02
 * @Desc
 */
public class SearchResultVideoFragment extends BaseLazyFragment<FragmentVideoResultBinding> {
    private final String keyword;
    private int pageNum = 0;
    private Condition.VideoOrder videoOrder = null;
    private Condition.VideoDuration videoDuration = null;
    private Condition.VideoTids videoTids = null;

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
        httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
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
