package com.leon.biuvideo.ui.adapters.drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.account.FavoriteFolder;
import com.leon.biuvideo.databinding.ItemFolderBinding;

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
        binding.getRoot().setOnClickListener(v -> Toast.makeText(context, "Go", Toast.LENGTH_SHORT).show());

        binding.name.setText(data.getTitle());
        binding.count.setText(String.valueOf(data.getMediaCount()));
    }
}
