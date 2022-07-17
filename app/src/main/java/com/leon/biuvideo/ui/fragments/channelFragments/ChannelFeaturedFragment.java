package com.leon.biuvideo.ui.fragments.channelFragments;

import androidx.recyclerview.widget.GridLayoutManager;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.databinding.PageFilterRefreshBinding;
import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.beans.home.channel.ChannelDetailFeatured;
import com.leon.biuvideo.ui.adapters.channel.ChannelFeaturedAdapter;
import com.leon.biuvideo.ui.adapters.channel.ChannelFeaturedFilterAdapter;
import com.leon.biuvideo.utils.PaginationLoader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * @Author Leon
 * @Time 2022/07/16
 * @Desc
 */
public class ChannelFeaturedFragment extends BaseLazyFragment<PageFilterRefreshBinding> {
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
        getYears();
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
    }

    /**
     * todo 重载数据错误待修复
     */
    private void reload() {
        offset = null;

        adapter.removeAll();
        loader
                .setUpdateInterface(loadType -> loader.setObservable(httpApi.getChannelDetailFeatured(id, type, offset)))
                .obtain();
    }

    /**
     * todo 获取时间过长，待替换
     */
    private void getYears() {
        Call<ResponseBody> call = new RetrofitClient(BaseUrl.MAIN).getHttpRaw().getChannelFeatured(id);
        new ApiHelper<>(call).setOnResult(s -> {
            Document document = Jsoup.parse(s.string());
            Elements select = document.select("#container > div.detail-panels > div.detail-select-panel > div > div.year-selector");
            List<Node> nodes = select.get(0).childNodes();

            HashMap<Integer, String> filter = new LinkedHashMap<>(Map.of(0, "全部"));
            for (int i = 1; i < nodes.size(); i++) {
                String year = Objects.requireNonNull(nodes.get(i).firstChild()).toString();
                filter.put(Integer.parseInt(year.replaceAll("年", "")), year);
            }

            ChannelFeaturedFilterAdapter filterAdapter = new ChannelFeaturedFilterAdapter(context).setOnFilterCallback(integerStringEntry -> {
                ChannelFeaturedFragment.this.type = integerStringEntry.getKey();
                reload();
            });
            filterAdapter.appendHead(filter.entrySet());
            binding.filter.subTab.setAdapter(filterAdapter);
        }).doIt();
    }
}
