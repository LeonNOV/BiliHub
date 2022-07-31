package com.leon.biuvideo.ui.adapters.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.publicBeans.user.UserVideo;
import com.leon.biuvideo.databinding.ItemVideoBinding;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

/**
 * @Author Leon
 * @Time 2022/07/07
 * @Desc
 */
public class UserVideoAdapter extends BaseViewBindingAdapter<UserVideo.Data.DataList.Video, ItemVideoBinding> {
    public UserVideoAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemVideoBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemVideoBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_video, parent, false));
    }

    @Override
    protected void onBindViewHolder(UserVideo.Data.DataList.Video data, ItemVideoBinding binding, int position) {
        binding.danmaku.setText(ValueUtils.generateCN(data.getVideoReview()));
        binding.extra.setText(data.getLength());
        binding.title.setText(data.getTitle());
        binding.view.setText(ValueUtils.generateCN(data.getPlay()));
        ViewUtils.setImg(context, binding.cover, data.getPic());
        binding.getRoot().setOnClickListener(v -> {
            Toast.makeText(context, data.getTitle() + "----" + data.getBvid(), Toast.LENGTH_SHORT).show();
        });
    }
}
