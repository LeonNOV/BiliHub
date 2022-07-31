package com.leon.biuvideo.utils.filter;

import android.content.Context;

import androidx.appcompat.widget.AppCompatTextView;

import com.leon.biuvideo.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author Leon
 * @Time 2022/07/16
 * @Desc
 */
public class FilterUtils {
    private final Context context;

    private final Map<Integer, AppCompatTextView> textViewMap = new HashMap<>();
    private int selectedPosition;

    public FilterUtils(Context context, int defaultSelected) {
        this.context = context;
        this.selectedPosition = defaultSelected;
    }

    public void selected(int position) {
        changeColor(position);
        this.selectedPosition = position;
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
    }
}
