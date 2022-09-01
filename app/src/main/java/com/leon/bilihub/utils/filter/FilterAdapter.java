package com.leon.bilihub.utils.filter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.databinding.ItemFilterBinding;

/**
 * @Author Leon
 * @Time 2022/07/16
 * @Desc
 */
public class FilterAdapter<T> extends ViewBindingAdapter<T, ItemFilterBinding> {
    private final FilterUtils filterUtils;
    private OnFilterCallback<T> onFilterCallback;
    private int selected = 0;

    public FilterAdapter(Context context) {
        super(context);

        this.filterUtils = new FilterUtils(context, 0);
    }

    @Override
    protected ItemFilterBinding getItemViewBinding(Context context, ViewGroup parent) {
        return ItemFilterBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_filter, parent, false));
    }

    @Override
    protected void onBindViewHolder(T data, ItemFilterBinding binding, int position) {
        filterUtils.addTextView(position, binding.filter);
        binding.getRoot().setOnClickListener(v -> {
            if (selected != position) {
                onFilterCallback.onReload(data);
                filterUtils.selected(position);
                selected = position;
            }
        });

        if (onFilterCallback != null) {
            binding.filter.setText(onFilterCallback.onGuide(data));
        }
    }

    public void setOnFilterCallback(OnFilterCallback<T> onFilterCallback) {
        this.onFilterCallback = onFilterCallback;
    }

    public interface OnFilterCallback<T> {
        /**
         * 当过滤项目被点击时的回调
         *
         * @param t data
         */
        void onReload(T t);

        /**
         * 指定显示数据
         *
         * @param t T
         * @return  String
         */
        String onGuide(T t);
    }
}
