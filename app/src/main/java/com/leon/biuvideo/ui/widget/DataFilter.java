package com.leon.biuvideo.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.leon.biuvideo.R;

/**
 * @Author Leon
 * @Time 2022/07/11
 * @Desc
 */
public class DataFilter extends LinearLayoutCompat implements View.OnClickListener {
    private final Context context;
    private int selectedColor;
    private int unselectedColor;
    private int nowSelected = 0;
    private onSelectedListener onSelectedListener;

    public DataFilter(@NonNull Context context) {
        super(context);
        this.context = context;

        init();
    }

    public DataFilter(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        init();
    }

    private void init() {
        this.selectedColor = context.getColor(R.color.black);
        this.unselectedColor = context.getColor(R.color.infoColor);
    }

    public DataFilter setFilterItem(String ... items) {
        for (int i = 0; i < items.length; i++) {
            AppCompatTextView itemView = new AppCompatTextView(context, null, R.style.FilterItemStyle);
            itemView.setText(items[i]);
            itemView.setTextSize(context.getResources().getDimensionPixelSize(R.dimen.DataFilterTextSize));
            itemView.setOnClickListener(this);

            if (i == 0) {
                itemView.setTextColor(selectedColor);
            } else {
                itemView.setTextColor(unselectedColor);
            }

            this.addView(itemView);
        }

        return this;
    }

    @Override
    public void onClick(View v) {
        ((AppCompatTextView) getChildAt(nowSelected)).setTextColor(unselectedColor);
        ((AppCompatTextView) v).setTextColor(selectedColor);

        this.nowSelected = indexOfChild(v);

        if (onSelectedListener != null) {
            onSelectedListener.onSelect(this.nowSelected);
        }
    }

    public void setOnSelected(onSelectedListener onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }

    public interface onSelectedListener {
        void onSelect(int itemIndex);
    }
}
