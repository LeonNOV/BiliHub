package com.leon.biuvideo.ui.adapters.userAdapters;

import android.content.Context;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseActivity.ActivityManager;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.publicBeans.user.UserOrder;
import com.leon.biuvideo.databinding.UserOrderItemBinding;
import com.leon.biuvideo.ui.activities.publicActivities.VideoActivity;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Locale;

/**
 * @Author Leon
 * @Time 2022/07/11
 * @Desc
 */
public class UserOrderAdapter extends BaseViewBindingAdapter<UserOrder.Data.Order, UserOrderItemBinding> {
    public UserOrderAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayout(int viewType) {
        return R.layout.user_order_item;
    }

    @Override
    protected UserOrderItemBinding getItemViewBinding() {
        return UserOrderItemBinding.bind(itemView);
    }

    @Override
    protected void onBindViewHolder(UserOrder.Data.Order data, UserOrderItemBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> ActivityManager.startActivity(context, VideoActivity.class));

        ViewUtils.setImg(context, binding.cover, data.getCover());
        ViewUtils.setImg(context, binding.badge, data.getBadgeInfo().getImg());
        binding.title.setText(data.getTitle());
        binding.epCount.setText(String.format(Locale.CHINESE, "全%d话", data.getTotalCount()));
        binding.progress.setText(data.getProgress());
    }
}
