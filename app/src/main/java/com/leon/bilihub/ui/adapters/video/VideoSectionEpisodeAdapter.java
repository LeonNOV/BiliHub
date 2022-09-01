package com.leon.bilihub.ui.adapters.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.beans.publicBeans.resources.video.VideoDetail;
import com.leon.bilihub.databinding.ItemVideoEpisodeBinding;
import com.leon.bilihub.utils.ValueUtils;

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
public class VideoSectionEpisodeAdapter extends ViewBindingAdapter<VideoDetail.Data.View.UgcSeason.Section.Episode, ItemVideoEpisodeBinding> {
    public VideoSectionEpisodeAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemVideoEpisodeBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemVideoEpisodeBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video_episode, parent, false));
    }

    @Override
    protected void onBindViewHolder(VideoDetail.Data.View.UgcSeason.Section.Episode data, ItemVideoEpisodeBinding binding, int position) {
        binding.getRoot().setOnClickListener(view -> {});

        binding.title.setText(data.getTitle());
        binding.duration.setText(ValueUtils.toMediaDuration(data.getPage().getDuration()));
    }
}
