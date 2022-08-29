package com.leon.bilihub.ui.adapters.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.bilihub.beans.publicBeans.resources.video.VideoDetail;
import com.leon.bilihub.databinding.ItemVideoEpisodeBinding;
import com.leon.bilihub.model.VideoPlayerModel;
import com.leon.bilihub.ui.widget.player.PlayerController;
import com.leon.bilihub.utils.PreferenceUtils;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;
import com.leon.bilihub.wraps.VideoResourceWrap;

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
public class VideoEpisodeAdapter extends BaseViewBindingAdapter<VideoDetail.Data.View.Page, ItemVideoEpisodeBinding> {
    private PlayerController.OnSelectedListener<Integer> onSelectedListener;
    private final VideoPlayerModel videoPlayerModel;
    private int selectedPosition = 0;

    public VideoEpisodeAdapter(Context context) {
        super(context);
        videoPlayerModel = new ViewModelProvider(ViewUtils.scanForActivity(context)).get(VideoPlayerModel.class);
    }

    @Override
    protected ItemVideoEpisodeBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemVideoEpisodeBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video_episode, parent, false));
    }

    @Override
    protected void onBindViewHolder(VideoDetail.Data.View.Page data, ItemVideoEpisodeBinding binding, int position) {
        binding.getRoot().setOnClickListener(view -> {
            if (onSelectedListener != null && selectedPosition != position) {
                videoPlayerModel.getVideoTitleDisplay().setValue(data.getPart());
                videoPlayerModel.getVideoResource().setValue(new VideoResourceWrap(null, data.getCid(), PreferenceUtils.getVideoQuality(context)));

                VideoDetail.Data.View.Page.ItemState itemState = data.getItemState();
                itemState.setItemColor(context.getColor(R.color.blue));
                itemState.setSelected(true);

                onSelectedListener.onSelected(selectedPosition);
                selectedPosition = position;

                notifyItemChanged(position);
            }
        });

        VideoDetail.Data.View.Page.ItemState itemState = data.getItemState();
        binding.getRoot().setSelected(itemState.getSelected());
        binding.title.setTextColor(itemState.getItemColor());
        binding.duration.setTextColor(itemState.getItemColor());

        binding.title.setText(data.getPart());
        binding.duration.setText(ValueUtils.toMediaDuration(data.getDuration()));
    }

    public void setOnSelectedListener(PlayerController.OnSelectedListener<Integer> onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }
}
