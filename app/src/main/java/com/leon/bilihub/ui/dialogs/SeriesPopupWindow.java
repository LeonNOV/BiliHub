package com.leon.bilihub.ui.dialogs;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.leon.bilihub.R;
import com.leon.bilihub.beans.home.drawerFunction.Series;
import com.leon.bilihub.databinding.DialogSeriesBinding;
import com.leon.bilihub.ui.adapters.SeriesAdapter;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/07/05
 * @Desc
 */
public class SeriesPopupWindow extends PopupWindow {
    private final List<Series.Data.Item> itemList;
    private final Context context;
    private final DialogSeriesBinding binding;
    private final int selectedIndex;


    public SeriesPopupWindow(Context context, List<Series.Data.Item> itemList, int selectedIndex) {
        this.context = context;
        this.itemList = itemList;
        this.selectedIndex = selectedIndex;

        binding = DialogSeriesBinding.bind(LayoutInflater.from(context).inflate(R.layout.dialog_series, null));
        setContentView(binding.getRoot());

        init();
    }

    private void init() {
        this.setHeight(context.getResources().getDimensionPixelSize(R.dimen.dialogH));
        this.setWidth(ViewGroup.MarginLayoutParams.MATCH_PARENT);
        this.setOutsideTouchable(true);

        SeriesAdapter seriesAdapter = new SeriesAdapter(context, selectedIndex);
        seriesAdapter.appendHead(itemList);

        binding.content.setAdapter(seriesAdapter);
    }

    public void setAdapter(SeriesAdapter seriesAdapter) {
        binding.content.setAdapter(seriesAdapter);
        seriesAdapter.appendHead(itemList);
        binding.content.smoothScrollToPosition(selectedIndex + 2);
    }

    public void show(View anchor) {
        this.showAsDropDown(anchor, 0, 0, Gravity.CENTER_HORIZONTAL);
    }
}
