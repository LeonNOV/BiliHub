package com.leon.biuvideo.ui.adapters.video.pgc;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.core.graphics.ColorKt;
import androidx.core.view.ViewCompat;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.publicBeans.resources.video.PgcDetail;
import com.leon.biuvideo.databinding.ItemPgcEpisodeBinding;

import java.util.Locale;

/**
 * @Author Leon
 * @Time 2022/08/21
 * @Desc
 */
public class PgcEpisodeAdapter extends BaseViewBindingAdapter<PgcDetail.Result.Episode, ItemPgcEpisodeBinding> {
    private final String epSuffix;

    public PgcEpisodeAdapter(Context context, int type) {
        super(context);

        if (type == 1 || type == 4) {
            epSuffix = "话";
        } else {
            epSuffix = "集";
        }
    }

    @Override
    protected ItemPgcEpisodeBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemPgcEpisodeBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_pgc_episode, parent, false));
    }

    @Override
    protected void onBindViewHolder(PgcDetail.Result.Episode data, ItemPgcEpisodeBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> {
            binding.epIndex.setTextColor(context.getColor(R.color.BiliBili_pink));
            binding.epTitle.setTextColor(context.getColor(R.color.BiliBili_pink));
            binding.getRoot().setSelected(true);
        });

        String epIndex;
        try {
            int i = Integer.parseInt(data.getTitle());
            epIndex = String.format(Locale.CHINESE, "第 %d %s", i, epSuffix);
        } catch (NumberFormatException e) {
            epIndex = data.getTitle();
        }

        binding.epIndex.setText(epIndex);

        if (!"".equals(data.getLongTitle())) {
            binding.epTitle.setText(data.getLongTitle());
        }

        /**
         * 2：预告/不需要大会员/限免
         * 8：付费
         * 13：需要大会员
         */
        switch (data.getStatus()) {
            case 2:
            case 8:
            case 13:
                setBadge(data.getBadgeInfo(), binding);
                break;
            default:
                break;
        }
    }

    private void setBadge(PgcDetail.Result.Episode.BadgeInfo badgeInfo, ItemPgcEpisodeBinding binding) {
        if (!"".equals(badgeInfo.getText())) {
            binding.badge.setVisibility(View.VISIBLE);
            binding.badge.setText(badgeInfo.getText());
            ViewCompat.setBackgroundTintList(binding.badge, ColorStateList.valueOf(ColorKt.toColorInt(badgeInfo.getBgColor())));
        }
    }
}
