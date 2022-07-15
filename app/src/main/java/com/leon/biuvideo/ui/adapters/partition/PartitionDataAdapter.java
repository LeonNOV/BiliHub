package com.leon.biuvideo.ui.adapters.partition;

import android.content.Context;
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
    public int getLayout(int viewType) {
        return R.layout.item_partition_video;
    }

    @Override
    protected ItemPartitionVideoBinding getItemViewBinding() {
        return ItemPartitionVideoBinding.bind(itemView);
    }

    @Override
    protected void onBindViewHolder(PartitionData.Result data, ItemPartitionVideoBinding binding, int position) {
        ViewGroup.LayoutParams layoutParams = binding.getRoot().getLayoutParams();
        layoutParams.width = context.getResources().getDimensionPixelSize(R.dimen.ItemPartitionRecommendWidth);
        binding.getRoot().setLayoutParams(layoutParams);

        binding.getRoot().setOnClickListener(v -> ActivityManager.startActivity(context, VideoActivity.class, Map.of(VideoActivity.PARAM, data.getBvid())));

        ViewUtils.setImg(context, binding.cover, "https:" + data.getPic());
        binding.play.setText(ValueUtils.generateCN(Integer.parseInt(data.getPlay())));
        binding.extra.setText(ValueUtils.lengthGenerate(data.getDuration()));
        binding.title.setText(data.getTitle());
        binding.author.setText(data.getAuthor());
    }
}
