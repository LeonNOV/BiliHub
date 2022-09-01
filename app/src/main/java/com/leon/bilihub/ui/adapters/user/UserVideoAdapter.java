package com.leon.bilihub.ui.adapters.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.beans.publicBeans.user.UserVideo;
import com.leon.bilihub.databinding.ItemVideoBinding;
import com.leon.bilihub.ui.activities.publicActivities.VideoActivity;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/07
 * @Desc
 */
public class UserVideoAdapter extends ViewBindingAdapter<UserVideo.Data.DataList.Video, ItemVideoBinding> {
    public UserVideoAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemVideoBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemVideoBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video, parent, false));
    }

    @Override
    protected void onBindViewHolder(UserVideo.Data.DataList.Video data, ItemVideoBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_TYPE, VideoActivity.TYPE_VIDEO,
                VideoActivity.PARAM_ID, data.getBvid())));

        binding.author.setVisibility(View.GONE);
        binding.danmaku.setText(ValueUtils.generateCN(data.getVideoReview()));
        binding.extra.setText(data.getLength());
        binding.title.setText(data.getTitle());
        binding.view.setText(ValueUtils.generateCN(data.getPlay()));
        ViewUtils.setImg(context, binding.cover, data.getPic());
    }
}
