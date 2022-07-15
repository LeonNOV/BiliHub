package com.leon.biuvideo.ui.adapters;

import android.content.Context;

import androidx.appcompat.widget.AppCompatTextView;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.partition.PartitionTag;
import com.leon.biuvideo.databinding.ItemFilterBinding;

import java.util.ArrayList;

/**
 * @Author Leon
 * @Time 2022/07/14
 * @Desc
 */
public class PartitionFilterAdapter extends BaseViewBindingAdapter<PartitionTag, ItemFilterBinding> {
    private final ArrayList<AppCompatTextView> textViews = new ArrayList<>();
    private int selectedPosition = 0;
    private OnRefreshData onRefreshData;

    public PartitionFilterAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayout(int viewType) {
        return R.layout.item_filter;
    }

    @Override
    protected ItemFilterBinding getItemViewBinding() {
        return ItemFilterBinding.bind(itemView);
    }

    @Override
    protected void onBindViewHolder(PartitionTag data, ItemFilterBinding binding, int position) {
        if (position == 0) {
            binding.filter.setTextColor(context.getColor(R.color.BiliBili_pink));
        }

        textViews.add(binding.filter);
        binding.getRoot().setOnClickListener(v -> {
            if (selectedPosition != position) {
                if (onRefreshData != null) {
                    onRefreshData.onRefresh(data);
                }
                changeColor(position);
            }
        });

        binding.filter.setText(data.getTitle());
    }

    private void changeColor(int position) {
        textViews.get(position).setTextColor(context.getColor(R.color.BiliBili_pink));
        textViews.get(selectedPosition).setTextColor(context.getColor(R.color.infoColor));

        selectedPosition = position;
    }

    public void setOnRefreshData(OnRefreshData onRefreshData) {
        this.onRefreshData = onRefreshData;
    }

    public interface OnRefreshData {
        void onRefresh(PartitionTag data);
    }
}
