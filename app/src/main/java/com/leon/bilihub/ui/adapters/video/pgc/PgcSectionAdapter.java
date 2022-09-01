package com.leon.bilihub.ui.adapters.video.pgc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.beans.publicBeans.resources.video.PgcDetail;
import com.leon.bilihub.databinding.ItemPgcSectionBinding;
import com.leon.bilihub.utils.ViewUtils;

/**
 * @Author Leon
 * @Time 2022/08/21
 * @Desc
 */
public class PgcSectionAdapter extends ViewBindingAdapter<PgcDetail.Result.Section, ItemPgcSectionBinding> {
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
