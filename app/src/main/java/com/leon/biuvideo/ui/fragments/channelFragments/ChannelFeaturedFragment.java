package com.leon.biuvideo.ui.fragments.channelFragments;

import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.databinding.PageFilterRefreshBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.beans.home.channel.ChannelDetailFeatured;
import com.leon.biuvideo.ui.adapters.channel.ChannelFeaturedAdapter;
import com.leon.biuvideo.ui.adapters.channel.ChannelFeaturedFilterAdapter;
import com.leon.biuvideo.utils.PaginationLoader;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/16
 * @Desc
 */
public class ChannelFeaturedFragment extends BaseLazyFragment<PageFilterRefreshBinding> {
    private final static int START = 2022;
    private final static int END = 2009;

    private PaginationLoader<ChannelDetailFeatured, ChannelDetailFeatured.Data.Archive> loader;
    private HttpApi httpApi;

    private final String id;
    private int type = 0;
    private String offset;
    private ChannelFeaturedAdapter adapter;

    public ChannelFeaturedFragment(String id) {
        this.id = id;
    }

    @Override
    public PageFilterRefreshBinding getViewBinding() {
        return PageFilterRefreshBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        HashMap<Integer, String> filter = new LinkedHashMap<>(Map.of(0, "全部"));

        for (int i = START; i >= END; i--) {
            filter.put(i, i + "年");
        }

        ChannelFeaturedFilterAdapter filterAdapter = new ChannelFeaturedFilterAdapter(context).setOnFilterCallback(integerStringEntry -> {
            ChannelFeaturedFragment.this.type = integerStringEntry.getKey();
            reload();
        });
        filterAdapter.appendHead(filter.entrySet());
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
    protected void onLazyLoad() {
        if (loader != null) {
            loader.firstObtain();
        }
    }

    // todo 重置没有进行自动加载，待修复
    private void reload() {
        Toast.makeText(context, String.valueOf(type), Toast.LENGTH_SHORT).show();
        offset = null;
        adapter.removeAll();
        loader.setUpdateInterface(loadType -> loader.setObservable(httpApi.getChannelDetailFeatured(id, type, offset)));
    }
}
