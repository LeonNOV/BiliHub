package com.leon.biuvideo.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.databinding.ItemTextBinding;

/**
 * @Author Leon
 * @Time 2022/07/04
 * @Desc
 */
public class PopularRankPartitionAdapter extends BaseViewBindingAdapter<String, ItemTextBinding> {
    private int preSelectedIndex = 0;
    private OnItemSelectedListener onItemSelectedListener;

    public PopularRankPartitionAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayout(int viewType) {
        return R.layout.item_text;
    }

    @Override
    protected ItemTextBinding getItemViewBinding() {
        return ItemTextBinding.bind(itemView);
    }

    @Override
    protected void onBindViewHolder(String data, ItemTextBinding binding, int position) {
        if (position == 0) {
            binding.content.setTextColor(context.getColor(R.color.BiliBili_pink));
        }

        binding.content.setText(data);
        binding.content.setOnClickListener(v -> {
            if (onItemSelectedListener != null) {
                onItemSelectedListener.onSelected(preSelectedIndex, position);
                preSelectedIndex = position;
            }
        });
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public interface OnItemSelectedListener {
        void onSelected(int preIndex, int currentIndex);
    }
}
