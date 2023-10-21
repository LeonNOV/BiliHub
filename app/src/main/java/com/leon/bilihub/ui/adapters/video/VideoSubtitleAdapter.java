package com.leon.bilihub.ui.adapters.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.beans.publicBeans.resources.video.VideoDetail;
import com.leon.bilihub.databinding.ItemTextBinding;
import com.leon.bilihub.ui.widget.player.PlayerController;

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
public class VideoSubtitleAdapter extends ViewBindingAdapter<VideoDetail.Data.View.Subtitle.Data, ItemTextBinding> {
    private PlayerController.OnSelectedListener<VideoDetail.Data.View.Subtitle.Data> onSelectedListener;
    private int selectedPosition;

    public VideoSubtitleAdapter(Context context, int initSelectedPosition) {
        super(context);

        this.selectedPosition = initSelectedPosition;
    }

    @Override
    protected ItemTextBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemTextBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_text, parent, false));
    }

    @Override
    protected void onBindViewHolder(VideoDetail.Data.View.Subtitle.Data data, ItemTextBinding binding, int position) {
        binding.getRoot().setOnClickListener(view -> {
            if (onSelectedListener != null && selectedPosition != position) {
                data.setSelected(true);

                onSelectedListener.onSelected(data);
                selectedPosition = position;

                notifyItemChanged(position);
            }
        });

        binding.content.setText(data.getLanDoc());
        binding.content.setTextColor(data.getSelected() ? context.getColor(R.color.pink) : context.getColor(R.color.white));
    }

    public void setOnSelectedListener(PlayerController.OnSelectedListener<VideoDetail.Data.View.Subtitle.Data> onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }
}
