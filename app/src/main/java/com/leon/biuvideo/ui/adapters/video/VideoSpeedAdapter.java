package com.leon.biuvideo.ui.adapters.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoSpeed;
import com.leon.biuvideo.databinding.ItemVideoSpeedBinding;
import com.leon.biuvideo.utils.ViewUtils;
import com.leon.biuvideo.wraps.VideoEpisodeWrap;

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
public class VideoSpeedAdapter extends BaseViewBindingAdapter<VideoSpeed, ItemVideoSpeedBinding> {
    private final VideoEpisodeWrap episodeWrap;

    public VideoSpeedAdapter(Context context) {
        super(context);
        episodeWrap = new ViewModelProvider(ViewUtils.scanForActivity(context)).get(VideoEpisodeWrap.class);
    }

    @Override
    protected ItemVideoSpeedBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemVideoSpeedBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video_speed, parent, false));
    }

    @Override
    protected void onBindViewHolder(VideoSpeed data, ItemVideoSpeedBinding binding, int position) {
        binding.getRoot().setOnClickListener(view -> episodeWrap.getSpeed().setValue(data));

        binding.speed.setText(data.getSpeedStr());
    }
}
