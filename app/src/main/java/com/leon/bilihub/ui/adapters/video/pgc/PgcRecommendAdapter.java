package com.leon.bilihub.ui.adapters.video.pgc;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.graphics.ColorKt;
import androidx.core.view.ViewCompat;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.bilihub.beans.publicBeans.resources.video.PgcRecommend;
import com.leon.bilihub.databinding.ItemPgcRecommendBinding;
import com.leon.bilihub.ui.activities.publicActivities.VideoActivity;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.Locale;
import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/08/24
 * @Desc
 */
public class PgcRecommendAdapter extends BaseViewBindingAdapter<PgcRecommend.Data.Season, ItemPgcRecommendBinding> {
    public PgcRecommendAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemPgcRecommendBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemPgcRecommendBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_pgc_recommend, parent, false));
    }

    @Override
    protected void onBindViewHolder(PgcRecommend.Data.Season data, ItemPgcRecommendBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(VideoActivity.class, Map.of(VideoActivity.PARAM_TYPE, VideoActivity.TYPE_PGC,
                VideoActivity.PARAM_ID, data.getSeasonId())));

        ViewUtils.setImg(context, binding.cover, data.getCover());
        if (!"".equals(data.getBadge())) {
            binding.badge.setText(data.getBadgeInfo().getText());
            ViewCompat.setBackgroundTintList(binding.badge, ColorStateList.valueOf(ColorKt.toColorInt(data.getBadgeInfo().getBgColor())));
        } else {
            binding.badge.setVisibility(View.GONE);
        }

        binding.title.setText(data.getTitle());
        binding.newEp.setText(data.getNewEp().getIndexShow());
        binding.view.setText(ValueUtils.generateCN(data.getStat().getView()));
        binding.danmaku.setText(ValueUtils.generateCN(data.getStat().getDanmaku()));

        if (data.getRating() != null) {
            binding.ratingScore.setText(String.valueOf(data.getRating().getScore()));
            binding.ratingCount.setText(String.format(Locale.CHINESE, "%s人评分", ValueUtils.generateCN(data.getRating().getCount())));
        } else {
            binding.ratingContainer.setVisibility(View.GONE);
        }
    }
}
