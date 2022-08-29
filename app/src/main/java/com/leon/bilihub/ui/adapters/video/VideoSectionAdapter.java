package com.leon.bilihub.ui.adapters.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.bilihub.beans.publicBeans.resources.video.VideoDetail;
import com.leon.bilihub.databinding.ItemVideoSectionBinding;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Locale;

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
public class VideoSectionAdapter extends BaseViewBindingAdapter<VideoDetail.Data.View.UgcSeason.Section, ItemVideoSectionBinding> {
    public VideoSectionAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemVideoSectionBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemVideoSectionBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video_section, parent, false));
    }

    @Override
    protected void onBindViewHolder(VideoDetail.Data.View.UgcSeason.Section data, ItemVideoSectionBinding binding, int position) {
        binding.countContainer.setOnClickListener(view -> binding.content.setVisibility(binding.content.getVisibility() == View.GONE ? View.VISIBLE : View.GONE));

        binding.count.setText(String.format(Locale.CHINESE,"%s-%d个内容", data.getTitle(), data.getEpisodes().size()));

        VideoSectionEpisodeAdapter adapter = new VideoSectionEpisodeAdapter(context);
        adapter.appendHead(data.getEpisodes());
        ViewUtils.linkAdapter(binding.content, adapter);
    }
}
