package com.leon.biuvideo.ui.adapters.channel;

import android.content.Context;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.home.channel.ChannelData;
import com.leon.biuvideo.databinding.ItemChannelBinding;
import com.leon.biuvideo.ui.activities.drawerFunction.channel.ChannelDetailActivity;

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
        binding.getRoot().setOnClickListener(v -> startActivity(ChannelDetailActivity.class, Map.of(ChannelDetailActivity.PARAM_A, String.valueOf(data.getId()),
                ChannelDetailActivity.PARAM_B, data.getName())));

        binding.channelName.setText(data.getName());
    }
}
