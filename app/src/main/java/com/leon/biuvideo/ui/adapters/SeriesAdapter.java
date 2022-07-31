package com.leon.biuvideo.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.home.drawerFunction.Series;
import com.leon.biuvideo.databinding.ItemTextBinding;

import java.util.Locale;

/**
 * @Author Leon
 * @Time 2022/07/05
 * @Desc
 */
public class SeriesAdapter extends BaseViewBindingAdapter<Series.Data.Item, ItemTextBinding> {
    private final int selectedIndex;
    private OnItemListener onItemListener;

    public SeriesAdapter(Context context, int selectedIndex) {
        super(context);
        this.selectedIndex = selectedIndex;
    }

    @Override
    protected ItemTextBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemTextBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_text, parent, false));
    }

    @Override
    protected void onBindViewHolder(Series.Data.Item data, ItemTextBinding binding, int position) {
        binding.content.setText(String.format(Locale.CHINESE, "第%d期 %s", data.getNumber(), data.getSubject()));
        if (selectedIndex == position) {
            binding.content.setTextColor(context.getColor(R.color.BiliBili_pink));
        }
        binding.getRoot().setOnClickListener(v -> {
            if (onItemListener != null) {
                onItemListener.selected(position, data);
            }
        });
    }

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public interface OnItemListener {
        void selected(int index, Series.Data.Item item);
    }
}
