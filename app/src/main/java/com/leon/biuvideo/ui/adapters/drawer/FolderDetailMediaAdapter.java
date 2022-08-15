package com.leon.biuvideo.ui.adapters.drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.account.FolderDetailMedia;
import com.leon.biuvideo.databinding.ItemFolderDetailMediaBinding;
import com.leon.biuvideo.ui.activities.publicActivities.VideoActivity;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/08/03
 * @Desc
 */
public class FolderDetailMediaAdapter extends BaseViewBindingAdapter<FolderDetailMedia, ItemFolderDetailMediaBinding> {
    public FolderDetailMediaAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemFolderDetailMediaBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemFolderDetailMediaBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_folder_detail_media, parent, false));
    }

    @Override
    protected void onBindViewHolder(FolderDetailMedia data, ItemFolderDetailMediaBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_TYPE, VideoActivity.TYPE_VIDEO,
                VideoActivity.PARAM_ID, data.getBvid())));

        ViewUtils.setImg(context, binding.cover, data.getCover());
        binding.extra.setText(ValueUtils.toMediaDuration(data.getDuration()));
        binding.title.setText(data.getTitle());
        binding.author.setText(data.getAuthor());
        binding.view.setText(ValueUtils.generateCN(data.getPlay()));
        binding.collect.setText(ValueUtils.generateCN(data.getCollect()));
    }
}
