package com.leon.bilihub.ui.adapters.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.beans.publicBeans.resources.video.VideoSpeed;
import com.leon.bilihub.databinding.ItemVideoSpeedBinding;
import com.leon.bilihub.ui.widget.player.PlayerController;
import com.leon.bilihub.wraps.VideoSpeedWrap;

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
public class VideoSpeedAdapter extends ViewBindingAdapter<VideoSpeed, ItemVideoSpeedBinding> {

    private PlayerController.OnSelectedListener<VideoSpeedWrap> onSelectedListener;
    private int selectedPosition;

    public VideoSpeedAdapter(Context context, int initSelectedPosition) {
        super(context);

        this.selectedPosition = initSelectedPosition;
    }

    @Override
    protected ItemVideoSpeedBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemVideoSpeedBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video_speed, parent, false));
    }

    @Override
    protected void onBindViewHolder(VideoSpeed data, ItemVideoSpeedBinding binding, int position) {
        binding.getRoot().setOnClickListener(view -> {
            if (onSelectedListener != null && selectedPosition != position) {
                data.setSelected(true);

                onSelectedListener.onSelected(new VideoSpeedWrap(data.getSpeed(), selectedPosition));
                selectedPosition = position;

                notifyItemChanged(position);
            }
        });

        binding.speed.setText(data.getSpeedStr());
        binding.speed.setTextColor(data.getSelected() ? context.getColor(R.color.pink) : context.getColor(R.color.white));
    }

    public void setOnSelectedListener(PlayerController.OnSelectedListener<VideoSpeedWrap> onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }
}
