package com.leon.bilihub.ui.adapters.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.bilihub.beans.publicBeans.user.UserPicture;
import com.leon.bilihub.databinding.ItemUserPictureBinding;
import com.leon.bilihub.ui.activities.publicActivities.PictureActivity;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Locale;
import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/08
 * @Desc
 */
public class UserPictureAdapter extends BaseViewBindingAdapter<UserPicture.Data.Item, ItemUserPictureBinding> {
    public UserPictureAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemUserPictureBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemUserPictureBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_user_picture, parent, false));
    }

    @Override
    protected void onBindViewHolder(UserPicture.Data.Item data, ItemUserPictureBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(PictureActivity.class, Map.of(PictureActivity.PARAM, data.getDynId())));

        ViewUtils.setImg(context, binding.cover, data.getPictures().get(0).getImgSrc());
        if (data.getCount() > 1) {
            binding.extra.setVisibility(View.VISIBLE);
            binding.extra.setText(String.format(Locale.CHINESE, "%dP", data.getCount()));
        }
        binding.desc.setText(data.getDescription());
        binding.pubTime.setText(ValueUtils.generateTime(data.getCtime(), "yyyy-MM-dd", true));
        binding.view.setText(ValueUtils.generateCN(data.getView()));
        binding.like.setText(ValueUtils.generateCN(data.getLike()));
    }
}
