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
import com.leon.biuvideo.ui.adapters.channel.ChannelFeaturedFilterAdapter;
import com.leon.biuvideo.utils.PaginationLoader;

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
        ChannelFeaturedFilterAdapter filterAdapter = new ChannelFeaturedFilterAdapter(context).setOnFilterCallback(option -> {
            ChannelFeaturedFragment.this.type = Integer.parseInt(option.getValue());
            reload();
        });
        filterAdapter.appendHead(options);
        binding.filter.subTab.setAdapter(filterAdapter);

        httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();

        adapter = new ChannelFeaturedAdapter(context);
        loader = new PaginationLoader<>(binding.content, adapter);
        loader.closeRefresh();
        loader.setLayoutManager(new GridLayoutManager(context, 2));
        loader.setGuide(channelDetailFeatured -> channelDetailFeatured.getData().getList());
        reload();
    }

    @Override
    protected void onLazyLoad() {}

    /**
     * todo 重载数据错误待修复
     */
    private void reload() {
        offset = null;
        adapter.removeAll();
        loader.setUpdateInterface(loadType -> httpApi.getChannelDetailFeatured(channelId, type, offset)).obtain();
    }
}
