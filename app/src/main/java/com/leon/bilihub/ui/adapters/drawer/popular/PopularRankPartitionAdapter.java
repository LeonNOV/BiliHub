package com.leon.bilihub.ui.adapters.drawer.popular;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.databinding.ItemTextBinding;

/**
 * @Author Leon
 * @Time 2022/07/04
 * @Desc
 */
public class PopularRankPartitionAdapter extends ViewBindingAdapter<String, ItemTextBinding> {
    private int preSelectedIndex = 0;
    private OnItemSelectedListener onItemSelectedListener;

    public PopularRankPartitionAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemTextBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemTextBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_text, parent, false));
    }

    @Override
    protected void onBindViewHolder(String data, ItemTextBinding binding, int position) {
        if (position == 0) {
            binding.content.setTextColor(context.getColor(R.color.pink));
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
