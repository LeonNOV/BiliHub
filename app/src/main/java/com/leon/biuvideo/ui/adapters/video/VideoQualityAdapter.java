package com.leon.biuvideo.ui.adapters.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoQuality;
import com.leon.biuvideo.databinding.ItemVideoQualityBinding;
import com.leon.biuvideo.utils.ViewUtils;
import com.leon.biuvideo.wraps.VideoEpisodeWrap;

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
public class VideoQualityAdapter extends BaseViewBindingAdapter<VideoQuality, ItemVideoQualityBinding> {
    private final VideoEpisodeWrap episodeWrap;

    public VideoQualityAdapter(Context context) {
        super(context);
        episodeWrap = new ViewModelProvider(ViewUtils.scanForActivity(context)).get(VideoEpisodeWrap.class);
    }

    @Override
    protected ItemVideoQualityBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemVideoQualityBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video_quality, parent, false));
    }

    @Override
    protected void onBindViewHolder(VideoQuality data, ItemVideoQualityBinding binding, int position) {
        binding.getRoot().setOnClickListener(view -> {
            if (data.isOrdinary()) {
                episodeWrap.getQuality().setValue(data);
            } else {
                // do something
            }
        });

        binding.quality.setText(data.getQualityStr());
        if (data.getExtra() != null) {
            binding.extra.setVisibility(View.VISIBLE);
            binding.extra.setText(data.getExtra());
        }
    }
}
