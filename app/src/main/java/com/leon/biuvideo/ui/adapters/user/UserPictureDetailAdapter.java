package com.leon.biuvideo.ui.adapters.user;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.publicBeans.resources.picture.PictureInfo;
import com.leon.biuvideo.databinding.ItemPictureBinding;
import com.leon.biuvideo.ui.activities.publicActivities.PictureViewerActivity;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.ArrayList;

/**
 * @Author Leon
 * @Time 2022/07/29
 * @Desc 相簿详情页图片适配器
 */
public class UserPictureDetailAdapter extends BaseViewBindingAdapter<PictureInfo.Data.Item.Modules.ModuleDynamic.Major.Draw.Item, ItemPictureBinding> {
    private final Bundle bundle;

    public UserPictureDetailAdapter(Context context, ArrayList<PictureInfo.Data.Item.Modules.ModuleDynamic.Major.Draw.Item> pictures) {
        super(context);
        this.bundle = new Bundle();
        this.bundle.putParcelableArrayList(PictureViewerActivity.PARAM_A, pictures);
    }

    @Override
    protected ItemPictureBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemPictureBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_picture, parent, false));
    }

    @Override
    protected void onBindViewHolder(PictureInfo.Data.Item.Modules.ModuleDynamic.Major.Draw.Item data, ItemPictureBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> {
            bundle.putInt(PictureViewerActivity.PARAM_B, position);
            startActivity(PictureViewerActivity.class, bundle);
        });
        ViewUtils.setImg(context, binding.getRoot(), data.getSrc());
    }
}
