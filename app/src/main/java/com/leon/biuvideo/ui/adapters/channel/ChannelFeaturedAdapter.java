package com.leon.biuvideo.ui.adapters.channel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.databinding.ItemVideoBBinding;
import com.leon.biuvideo.beans.home.channel.ChannelDetailFeatured;
import com.leon.biuvideo.ui.activities.publicActivities.VideoActivity;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/16
 * @Desc
 */
public class ChannelFeaturedAdapter extends BaseViewBindingAdapter<ChannelDetailFeatured.Data.Archive, ItemVideoBBinding> {
    public ChannelFeaturedAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemVideoBBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemVideoBBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video_b, parent, false));
    }

    @Override
    protected void onBindViewHolder(ChannelDetailFeatured.Data.Archive data, ItemVideoBBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_BVID, data.getBvid())));

        ViewUtils.setImg(context, binding.cover, data.getCover());
        binding.play.setText(data.getViewCount());
        binding.danmaku.setText(ValueUtils.generateCN(data.getDanmaku()));
        binding.extra.setText(data.getDuration());
        binding.title.setText(data.getName());
        binding.author.setText(data.getAuthorName());
    }
}
