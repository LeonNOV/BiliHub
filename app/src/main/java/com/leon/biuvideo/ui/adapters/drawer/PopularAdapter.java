package com.leon.biuvideo.ui.adapters.drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.home.drawerFunction.popular.PopularData;
import com.leon.biuvideo.databinding.ItemPopularBinding;
import com.leon.biuvideo.ui.activities.publicActivities.VideoActivity;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/08/03
 * @Desc
 */
public class PopularAdapter extends BaseViewBindingAdapter<PopularData, ItemPopularBinding> {
    public PopularAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemPopularBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemPopularBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_popular, parent, false));
    }

    @Override
    protected void onBindViewHolder(PopularData data, ItemPopularBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_TYPE, VideoActivity.TYPE_VIDEO,
                VideoActivity.PARAM_ID, data.getId())));

        ViewUtils.setImg(context, binding.cover, data.getCover());
        binding.extra.setText(ValueUtils.toMediaDuration(data.getDuration()));
        binding.title.setText(data.getTitle());
        binding.author.setText(data.getAuthor());
        binding.view.setText(ValueUtils.generateCN(data.getPlay()));
        binding.danmaku.setText(ValueUtils.generateCN(data.getDanmaku()));

        if (data.getType() == PopularType.Rank) {
            binding.reason.setVisibility(View.GONE);
        } else if (!"".equals(data.getReason())) {
            int bg = 0;
            int textColor = 0;

            switch (data.getType()) {
                case Hot:
                    bg = R.drawable.item_popular_hot_bg;
                    textColor = context.getColor(R.color.ItemPopularHot);
                    break;
                case Weekly:
                    bg = R.drawable.item_popular_weekly_bg;
                    textColor = context.getColor(R.color.ItemPopularWeekly);
                    break;
                case Precious:
                    bg = R.drawable.item_popular_precious_bg;
                    textColor = context.getColor(R.color.ItemPopularPrecious);
                    break;
                default:
                    binding.reason.setVisibility(View.GONE);
                    break;
            }
            binding.reason.setBackgroundResource(bg);
            binding.reason.setTextColor(textColor);
            binding.reason.setText(data.getReason());
        } else {
            binding.reason.setVisibility(View.GONE);
        }
    }

    public enum PopularType {
        Hot,
        Weekly,
        Precious,
        Rank
    }
}
