package com.leon.biuvideo.ui.fragments.channelFragments;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.databinding.FragmentChannelMultipleBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.beans.home.channel.ChannelDetailMultiple;
import com.leon.biuvideo.ui.adapters.channel.ChannelMultipleContentAdapter;
import com.leon.biuvideo.ui.adapters.channel.ChannelMultipleTopAdapter;
import com.leon.biuvideo.utils.PaginationLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Leon
 * @Time 2022/07/16
 * @Desc
 */
public class ChannelMultipleFragment extends BaseLazyFragment<FragmentChannelMultipleBinding> {
    private final String channelId;
    private String offset;
    private int selectedIndex = 0;
    private String sort = "hot";

    private HttpApi httpApi;
    private PaginationLoader<ChannelDetailMultiple, ChannelDetailMultiple.Data.Archive> loader;
    private ChannelMultipleContentAdapter contentAdapter;
    private List<AppCompatTextView> textViews;

    public ChannelMultipleFragment(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public FragmentChannelMultipleBinding getViewBinding() {
        return FragmentChannelMultipleBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        textViews = new ArrayList<>(3);
        textViews.add(binding.sortHot);
        textViews.add(binding.sortView);
        textViews.add(binding.sortNew);
        binding.sortHot.setOnClickListener(v -> {
            sort = "hot";
            changeSelectedColor(0);
        });
        binding.sortView.setOnClickListener(v -> {
            sort = "view";
            changeSelectedColor(1);
        });
        binding.sortNew.setOnClickListener(v -> {
            sort = "new";
            changeSelectedColor(2);
        });

        ChannelMultipleTopAdapter topAdapter = new ChannelMultipleTopAdapter(context);
        binding.topContent.setAdapter(topAdapter);
        binding.topContent.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        binding.topContent.setMotionEventSplittingEnabled(false);
        binding.topContent.setNestedScrollingEnabled(false);
        binding.topContent.setHasFixedSize(true);

        contentAdapter = new ChannelMultipleContentAdapter(context);

        httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
        loader = new PaginationLoader<>(binding.content, contentAdapter);
        loader.closeRefresh();
        loader.setLayoutManager(new GridLayoutManager(context, 2));
        loader.setGuide(channelDetailMultiple -> {
            offset = channelDetailMultiple.getData().getOffset();

            List<ChannelDetailMultiple.Data.Archive.Item> items = channelDetailMultiple.getData().getList().get(0).getItems();
            if (items != null) {
                topAdapter.appendHead(items);
                channelDetailMultiple.getData().getList().remove(0);
            }
            return channelDetailMultiple.getData().getList();
        });

        reload();
    }

    private void changeSelectedColor(int index) {
        if (selectedIndex != index) {
            textViews.get(selectedIndex).setTextColor(context.getColor(R.color.infoColor));
            textViews.get(index).setTextColor(context.getColor(R.color.infoColor));

            selectedIndex = index;
            reload();
        }
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }

    // todo 重置没有进行自动加载，待修复
    private void reload() {
        offset = null;
        contentAdapter.removeAll();
        loader.setUpdateInterface(loadType -> loader.setObservable(httpApi.getChannelDetailMultiple(channelId, sort, offset)));
    }
}
