package com.leon.bilihub.ui.adapters.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.beans.publicBeans.resources.video.VideoDetail;
import com.leon.bilihub.databinding.ItemVideoBinding;
import com.leon.bilihub.ui.activities.publicActivities.VideoActivity;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/08/11
 * @Desc
 */
public class MediaInfoRecommendAdapter extends ViewBindingAdapter<VideoDetail.Data.Related, ItemVideoBinding> {
    public MediaInfoRecommendAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemVideoBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemVideoBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video, parent, false));
    }

    @Override
    protected void onBindViewHolder(VideoDetail.Data.Related data, ItemVideoBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_TYPE, VideoActivity.TYPE_VIDEO,
                VideoActivity.PARAM_ID, data.getBvid())));

        ViewUtils.setImg(context, binding.cover, data.getPic());
        binding.extra.setText(ValueUtils.toMediaDuration(data.getDuration()));
        binding.title.setText(data.getTitle());
        binding.author.setText(data.getOwner().getName());
        binding.view.setText(ValueUtils.generateCN(data.getStat().getView()));
        binding.danmaku.setText(ValueUtils.generateCN(data.getStat().getDanmaku()));
    }
}
