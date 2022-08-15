package com.leon.biuvideo.ui.adapters.channel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.databinding.ItemVideoBBinding;
import com.leon.biuvideo.beans.home.channel.ChannelDetailMultiple;
import com.leon.biuvideo.ui.activities.publicActivities.VideoActivity;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/16
 * @Desc
 */
public class ChannelMultipleTopAdapter extends BaseViewBindingAdapter<ChannelDetailMultiple.Data.Archive.Item, ItemVideoBBinding> {
    public ChannelMultipleTopAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemVideoBBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemVideoBBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video_b, parent, false));
    }

    @Override
    protected void onBindViewHolder(ChannelDetailMultiple.Data.Archive.Item data, ItemVideoBBinding binding, int position) {
        ViewGroup.LayoutParams layoutParams = binding.getRoot().getLayoutParams();
        layoutParams.width = context.getResources().getDimensionPixelSize(R.dimen.ItemVideoW);
        binding.getRoot().setLayoutParams(layoutParams);

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
