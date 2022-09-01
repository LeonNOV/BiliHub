package com.leon.bilihub.ui.adapters.video.pgc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.beans.publicBeans.resources.video.PgcDetail;
import com.leon.bilihub.databinding.ItemPgcSectionContentBinding;
import com.leon.bilihub.ui.activities.publicActivities.VideoActivity;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/08/21
 * @Desc
 */
public class PgcSectionContentAdapter extends ViewBindingAdapter<PgcDetail.Result.Section.Episode, ItemPgcSectionContentBinding> {
    public PgcSectionContentAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemPgcSectionContentBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemPgcSectionContentBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_pgc_section_content, parent, false));
    }

    @Override
    protected void onBindViewHolder(PgcDetail.Result.Section.Episode data, ItemPgcSectionContentBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_TYPE, VideoActivity.TYPE_VIDEO,
                VideoActivity.PARAM_ID, data.getBvid())));

        ViewUtils.setImg(context, binding.cover, data.getCover());
        binding.title.setText(data.getTitle());
        binding.longTitle.setText(data.getLongTitle());
//        binding.extra.setText(ValueUtils.toMediaDuration(data.getDuration()));
    }
}
