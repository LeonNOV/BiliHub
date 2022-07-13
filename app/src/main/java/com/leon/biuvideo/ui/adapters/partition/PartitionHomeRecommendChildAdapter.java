package com.leon.biuvideo.ui.adapters.partition;

import android.content.Context;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseActivity.ActivityManager;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.partition.PartitionRecommend;
import com.leon.biuvideo.databinding.ItemVideoBBinding;
import com.leon.biuvideo.ui.activities.publicActivities.VideoActivity;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

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
    public int getLayout(int viewType) {
        return R.layout.item_video_b;
    }

    @Override
    protected ItemVideoBBinding getItemViewBinding() {
        return ItemVideoBBinding.bind(itemView);
    }

    @Override
    protected void onBindViewHolder(PartitionRecommend.Data.Archive data, ItemVideoBBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> ActivityManager.startActivity(context, VideoActivity.class, Map.of(VideoActivity.PARAM, data.getBvid())));

        ViewUtils.setImg(context, binding.cover, data.getPic());
        binding.play.setText(ValueUtils.generateCN(data.getStat().getView()));
        binding.danmaku.setText(ValueUtils.generateCN(data.getStat().getDanmaku()));
        binding.extra.setText(ValueUtils.lengthGenerate(data.getDuration()));
        binding.title.setText(data.getTitle());
        binding.author.setText(data.getOwner().getName());
    }
}
