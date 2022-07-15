package com.leon.biuvideo.ui.adapters.drawer.relation;

import android.content.Context;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseActivity.ActivityManager;
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
    public int getLayout(int viewType) {
        return R.layout.item_folder;
    }

    @Override
    protected ItemFolderBinding getItemViewBinding() {
        return ItemFolderBinding.bind(itemView);
    }

    @Override
    protected void onBindViewHolder(RelationTags.Data data, ItemFolderBinding binding, int position) {
        binding.getRoot().setOnClickListener(v -> ActivityManager.startActivity(context, RelationDetailActivity.class, Map.of(RelationDetailActivity.PARAM, String.valueOf(data.getTagid()))));

        binding.name.setText(data.getName());
        binding.count.setText(String.valueOf(data.getCount()));
    }
}
