package com.leon.biuvideo.ui.adapters.video.pgc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.publicBeans.resources.video.PgcDetail;
import com.leon.biuvideo.databinding.ItemPgcSectionBinding;
import com.leon.biuvideo.utils.ViewUtils;

/**
 * @Author Leon
 * @Time 2022/08/21
 * @Desc
 */
public class PgcSectionAdapter extends BaseViewBindingAdapter<PgcDetail.Result.Section, ItemPgcSectionBinding> {
    public PgcSectionAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemPgcSectionBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemPgcSectionBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_pgc_section, parent, false));
    }

    @Override
    protected void onBindViewHolder(PgcDetail.Result.Section data, ItemPgcSectionBinding binding, int position) {
        binding.title.setText(data.getTitle());

        PgcSectionContentAdapter adapter = new PgcSectionContentAdapter(context);
        adapter.appendHead(data.getEpisodes());
        ViewUtils.listInitializer(binding.subContent, adapter);
    }
}
