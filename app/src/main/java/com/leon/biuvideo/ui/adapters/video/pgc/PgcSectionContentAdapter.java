package com.leon.biuvideo.ui.adapters.video.pgc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.publicBeans.resources.video.PgcDetail;
import com.leon.biuvideo.databinding.ItemPgcSectionContentBinding;
import com.leon.biuvideo.ui.activities.publicActivities.VideoActivity;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/08/21
 * @Desc
 */
public class PgcSectionContentAdapter extends BaseViewBindingAdapter<PgcDetail.Result.Section.Episode, ItemPgcSectionContentBinding> {
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
