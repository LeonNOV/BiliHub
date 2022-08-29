package com.leon.bilihub.ui.adapters.channel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.bilihub.beans.home.channel.ChannelData;
import com.leon.bilihub.databinding.ItemChannelBinding;
import com.leon.bilihub.ui.activities.drawerFunction.channel.ChannelDetailActivity;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

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
    protected ItemChannelBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemChannelBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_channel, parent, false));
    }

    @Override
    protected void onBindViewHolder(ChannelData.Data.ArchiveChannel data, ItemChannelBinding binding, int position) {
        binding.container.setOnClickListener(v -> startActivity(ChannelDetailActivity.class, Map.of(ChannelDetailActivity.PARAM_ID, data.getId())));

        ViewUtils.setImg(context, binding.face, data.getCover());
        binding.name.setText(data.getName());
        binding.extraA.setText(String.format(Locale.CHINESE, "%s个视频", data.getArchiveCount()));
        binding.extraB.setText(String.format(Locale.CHINESE, "%s个精选视频", ValueUtils.generateCN(data.getFeaturedCount())));
    }
}
