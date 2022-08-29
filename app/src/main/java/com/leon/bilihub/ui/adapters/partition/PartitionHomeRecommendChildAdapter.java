package com.leon.bilihub.ui.adapters.partition;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.bilihub.beans.partition.PartitionRecommend;
import com.leon.bilihub.databinding.ItemVideoBBinding;
import com.leon.bilihub.ui.activities.publicActivities.VideoActivity;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/13
 * @Desc
 */
public class PartitionHomeRecommendChildAdapter extends BaseViewBindingAdapter<PartitionRecommend.Data.Archive, ItemVideoBBinding> {
    public PartitionHomeRecommendChildAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemVideoBBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemVideoBBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video_b, parent, false));
    }

    @Override
    protected void onBindViewHolder(PartitionRecommend.Data.Archive data, ItemVideoBBinding binding, int position) {
        ViewGroup.LayoutParams layoutParams = binding.getRoot().getLayoutParams();
        layoutParams.width = context.getResources().getDimensionPixelSize(R.dimen.ItemVideoW);
        layoutParams.height = context.getResources().getDimensionPixelSize(R.dimen.ItemVideoH);
        binding.getRoot().setLayoutParams(layoutParams);

        binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_TYPE, VideoActivity.TYPE_VIDEO,
                VideoActivity.PARAM_ID, data.getBvid())));

        ViewUtils.setImg(context, binding.cover, data.getPic());
        binding.play.setText(ValueUtils.generateCN(data.getStat().getView()));
        binding.danmaku.setText(ValueUtils.generateCN(data.getStat().getDanmaku()));
        binding.extra.setText(ValueUtils.toMediaDuration(data.getDuration()));
        binding.title.setText(data.getTitle());
        binding.author.setText(data.getOwner().getName());
    }
}
