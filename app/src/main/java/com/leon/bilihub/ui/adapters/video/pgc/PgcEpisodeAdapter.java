package com.leon.bilihub.ui.adapters.video.pgc;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.graphics.ColorKt;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProvider;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.bilihub.beans.publicBeans.resources.video.PgcDetail;
import com.leon.bilihub.databinding.ItemPgcEpisodeBinding;
import com.leon.bilihub.model.VideoPlayerModel;
import com.leon.bilihub.ui.widget.player.PlayerController;
import com.leon.bilihub.utils.PreferenceUtils;
import com.leon.bilihub.utils.ViewUtils;
import com.leon.bilihub.wraps.VideoResourceWrap;

import java.util.Locale;

/**
 * @Author Leon
 * @Time 2022/08/21
 * @Desc
 */
public class PgcEpisodeAdapter extends BaseViewBindingAdapter<PgcDetail.Result.Episode, ItemPgcEpisodeBinding> {
    private final String epSuffix;

    private PlayerController.OnSelectedListener<Integer> onSelectedListener;
    private int selectedPosition;

    private final VideoPlayerModel videoPlayerModel;

    public PgcEpisodeAdapter(Context context, int type, int selectedPosition) {
        super(context);

        this.selectedPosition = selectedPosition;
        videoPlayerModel = new ViewModelProvider(ViewUtils.scanForActivity(context)).get(VideoPlayerModel.class);

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
            if (data.getStatus() == 13 || data.getStatus() == 8) {
                Toast.makeText(context, String.format(Locale.CHINESE, "该选集需要%s才能观看", data.getBadge()), Toast.LENGTH_SHORT).show();
            } else if (onSelectedListener != null && selectedPosition != position) {
                videoPlayerModel.getVideoPgcEpisode().setValue(data.getId());
                String epIndex = getEpIndex(data);
                videoPlayerModel.getVideoTitleDisplay().setValue("".equals(data.getLongTitle()) ? epIndex : epIndex + data.getLongTitle());
                videoPlayerModel.getVideoResource().setValue(new VideoResourceWrap(data.getBvid(), data.getCid(), PreferenceUtils.getVideoQuality(context)));

                PgcDetail.Result.Episode.ItemState itemState = data.getItemState();
                itemState.setEpColor(context.getColor(R.color.blue));
                itemState.setEpSelected(true);

                onSelectedListener.onSelected(selectedPosition);
                selectedPosition = position;

                notifyItemChanged(position);
            }
        });

        binding.epIndex.setText(getEpIndex(data));

        PgcDetail.Result.Episode.ItemState itemState = data.getItemState();

        binding.epTitle.setTextColor(itemState.getEpColor());
        binding.epIndex.setTextColor(itemState.getEpColor());
        binding.getRoot().setSelected(itemState.getEpSelected());

        if (!"".equals(data.getLongTitle())) {
            binding.epTitle.setText(data.getLongTitle());
        }

        // 2：预告/不需要大会员/限免
        // 8：付费
        // 13：需要大会员
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

    @NonNull
    private String getEpIndex(PgcDetail.Result.Episode data) {
        String epIndex;
        try {
            int i = Integer.parseInt(data.getTitle());
            epIndex = String.format(Locale.CHINESE, "第 %d %s", i, epSuffix);
        } catch (NumberFormatException e) {
            epIndex = data.getTitle();
        }
        return epIndex;
    }

    private void setBadge(PgcDetail.Result.Episode.BadgeInfo badgeInfo, ItemPgcEpisodeBinding binding) {
        if (!"".equals(badgeInfo.getText())) {
            binding.badge.setVisibility(View.VISIBLE);
            binding.badge.setText(badgeInfo.getText());
            ViewCompat.setBackgroundTintList(binding.badge, ColorStateList.valueOf(ColorKt.toColorInt(badgeInfo.getBgColor())));
        }
    }

    public void setOnSelectedListener(PlayerController.OnSelectedListener<Integer> onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }
}
