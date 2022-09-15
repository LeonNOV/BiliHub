package com.leon.bilihub.ui.adapters.drawer.relation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.beans.account.RelationTags;
import com.leon.bilihub.databinding.ItemFolderBinding;
import com.leon.bilihub.ui.activities.publicActivities.RelationDetailActivity;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/12
 * @Desc
 */
public class RelationGroupAdapter extends ViewBindingAdapter<RelationTags.Data, ItemFolderBinding> {
    private final boolean isView;

    public RelationGroupAdapter(Context context, boolean isView) {
        super(context);
        this.isView = isView;
    }

    @Override
    protected ItemFolderBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemFolderBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_folder, parent, false));
    }

    @Override
    protected void onBindViewHolder(RelationTags.Data data, ItemFolderBinding binding, int position) {
        if (isView) {
            binding.getRoot().setOnClickListener(v -> startActivity(RelationDetailActivity.class,
                    Map.of(RelationDetailActivity.PARAM_A, data.getTagid(),
                            RelationDetailActivity.PARAM_B, data.getName())));
        } else {
            binding.getRoot().setOnClickListener(v -> {
                boolean isSelected = !data.isSelected();
                data.setSelected(isSelected);
                binding.action.setSelected(isSelected);

                notifyItemChanged(position);
            });
        }

        binding.name.setText(data.getName());
        binding.count.setText(String.valueOf(data.getCount()));
        binding.action.setImageResource(isView ? R.drawable.ic_arrow : R.drawable.ic_check);
    }
}
