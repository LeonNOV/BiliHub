package com.leon.bilihub.ui.adapters.drawer.favFolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.beans.account.CollectFolder;
import com.leon.bilihub.databinding.ItemCollectBinding;
import com.leon.bilihub.ui.activities.publicActivities.FolderDetailActivity;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Locale;
import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/12
 * @Desc
 */
public class CollectAdapter extends ViewBindingAdapter<CollectFolder.Data.CollectData, ItemCollectBinding> {
    public CollectAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemCollectBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemCollectBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_collect, parent, false));
    }

    @Override
    protected void onBindViewHolder(CollectFolder.Data.CollectData data, ItemCollectBinding binding, int position) {
        ViewUtils.setImg(context, binding.cover, data.getCover());
        // 11：收藏文件夹
        // 21：合集文件夹
        if (data.getType() == 11) {
            binding.icon.setImageResource(R.drawable.item_collect_a);
            binding.getRoot().setOnClickListener(v -> startActivity(FolderDetailActivity.class, Map.of(FolderDetailActivity.PARAM_B, String.valueOf(data.getId()))));
        } else {
            binding.icon.setImageResource(R.drawable.item_collect_b);
            binding.getRoot().setOnClickListener(v -> startActivity(FolderDetailActivity.class, Map.of(FolderDetailActivity.PARAM_A, String.valueOf(data.getId()))));
        }

        binding.title.setText(data.getTitle());
        binding.creator.setText(String.format(Locale.CHINESE, "创建者: %s", data.getUpper().getName()));
        binding.count.setText(String.format(Locale.CHINESE, "内容数: %d", data.getMediaCount()));
        binding.play.setText(String.format(Locale.CHINESE, "播放量: %s", ValueUtils.generateCN(data.getViewCount())));
    }
}
