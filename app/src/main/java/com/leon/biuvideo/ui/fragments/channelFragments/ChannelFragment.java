package com.leon.biuvideo.ui.fragments.channelFragments;

import android.view.View;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.home.channel.ChannelCategory;
import com.leon.biuvideo.beans.home.channel.ChannelData;
import com.leon.biuvideo.beans.home.channel.UserChannelCategory;
import com.leon.biuvideo.databinding.FragmentChannelBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.http.TestValue;
import com.leon.biuvideo.ui.adapters.channel.ChannelAdapter;
import com.leon.biuvideo.ui.adapters.channel.UserChannelAdapter;
import com.leon.biuvideo.utils.PaginationLoader;
import com.leon.biuvideo.utils.RecyclerViewLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
                    .setObservable(new RetrofitClient(BaseUrl.API, Map.of(HttpApi.COOKIE, TestValue.TEST_COOKIE)).getHttpApi().getUserChannelCategory())
                    .obtain(false);
        } else {
            binding.count.setText(String.format(Locale.CHINESE, "共%s个频道", category.getChannelCount()));

            HttpApi httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
            loader = new PaginationLoader<>(binding.content, new ChannelAdapter(context));
            loader.closeRefresh();
            loader.setGuide(channelData -> {
                offset = channelData.getData().getOffset();
                return channelData.getData().getArchiveChannels();
            });
            loader.setUpdateInterface(loadType -> loader.setObservable(httpApi.getChannelData(category.getId(), offset)));
        }
    }

    @Override
    protected void onLazyLoad() {
        if (loader != null) {
            loader.firstObtain();
        }
    }
}
