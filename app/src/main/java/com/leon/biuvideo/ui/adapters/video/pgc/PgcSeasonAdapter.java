package com.leon.biuvideo.ui.adapters.video.pgc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.publicBeans.resources.video.PgcDetail;
import com.leon.biuvideo.databinding.ItemPgcSeasonBinding;
import com.leon.biuvideo.model.VideoPlayerModel;
import com.leon.biuvideo.ui.activities.publicActivities.VideoActivity;
import com.leon.biuvideo.ui.widget.player.PlayerController;
import com.leon.biuvideo.utils.ViewUtils;

/**
 * @Author Leon
 * @Time 2022/08/21
 * @Desc
 */
public class PgcSeasonAdapter extends BaseViewBindingAdapter<PgcDetail.Result.Season, ItemPgcSeasonBinding> {
    private PlayerController.OnSelectedListener<Integer> onSelectedListener;
    private int selectedPosition;

    private final VideoPlayerModel videoPlayerModel;

    public PgcSeasonAdapter(Context context, int selectedPosition) {
        super(context);

        this.selectedPosition = selectedPosition;
        videoPlayerModel = new ViewModelProvider(ViewUtils.scanForActivity(context)).get(VideoPlayerModel.class);
    }

    @Override
    protected ItemPgcSeasonBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemPgcSeasonBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_pgc_season, parent, false));
    }

    @Override
    protected void onBindViewHolder(PgcDetail.Result.Season data, ItemPgcSeasonBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> {
            if (onSelectedListener != null && selectedPosition != position) {
                videoPlayerModel.getVideoPgcSeason().setValue(data.getSeasonId());

                PgcDetail.Result.Season.ItemState itemState = data.getItemState();
                itemState.setTitleColor(context.getColor(R.color.blue));
                itemState.setSelected(true);

                onSelectedListener.onSelected(selectedPosition);
                selectedPosition = position;

                notifyItemChanged(position);
            }
        });

        binding.getRoot().setText(data.getSeasonTitle());

        PgcDetail.Result.Season.ItemState itemState = data.getItemState();
        binding.getRoot().setTextColor(itemState.getTitleColor());
        binding.getRoot().setSelected(itemState.getSelected());
    }

    public void setOnSelectedListener(PlayerController.OnSelectedListener<Integer> onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }
}
