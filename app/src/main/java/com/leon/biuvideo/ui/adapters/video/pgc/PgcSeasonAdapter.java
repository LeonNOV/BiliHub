package com.leon.biuvideo.ui.adapters.video.pgc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.publicBeans.resources.video.PgcDetail;
import com.leon.biuvideo.databinding.ItemPgcSeasonBinding;

/**
 * @Author Leon
 * @Time 2022/08/21
 * @Desc
 */
public class PgcSeasonAdapter extends BaseViewBindingAdapter<PgcDetail.Result.Season, ItemPgcSeasonBinding> {
    public PgcSeasonAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemPgcSeasonBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemPgcSeasonBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_pgc_season, parent, false));
    }

    @Override
    protected void onBindViewHolder(PgcDetail.Result.Season data, ItemPgcSeasonBinding binding, int position) {
        binding.getRoot().setText(data.getSeasonTitle());
    }
}
