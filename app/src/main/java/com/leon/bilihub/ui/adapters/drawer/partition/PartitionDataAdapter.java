package com.leon.bilihub.ui.adapters.drawer.partition;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.beans.partition.PartitionData;
import com.leon.bilihub.databinding.ItemPartitionVideoBinding;
import com.leon.bilihub.ui.activities.publicActivities.VideoActivity;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/15
 * @Desc
 */
public class PartitionDataAdapter extends ViewBindingAdapter<PartitionData.Result, ItemPartitionVideoBinding> {
    public PartitionDataAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemPartitionVideoBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemPartitionVideoBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_partition_video, parent, false));
    }

    @Override
    protected void onBindViewHolder(PartitionData.Result data, ItemPartitionVideoBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_TYPE, VideoActivity.TYPE_VIDEO,
                VideoActivity.PARAM_ID, data.getBvid())));

        ViewUtils.setImg(context, binding.cover, "https:" + data.getPic());
        binding.play.setText(ValueUtils.generateCN(Integer.parseInt(data.getPlay())));
        binding.extra.setText(ValueUtils.toMediaDuration(data.getDuration()));
        binding.title.setText(data.getTitle());
        binding.author.setText(data.getAuthor());
    }
}
