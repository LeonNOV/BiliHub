package com.leon.bilihub.ui.adapters.drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.beans.account.WatchLater;
import com.leon.bilihub.databinding.ItemVideoBinding;
import com.leon.bilihub.ui.activities.publicActivities.VideoActivity;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Locale;
import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/12
 * @Desc
 */
public class WatchLaterAdapter extends ViewBindingAdapter<WatchLater.Data.WatchLaterData, ItemVideoBinding> {

    public WatchLaterAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemVideoBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemVideoBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video, parent, false));
    }

    @Override
    protected void onBindViewHolder(WatchLater.Data.WatchLaterData data, ItemVideoBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_TYPE, VideoActivity.TYPE_VIDEO,
                VideoActivity.PARAM_ID, data.getBvid())));

        ViewUtils.setImg(context, binding.cover, data.getPic());
        if (data.getVideos() > 1) {
            binding.extra.setText(String.format(Locale.CHINESE, "%dP", data.getVideos()));
        } else {
            StringBuilder duration = new StringBuilder();
            if (data.getProgress() > 0) {
                duration.append(ValueUtils.toMediaDuration(data.getProgress())).append("/");
            }

            binding.extra.setText(duration.append(ValueUtils.toMediaDuration(data.getDuration())).toString());
        }
        binding.title.setText(data.getTitle());
        binding.author.setText(data.getOwner().getName());
        binding.view.setText(ValueUtils.generateCN(data.getStat().getView()));
        binding.danmaku.setText(ValueUtils.generateCN(data.getStat().getDanmaku()));
    }
}
