package com.leon.bilihub.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.beans.home.HomeRecommendApp;
import com.leon.bilihub.databinding.ItemVideoBBinding;
import com.leon.bilihub.databinding.ItemVideoBinding;
import com.leon.bilihub.ui.activities.publicActivities.VideoActivity;
import com.leon.bilihub.utils.PreferenceUtils;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/06/24
 * @Desc
 */
public class HomeRecommendAdapter extends ViewBindingAdapter<HomeRecommendApp.Data.Item, ViewBinding> {
    private final int recommendStyle;

    public HomeRecommendAdapter(Context context) {
        super(context);
        this.recommendStyle = PreferenceUtils.getRecommendStyle(context);
    }

    @Override
    protected ViewBinding getItemViewBinding(Context context, ViewGroup parent) {
        if (recommendStyle == 1) {
            // 单列
            return ItemVideoBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video, parent, false));
        } else if (recommendStyle == 2) {
            // 双列
            return ItemVideoBBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video_b, parent, false));
        }

        return ItemVideoBBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video_b, parent, false));
    }

    @Override
    protected void onBindViewHolder(HomeRecommendApp.Data.Item data, ViewBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_TYPE, VideoActivity.TYPE_VIDEO,
                VideoActivity.PARAM_ID, ValueUtils.av2bv(data.getPlayerArgs().getAid()))));

        if (recommendStyle == 1) {
            setSingleColumn(data, (ItemVideoBinding) binding);
        } else {
            setDoubleColumn(data, (ItemVideoBBinding) binding);
        }
    }

    private void setSingleColumn(HomeRecommendApp.Data.Item data, ItemVideoBinding binding) {
        ViewUtils.setImg(context, binding.cover, data.getCover());
        binding.view.setText(data.getCoverLeftText1());
        binding.danmaku.setText(data.getCoverLeftText2());
        binding.extra.setText(data.getCoverRightText());
        binding.title.setText(data.getTitle());
        binding.author.setText(data.getArgs().getUpName());
    }

    public void setDoubleColumn(HomeRecommendApp.Data.Item data, ItemVideoBBinding binding) {
        ViewUtils.setImg(context, binding.cover, data.getCover());
        binding.play.setText(data.getCoverLeftText1());
        binding.danmaku.setText(data.getCoverLeftText2());
        binding.extra.setText(data.getCoverRightText());
        binding.title.setText(data.getTitle());
        binding.author.setText(data.getArgs().getUpName());
    }
}
