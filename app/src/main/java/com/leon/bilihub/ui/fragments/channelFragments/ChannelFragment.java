package com.leon.bilihub.ui.fragments.channelFragments;

import android.view.View;

import com.leon.bilihub.base.baseFragment.BaseLazyFragment;
import com.leon.bilihub.beans.home.channel.ChannelCategory;
import com.leon.bilihub.beans.home.channel.ChannelData;
import com.leon.bilihub.beans.home.channel.UserChannelCategory;
import com.leon.bilihub.databinding.FragmentChannelBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.drawer.channel.ChannelAdapter;
import com.leon.bilihub.ui.adapters.drawer.channel.UserChannelAdapter;
import com.leon.bilihub.ui.widget.loader.PaginationLoader;
import com.leon.bilihub.ui.widget.loader.RecyclerViewLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @Author Leon
 * @Time 2022/07/15
 * @Desc
 */
public class ChannelFragment extends BaseLazyFragment<FragmentChannelBinding> {
    private final ChannelCategory.Data.Category category;
    private String offset;
    private PaginationLoader<ChannelData, ChannelData.Data.ArchiveChannel> loader;

    public ChannelFragment(ChannelCategory.Data.Category category) {
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
            binding.userContent.setVisibility(View.VISIBLE);

            RecyclerViewLoader<UserChannelCategory, UserChannelCategory.Data.NormalChannel> loader = new RecyclerViewLoader<>(binding.userContent, new UserChannelAdapter(context));
            loader.setGuide(userChannelCategory -> {
                        List<UserChannelCategory.Data.NormalChannel> result = new ArrayList<>();
                        for (UserChannelCategory.Data.StickChannel channel : userChannelCategory.getData().getStickChannels()) {
                            result.add(new UserChannelCategory.Data.NormalChannel(
                                    0, "", channel.getCover(), 0,
                                    channel.getId(), channel.getName(), channel.getSubscribedCount(),
                                    null, ""
                            ));
                        }

                        for (UserChannelCategory.Data.NormalChannel channel : userChannelCategory.getData().getNormalChannels()) {
                            if (channel.getCtype() == 2) {
                                result.add(channel);
                            }
                        }

                        return result;
                    })
                    .setObservable(new RetrofitClient(BaseUrl.API, context).getHttpApi().getUserChannelCategory())
                    .obtain(false);
        } else {
            binding.count.setText(String.format(Locale.CHINESE, "共%s个频道", category.getChannelCount()));

            HttpApi httpApi = new RetrofitClient(BaseUrl.API, context).getHttpApi();
            loader = new PaginationLoader<>(binding.content, new ChannelAdapter(context));
            loader.setGuide(channelData -> {
                offset = channelData.getData().getOffset();
                return channelData.getData().getArchiveChannels();
            });
            loader.setUpdateInterface(loadType -> httpApi.getChannelData(category.getId(), offset));
        }
    }

    @Override
    protected void onLazyLoad() {
        if (loader != null) {
            loader.firstObtain();
        }
    }
}
