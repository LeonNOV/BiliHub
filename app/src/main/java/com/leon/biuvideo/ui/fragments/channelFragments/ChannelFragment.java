package com.leon.biuvideo.ui.fragments.channelFragments;

import android.view.View;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.home.channel.Channel;
import com.leon.biuvideo.beans.home.channel.ChannelData;
import com.leon.biuvideo.databinding.FragmentChannelBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.channel.ChannelAdapter;
import com.leon.biuvideo.utils.PaginationLoader;

import java.util.Locale;

/**
 * @Author Leon
 * @Time 2022/07/15
 * @Desc
 */
public class ChannelFragment extends BaseLazyFragment<FragmentChannelBinding> {
    private final Channel.Data.Category category;
    private int pageNum = 0;
    private PaginationLoader<ChannelData, ChannelData.Data.ArchiveChannel> loader;

    public ChannelFragment(Channel.Data.Category category) {
        this.category = category;
    }

    @Override
    public FragmentChannelBinding getViewBinding() {
        return FragmentChannelBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        if (category.getId() == -1) {
            binding.count.setVisibility(View.GONE);
        } else {
            binding.count.setText(String.format(Locale.CHINESE, "共%s个频道", category.getChannelCount()));

            HttpApi httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
            loader = new PaginationLoader<>(binding.content, new ChannelAdapter(context));
            loader.closeRefresh();
            loader.setGuide(channelData -> channelData.getData().getArchiveChannels());
            loader.setUpdateInterface(loadType -> loader.setObservable(httpApi.getChannelData(category.getId(), getOffset())));
        }
    }

    @Override
    protected void onLazyLoad() {
        if (loader != null) {
            loader.firstObtain();
        }
    }

    // todo 存在问题，待处理
    private int getOffset() {
        if (pageNum == 0) {
            ++pageNum;
            return 0;
        } else {
            return ((++pageNum - 1) * 6) + 1;
        }
    }
}
