package com.leon.biuvideo.ui.adapters.partition;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseActivity.ActivityManager;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.partition.PartitionData;
import com.leon.biuvideo.databinding.ItemPartitionVideoBinding;
import com.leon.biuvideo.ui.activities.publicActivities.VideoActivity;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/15
 * @Desc
 */
public class PartitionDataAdapter extends BaseViewBindingAdapter<PartitionData.Result, ItemPartitionVideoBinding> {
    public PartitionDataAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemPartitionVideoBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemPartitionVideoBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_partition_video, parent, false));
    }

    @Override
    protected void onBindViewHolder(PartitionData.Result data, ItemPartitionVideoBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM, data.getBvid())));

        ViewUtils.setImg(context, binding.cover, "https:" + data.getPic());
        binding.play.setText(ValueUtils.generateCN(Integer.parseInt(data.getPlay())));
        binding.extra.setText(ValueUtils.lengthGenerate(data.getDuration()));
        binding.title.setText(data.getTitle());
        binding.author.setText(data.getAuthor());
    }
}
