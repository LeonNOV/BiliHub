package com.leon.biuvideo.ui.fragments.channelFragments;

import androidx.recyclerview.widget.GridLayoutManager;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.home.channel.ChannelDetail;
import com.leon.biuvideo.databinding.PageFilterRefreshBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.beans.home.channel.ChannelDetailFeatured;
import com.leon.biuvideo.ui.adapters.channel.ChannelFeaturedAdapter;
import com.leon.biuvideo.utils.filter.FilterAdapter;
import com.leon.biuvideo.ui.widget.loader.PaginationLoader;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/07/16
 * @Desc
 */
public class ChannelFeaturedFragment extends BaseLazyFragment<PageFilterRefreshBinding> {
    private PaginationLoader<ChannelDetailFeatured, ChannelDetailFeatured.Data.Archive> loader;
    private HttpApi httpApi;

    private int type = 0;
    private String offset;
    private ChannelFeaturedAdapter adapter;

    private final String channelId;
    private final List<ChannelDetail.Data.Tab.Option> options;

    public ChannelFeaturedFragment(String channelId, List<ChannelDetail.Data.Tab.Option> options) {
        this.channelId = channelId;
        this.options = options;
    }

    @Override
    public PageFilterRefreshBinding getViewBinding() {
        return PageFilterRefreshBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        FilterAdapter<ChannelDetail.Data.Tab.Option> filterAdapter = new FilterAdapter<>(context);
        filterAdapter.setOnFilterCallback(new FilterAdapter.OnFilterCallback<>() {
            @Override
            public void onReload(ChannelDetail.Data.Tab.Option option) {
                ChannelFeaturedFragment.this.type = Integer.parseInt(option.getValue());
                reload();
            }

            @Override
            public String onGuide(ChannelDetail.Data.Tab.Option option) {
                return option.getTitle();
            }
        });

        filterAdapter.appendHead(options);
        binding.filter.setAdapter(filterAdapter);

        httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
        adapter = new ChannelFeaturedAdapter(context);
        loader = new PaginationLoader<>(binding.content, adapter, new GridLayoutManager(context, 2));
        loader.setGuide(channelDetailFeatured -> {
            offset = channelDetailFeatured.getData().getOffset();

            return channelDetailFeatured.getData().getList();
        });
        loader.setUpdateInterface(loadType -> httpApi.getChannelDetailFeatured(channelId, type, offset));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }

    private void reload() {
        offset = null;
        adapter.removeAll();
        loader.setUpdateInterface(loadType -> httpApi.getChannelDetailFeatured(channelId, type, offset)).obtain();
    }
}
