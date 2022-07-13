package com.leon.biuvideo.ui.adapters;

import android.content.Context;
import android.widget.Toast;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.home.HomeRecommend;
import com.leon.biuvideo.databinding.ItemVideoBBinding;

/**
 * @Author Leon
 * @Time 2022/06/24
 * @Desc
 */
public class HomeRecommendAdapter extends BaseViewBindingAdapter<HomeRecommend.Data.Item, ItemVideoBBinding> {
    public HomeRecommendAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayout(int viewType) {
        return R.layout.item_video_b;
    }

    @Override
    protected ItemVideoBBinding getItemViewBinding() {
        return ItemVideoBBinding.bind(itemView);
    }

    @Override
    protected void onBindViewHolder(HomeRecommend.Data.Item data, ItemVideoBBinding binding, int position) {
        binding.play.setText(String.valueOf(data.getStat().getView()));
        binding.extra.setText(String.valueOf(data.getDuration()));
        binding.danmaku.setText(String.valueOf(data.getStat().getDanmaku()));
        binding.title.setText(data.getTitle());
        binding.author.setText(data.getOwner().getName());

        itemView.setOnClickListener(v -> Toast.makeText(context, data.getBvid() + "---" + position, Toast.LENGTH_SHORT).show());
    }
}
