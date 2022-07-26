package com.leon.biuvideo.ui.adapters.channel;

import android.content.Context;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.home.channel.ChannelData;
import com.leon.biuvideo.databinding.ItemChannelBinding;
import com.leon.biuvideo.ui.activities.drawerFunction.channel.ChannelDetailActivity;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Locale;
import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/15
 * @Desc
 */
public class ChannelAdapter extends BaseViewBindingAdapter<ChannelData.Data.ArchiveChannel, ItemChannelBinding> {
    public ChannelAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayout(int viewType) {
        return R.layout.item_channel;
    }

    @Override
    protected ItemChannelBinding getItemViewBinding() {
        return ItemChannelBinding.bind(itemView);
    }

    @Override
    protected void onBindViewHolder(ChannelData.Data.ArchiveChannel data, ItemChannelBinding binding, int position) {
        binding.container.setOnClickListener(v -> startActivity(ChannelDetailActivity.class, Map.of(ChannelDetailActivity.PARAM_ID, data.getId())));

        ViewUtils.setImg(context, binding.face, data.getCover());
        binding.name.setText(data.getName());
        binding.extraA.setText(String.format(Locale.CHINESE, "%s个视频", data.getArchiveCount()));
        binding.extraB.setText(String.format(Locale.CHINESE, "%s个精选视频", ValueUtils.generateCN(data.getFeaturedCount())));
        binding.subscribe.setText(String.format(Locale.CHINESE, "订阅 %s", ValueUtils.generateCN(data.getSubscribedCount())));
    }
}
