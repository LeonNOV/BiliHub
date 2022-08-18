package com.leon.biuvideo.ui.adapters.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.publicBeans.user.UserOrder;
import com.leon.biuvideo.databinding.ItemUserOrderBinding;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Locale;

/**
 * @Author Leon
 * @Time 2022/07/11
 * @Desc todo 打开方式待定
 */
public class UserOrderAdapter extends BaseViewBindingAdapter<UserOrder.Data.Order, ItemUserOrderBinding> {
    public UserOrderAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemUserOrderBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemUserOrderBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_user_order, parent, false));
    }

    @Override
    protected void onBindViewHolder(UserOrder.Data.Order data, ItemUserOrderBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> {});

        ViewUtils.setImg(context, binding.cover, data.getCover());
        if (!"".equals(data.getBadgeInfo().getImg())) {
            ViewUtils.setImg(context, binding.badge, data.getBadgeInfo().getImg());
        }
        binding.title.setText(data.getTitle());

        StringBuilder areaStr = new StringBuilder();
        for (UserOrder.Data.Order.Area area : data.getAreas()) {
            areaStr.append(area.getName()).append("、");
        }
        areaStr.delete(areaStr.length() - 1, areaStr.length());

        binding.extra.setText(String.format(Locale.CHINESE, "%s | %s", data.getSeasonTypeName(), areaStr));
        binding.epCount.setText(String.format(Locale.CHINESE, "全%d话", data.getTotalCount()));
        binding.progress.setText("".equals(data.getProgress()) ? "尚未观看" : data.getProgress());
    }
}
