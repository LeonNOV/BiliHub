package com.leon.biuvideo.ui.adapters.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoSpeed;
import com.leon.biuvideo.databinding.ItemVideoSpeedBinding;
import com.leon.biuvideo.ui.widget.player.PlayerController;
import com.leon.biuvideo.wraps.VideoSpeedWrap;

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
public class VideoSpeedAdapter extends BaseViewBindingAdapter<VideoSpeed, ItemVideoSpeedBinding> {

    private PlayerController.OnSelectedListener<VideoSpeedWrap> onSelectedListener;
    private int selectedPosition;

    public VideoSpeedAdapter(Context context, int selectedPosition) {
        super(context);
        this.selectedPosition = selectedPosition;
    }

    @Override
    protected ItemVideoSpeedBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemVideoSpeedBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video_speed, parent, false));
    }

    @Override
    protected void onBindViewHolder(VideoSpeed data, ItemVideoSpeedBinding binding, int position) {
        binding.getRoot().setOnClickListener(view -> {
            if (onSelectedListener != null && selectedPosition != position) {
                onSelectedListener.onSelected(new VideoSpeedWrap(data.getSpeed(), selectedPosition));

                selectedPosition = position;
                binding.speed.setTextColor(context.getColor(R.color.BiliBili_pink));
            }
        });

        if (selectedPosition == position) {
            binding.speed.setTextColor(context.getColor(R.color.BiliBili_pink));
        }

        binding.speed.setText(data.getSpeedStr());
    }

    public void setOnSelectedListener(PlayerController.OnSelectedListener<VideoSpeedWrap> onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }
}