package com.leon.bilihub.ui.adapters.drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.bilihub.beans.account.FavoriteFolder;
import com.leon.bilihub.databinding.ItemFolderBinding;
import com.leon.bilihub.ui.activities.publicActivities.FolderDetailActivity;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/12
 * @Desc
 */
public class FolderAdapter extends BaseViewBindingAdapter<FavoriteFolder.Data.Folder, ItemFolderBinding> {
    public FolderAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemFolderBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemFolderBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_folder, parent, false));
    }

    @Override
    protected void onBindViewHolder(FavoriteFolder.Data.Folder data, ItemFolderBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(FolderDetailActivity.class, Map.of(FolderDetailActivity.PARAM_B, String.valueOf(data.getId()))));

        binding.name.setText(data.getTitle());
        binding.count.setText(String.valueOf(data.getMediaCount()));
    }
}
