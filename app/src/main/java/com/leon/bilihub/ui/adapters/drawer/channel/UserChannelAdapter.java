package com.leon.bilihub.ui.adapters.drawer.channel;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.beans.home.channel.UserChannelCategory;
import com.leon.bilihub.databinding.ItemChannelBinding;
import com.leon.bilihub.ui.activities.drawerFunction.channel.ChannelDetailActivity;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/16
 * @Desc
 */
public class UserChannelAdapter extends ViewBindingAdapter<UserChannelCategory.Data.NormalChannel, ItemChannelBinding> {
    public UserChannelAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemChannelBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemChannelBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_channel, parent, false));
    }

    @Override
    protected void onBindViewHolder(UserChannelCategory.Data.NormalChannel data, ItemChannelBinding binding, int position) {
        binding.container.setOnClickListener(v -> startActivity(ChannelDetailActivity.class, Map.of(ChannelDetailActivity.PARAM_ID, data.getId())));

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) binding.name.getLayoutParams();
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        binding.name.setLayoutParams(layoutParams);


        ViewUtils.setImg(context, binding.face, data.getCover());
        binding.name.setText(data.getName());
    }
}