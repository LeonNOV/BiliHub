package com.leon.bilihub.ui.fragments.drawerFragments.partition;

import androidx.recyclerview.widget.GridLayoutManager;

import com.leon.bilihub.base.baseFragment.BaseLazyFragment;
import com.leon.bilihub.beans.partition.PartitionData;
import com.leon.bilihub.beans.partition.PartitionTag;
import com.leon.bilihub.databinding.PageFilterRefreshBinding;
import com.leon.bilihub.http.ApiHelper;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.partition.PartitionDataAdapter;
import com.leon.bilihub.ui.widget.loader.PaginationLoader;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.filter.FilterAdapter;

/**
 * @Author Leon
 * @Time 2022/07/13
 * @Desc
 */
public class PartitionFragment extends BaseLazyFragment<PageFilterRefreshBinding> {
    private final int tid;

    private HttpApi httpApi;
    private int pageNum = 0;

    private PaginationLoader<PartitionData, PartitionData.Result> loader;
    private PartitionDataAdapter adapter;

    public PartitionFragment(int tid) {
        this.tid = tid;
    }

    @Override
    public PageFilterRefreshBinding getViewBinding() {
        return PageFilterRefreshBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        initFilter();
        httpApi = new RetrofitClient(BaseUrl.SEARCH).getHttpApi();
        adapter = new PartitionDataAdapter(context);
        loader = new PaginationLoader<>(binding.content, adapter, new GridLayoutManager(context, 2));
        loader.setGuide(PartitionData::getResult);
    }

    @Override
    protected void onLazyLoad() {
        reload(-1, null);
    }

    private void initFilter() {
        new ApiHelper<>(new RetrofitClient(BaseUrl.API).getHttpApi().getPartitionTags(tid))
                .setOnResult(partitionTag -> {
                    FilterAdapter<PartitionTag.Data.Tag> filterAdapter = new FilterAdapter<>(context);
                    filterAdapter.setOnFilterCallback(new FilterAdapter.OnFilterCallback<>() {
                        @Override
                        public void onReload(PartitionTag.Data.Tag tag) {
                            reload(tag.getTagId(), tag.getTagName());
                        }

                        @Override
                        public String onGuide(PartitionTag.Data.Tag tag) {
                            return tag.getTagName();
                        }
                    });
                    partitionTag.getData().get(0).getTags().add(0, new PartitionTag.Data.Tag(0, 0, -1, "首页"));
                    filterAdapter.appendHead(partitionTag.getData().get(0).getTags());
                    binding.filter.setAdapter(filterAdapter);
                })
                .doIt();
    }

    private void reload(int tagId, String keyword) {
        if (tagId == -1) {
            keyword = null;
        }

        pageNum = 0;
        adapter.removeAll();
        long end = System.currentTimeMillis() / 1000;
        String finalKeyword = keyword;
        loader
                .setUpdateInterface(loadType -> httpApi.getPartitionData(tid, finalKeyword, ++pageNum, ValueUtils.generateTime(end - 604800, "yyyyMMdd", true), ValueUtils.generateTime(end, "yyyyMMdd", true)))
                .obtain();
    }
}
