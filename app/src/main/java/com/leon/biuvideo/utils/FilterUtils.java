package com.leon.biuvideo.utils;

import android.content.Context;

import androidx.appcompat.widget.AppCompatTextView;

import com.leon.biuvideo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author Leon
 * @Time 2022/07/16
 * @Desc
 */
public class FilterUtils<T> {
    private final Context context;

    private final Map<Integer, AppCompatTextView> textViewMap = new HashMap<>();
    private int selectedPosition = 0;
    private OnFilterCallback<T> onFilterCallback;

    public FilterUtils(Context context, int defaultSelected) {
        this.context = context;
        this.selectedPosition = defaultSelected;
    }

    public void selected(T data, int position) {
        if (selectedPosition != position) {
            if (onFilterCallback != null) {
                onFilterCallback.onCall(data);
            }
            changeColor(position);
        }
    }

    public void addTextView(int position, AppCompatTextView textView) {
        if (!this.textViewMap.containsKey(position)) {
            if (position == selectedPosition) {
                textView.setTextColor(context.getColor(R.color.BiliBili_pink));
            }

            this.textViewMap.put(position, textView);
        }
    }

    private void changeColor(int position) {
        Objects.requireNonNull(this.textViewMap.get(position)).setTextColor(context.getColor(R.color.BiliBili_pink));
        Objects.requireNonNull(this.textViewMap.get(selectedPosition)).setTextColor(context.getColor(R.color.infoColor));

        selectedPosition = position;
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
        void onCall(T t);
    }
}
