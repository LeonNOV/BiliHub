package com.leon.bilihub.ui.adapters.drawer.channel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.databinding.ItemVideoBBinding;
import com.leon.bilihub.beans.home.channel.ChannelDetailFeatured;
import com.leon.bilihub.ui.activities.publicActivities.VideoActivity;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/16
 * @Desc
 */
public class ChannelFeaturedAdapter extends ViewBindingAdapter<ChannelDetailFeatured.Data.Archive, ItemVideoBBinding> {
    public ChannelFeaturedAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemVideoBBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemVideoBBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video_b, parent, false));
    }

    @Override
    protected void onBindViewHolder(ChannelDetailFeatured.Data.Archive data, ItemVideoBBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_TYPE, VideoActivity.TYPE_VIDEO,
                VideoActivity.PARAM_ID, data.getBvid())));

        ViewUtils.setImg(context, binding.cover, data.getCover());
        binding.play.setText(data.getViewCount());
        binding.danmaku.setText(ValueUtils.generateCN(data.getDanmaku()));
        binding.extra.setText(data.getDuration());
        binding.title.setText(data.getName());
        binding.author.setText(data.getAuthorName());
    }
}
