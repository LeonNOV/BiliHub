package com.leon.biuvideo.ui.adapters.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoQuality;
import com.leon.biuvideo.databinding.ItemVideoQualityBinding;
import com.leon.biuvideo.ui.widget.player.PlayerController;
import com.leon.biuvideo.wraps.VideoQualityWrap;

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
public class VideoQualityAdapter extends BaseViewBindingAdapter<VideoQuality, ItemVideoQualityBinding> {
    private PlayerController.OnSelectedListener<VideoQualityWrap> onSelectedListener;
    private int selectedPosition;

    public VideoQualityAdapter(Context context, int selectedPosition) {
        super(context);
        this.selectedPosition = selectedPosition;
    }

    @Override
    protected ItemVideoQualityBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemVideoQualityBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video_quality, parent, false));
    }

    @Override
    protected void onBindViewHolder(VideoQuality data, ItemVideoQualityBinding binding, int position) {
        binding.getRoot().setOnClickListener(view -> {
            if (onSelectedListener != null && selectedPosition != position) {
                if (data.isOrdinary()) {
                    binding.quality.setTextColor(context.getColor(R.color.BiliBili_pink));
                    onSelectedListener.onSelected(new VideoQualityWrap(data.getQuality(), selectedPosition));
                    selectedPosition = position;
                } else {
                    // do something
                }
            }
        });

        if (selectedPosition == position) {
            binding.quality.setTextColor(context.getColor(R.color.BiliBili_pink));
        }

        binding.quality.setText(data.getQualityStr());
        if (data.getExtra() != null) {
            binding.extra.setVisibility(View.VISIBLE);
            binding.extra.setText(data.getExtra());
        }
    }

    public void setOnSelectedListener(PlayerController.OnSelectedListener<VideoQualityWrap> onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }
}
