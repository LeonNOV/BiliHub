package com.leon.biuvideo.ui.adapters.drawer.relation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.account.RelationTags;
import com.leon.biuvideo.databinding.ItemFolderBinding;
import com.leon.biuvideo.ui.activities.publicActivities.RelationDetailActivity;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/12
 * @Desc
 */
public class RelationTagsAdapter extends BaseViewBindingAdapter<RelationTags.Data, ItemFolderBinding> {
    public RelationTagsAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemFolderBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemFolderBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_folder, parent, false));
    }

    @Override
    protected void onBindViewHolder(RelationTags.Data data, ItemFolderBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> startActivity(RelationDetailActivity.class,
                Map.of(RelationDetailActivity.PARAM_A, String.valueOf(data.getTagid()),
                        RelationDetailActivity.PARAM_B, data.getName())));

        binding.name.setText(data.getName());
        binding.count.setText(String.valueOf(data.getCount()));
    }
}
