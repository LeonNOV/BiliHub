package com.leon.bilihub.ui.adapters.drawer;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.graphics.ColorKt;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.beans.publicBeans.user.UserOrder;
import com.leon.bilihub.databinding.ItemUserOrderBinding;
import com.leon.bilihub.ui.activities.publicActivities.VideoActivity;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Locale;
import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/11
 * @Desc
 */
public class UserOrderAdapter extends ViewBindingAdapter<UserOrder.Data.Order, ItemUserOrderBinding> {
    public UserOrderAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemUserOrderBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemUserOrderBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_user_order, parent, false));
    }

    @Override
    protected void onBindViewHolder(UserOrder.Data.Order data, ItemUserOrderBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_TYPE, VideoActivity.TYPE_PGC,
                VideoActivity.PARAM_ID, data.getSeasonId())));

        ViewUtils.setImg(context, binding.cover, data.getCover());
        UserOrder.Data.Order.BadgeInfo badgeInfo = data.getBadgeInfo();
        if (badgeInfo.getText() != null && !"".equals(badgeInfo.getText())) {
            binding.badge.setVisibility(View.VISIBLE);
            binding.badge.setText(badgeInfo.getText());
            binding.badge.setBackgroundTintList(ColorStateList.valueOf(ColorKt.toColorInt(badgeInfo.getBgColor())));
        }
        binding.title.setText(data.getTitle());

        StringBuilder areaStr = new StringBuilder();
        for (UserOrder.Data.Order.Area area : data.getAreas()) {
            areaStr.append(area.getName()).append("、");
        }
        areaStr.delete(areaStr.length() - 1, areaStr.length());

        binding.extra.setText(String.format(Locale.CHINESE, "%s | %s", data.getSeasonTypeName(), areaStr));
        binding.newEp.setText(data.getNewEp().getIndexShow());
        binding.progress.setText("".equals(data.getProgress()) ? "尚未观看" : data.getProgress());
    }
}
