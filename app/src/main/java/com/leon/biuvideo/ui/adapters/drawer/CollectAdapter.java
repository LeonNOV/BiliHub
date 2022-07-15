package com.leon.biuvideo.ui.adapters.drawer;

import android.content.Context;
import android.widget.Toast;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.account.Collect;
import com.leon.biuvideo.databinding.ItemCollectBinding;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Locale;

/**
 * @Author Leon
 * @Time 2022/07/12
 * @Desc
 */
public class CollectAdapter extends BaseViewBindingAdapter<Collect.Data.CollectData, ItemCollectBinding> {
    public CollectAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayout(int viewType) {
        return R.layout.item_collect;
    }

    @Override
    protected ItemCollectBinding getItemViewBinding() {
        return ItemCollectBinding.bind(itemView);
    }

    @Override
    protected void onBindViewHolder(Collect.Data.CollectData data, ItemCollectBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> Toast.makeText(context, "Collect", Toast.LENGTH_SHORT).show());

        ViewUtils.setImg(context, binding.cover, data.getCover());
        if (data.getCoverType() == 2) {
            binding.icon.setImageResource(R.drawable.collect_a);
        }

        binding.title.setText(data.getTitle());
        binding.creator.setText(String.format(Locale.CHINESE, "创建者: %s", data.getUpper().getName()));
        binding.count.setText(String.format(Locale.CHINESE, "内容数: %d", data.getMediaCount()));
        binding.play.setText(String.format(Locale.CHINESE, "播放量: %s", ValueUtils.generateCN(data.getViewCount())));
    }
}
