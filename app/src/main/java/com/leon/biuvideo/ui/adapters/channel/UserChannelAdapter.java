package com.leon.biuvideo.ui.adapters.channel;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.home.channel.UserChannelCategory;
import com.leon.biuvideo.databinding.ItemChannelBinding;
import com.leon.biuvideo.ui.activities.drawerFunction.channel.ChannelDetailActivity;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Locale;
import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/16
 * @Desc
 */
public class UserChannelAdapter extends BaseViewBindingAdapter<UserChannelCategory.Data.NormalChannel, ItemChannelBinding> {
    public UserChannelAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayout(int viewType) {
        return R.layout.item_channel;
    }

    @Override
    protected ItemChannelBinding getItemViewBinding() {
        return ItemChannelBinding.bind(itemView);
    }

    @Override
    protected void onBindViewHolder(UserChannelCategory.Data.NormalChannel data, ItemChannelBinding binding, int position) {
        binding.container.setOnClickListener(v -> startActivity(ChannelDetailActivity.class, Map.of(ChannelDetailActivity.PARAM_ID, data.getId())));

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) binding.name.getLayoutParams();
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        binding.name.setLayoutParams(layoutParams);


        ViewUtils.setImg(context, binding.face, data.getCover());
        binding.name.setText(data.getName());
        binding.subscribe.setText(String.format(Locale.CHINESE, "订阅 %s", ValueUtils.generateCN(data.getSubscribedCount())));
    }
}
