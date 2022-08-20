package com.leon.biuvideo.ui.adapters.video;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoDetail;
import com.leon.biuvideo.databinding.ItemVideoEpisodeBinding;
import com.leon.biuvideo.ui.widget.player.PlayerController;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;
import com.leon.biuvideo.model.VideoEpisodeModel;
import com.leon.biuvideo.wraps.VideoQualityWrap;

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
public class VideoEpisodeAdapter extends BaseViewBindingAdapter<VideoDetail.Data.View.Page, ItemVideoEpisodeBinding> {
    private PlayerController.OnSelectedListener<Integer> onSelectedListener;
    private final VideoEpisodeModel episodeWrap;
    private int selectedPosition = 0;

    public VideoEpisodeAdapter(Context context) {
        super(context);
        episodeWrap = new ViewModelProvider(ViewUtils.scanForActivity(context)).get(VideoEpisodeModel.class);
    }

    @Override
    protected ItemVideoEpisodeBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemVideoEpisodeBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video_episode, parent, false));
    }

    @Override
    protected void onBindViewHolder(VideoDetail.Data.View.Page data, ItemVideoEpisodeBinding binding, int position) {
        binding.getRoot().setOnClickListener(view -> {
            if (onSelectedListener != null && selectedPosition != position) {
                episodeWrap.getTitle().setValue(data.getPart());
                episodeWrap.getResource().setValue(data.getCid());
                binding.getRoot().setSelected(true);
                binding.title.setTextColor(context.getColor(R.color.blue));
                binding.duration.setTextColor(context.getColor(R.color.blue));
                onSelectedListener.onSelected(selectedPosition);

                selectedPosition = position;
            }
        });

        if (selectedPosition == position) {
            binding.getRoot().setSelected(true);
            binding.title.setTextColor(context.getColor(R.color.blue));
            binding.duration.setTextColor(context.getColor(R.color.blue));
        }

        binding.title.setText(data.getPart());
        binding.duration.setText(ValueUtils.toMediaDuration(data.getDuration()));
    }

    public void setOnSelectedListener(PlayerController.OnSelectedListener<Integer> onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }
}
