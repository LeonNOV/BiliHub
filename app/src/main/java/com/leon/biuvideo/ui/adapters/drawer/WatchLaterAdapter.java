package com.leon.biuvideo.ui.adapters.drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseActivity.ActivityManager;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.account.WatchLater;
import com.leon.biuvideo.databinding.ItemVideoBinding;
import com.leon.biuvideo.ui.activities.publicActivities.VideoActivity;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Locale;

/**
 * @Author Leon
 * @Time 2022/07/12
 * @Desc
 */
public class WatchLaterAdapter extends BaseViewBindingAdapter<WatchLater.Data.WatchLaterData, ItemVideoBinding> {

    public WatchLaterAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemVideoBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemVideoBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video, parent, false));
    }

    @Override
    protected void onBindViewHolder(WatchLater.Data.WatchLaterData data, ItemVideoBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> ActivityManager.startActivity(context, VideoActivity.class));

        ViewUtils.setImg(context, binding.cover, data.getPic());
        if (data.getVideos() > 1) {
            binding.extra.setText(String.format(Locale.CHINESE, "%dP", data.getVideos()));
        } else {
            StringBuilder duration = new StringBuilder();
            if (data.getProgress() > 0) {
                duration.append(ValueUtils.lengthGenerate(data.getProgress())).append("/");
            }

            binding.extra.setText(duration.append(ValueUtils.lengthGenerate(data.getDuration())).toString());
        }
        binding.title.setText(data.getTitle());
        binding.author.setText(data.getOwner().getName());
        binding.view.setText(ValueUtils.generateCN(data.getStat().getView()));
        binding.danmaku.setText(ValueUtils.generateCN(data.getStat().getDanmaku()));
    }
}
