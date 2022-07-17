package com.leon.biuvideo.ui.fragments.channelFragments;

import android.util.Log;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.databinding.FragmentChannelMultipleBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.beans.home.channel.ChannelDetailMultiple;
import com.leon.biuvideo.ui.adapters.channel.ChannelMultipleContentAdapter;
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

        contentAdapter = new ChannelMultipleContentAdapter(context);
        httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
        loader = new PaginationLoader<>(binding.content, contentAdapter);

        loader.closeRefresh();
        loader.setLayoutManager(new GridLayoutManager(context, 2));
        loader.setGuide(channelDetailMultiple -> {
            offset = channelDetailMultiple.getData().getOffset();

            //todo 由于第一页的第一项为该频道排行榜，此阶段并不打算加入该功能，所以在这里直接移除掉
            //后期加入需用到com.leon.biuvideo.ui.adapters.channel.ChannelMultipleTopAdapter
            if (channelDetailMultiple.getData().getList().get(0).getItems() != null) {
                channelDetailMultiple.getData().getList().remove(0);
            }
            return channelDetailMultiple.getData().getList();
        });

        reload();
    }

    private void changeSelectedColor(int index) {
        if (selectedIndex != index) {
            textViews.get(selectedIndex).setTextColor(context.getColor(R.color.infoColor));
            textViews.get(index).setTextColor(context.getColor(R.color.BiliBili_pink));

            selectedIndex = index;
            reload();
        }
    }

    @Override
    protected void onLazyLoad() {
    }

    // todo 重载数据错误待修复
    private void reload() {
        offset = null;
        contentAdapter.removeAll();
        loader
                .setUpdateInterface(loadType -> {
                    loader.setObservable(httpApi.getChannelDetailMultiple(channelId, sort, offset));
                    Log.d(TAG, "reload: id=" + channelId + ";sort=" + sort + ";offset=" + offset);
                })
                .obtain();
    }
}
