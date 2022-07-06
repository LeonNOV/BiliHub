package com.leon.biuvideo.ui.fragments.popularFragments;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.home.drawerFunction.Series;
import com.leon.biuvideo.databinding.FragmentPopularWeeklyBinding;
import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.SeriesAdapter;
import com.leon.biuvideo.ui.dialogs.SeriesPopupWindow;

import java.util.List;
import java.util.Locale;

/**
 * @Author Leon
 * @Time 2022/07/03
 * @Desc
 */
public class WeeklyFragment extends BaseLazyFragment<FragmentPopularWeeklyBinding> {
    private List<Series.Data.Item> itemList;
    private int selectedIndex = 0;

    @Override
    public FragmentPopularWeeklyBinding getViewBinding() {
        return FragmentPopularWeeklyBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        binding.period.setOnClickListener(v -> {
            SeriesPopupWindow seriesPopupWindow = new SeriesPopupWindow(context, itemList, selectedIndex);
            seriesPopupWindow.show(v);
            SeriesAdapter seriesAdapter = new SeriesAdapter(context, selectedIndex);
            seriesAdapter.setOnItemListener((index, item) -> {
                WeeklyFragment.this.selectedIndex = index;
                binding.period.setText(String.format(Locale.CHINESE, "第%d期 %s", item.getNumber(), item.getSubject()));
                seriesPopupWindow.dismiss();
            });
            seriesPopupWindow.setAdapter(seriesAdapter);
        });
    }

    @Override
    protected void onLazyLoad() {
        new ApiHelper<>(new RetrofitClient(BaseUrl.API).getHttpApi().getSeries())
                .setOnResult(series -> {
                    WeeklyFragment.this.itemList = series.getData().getList();
                    Series.Data.Item firstSeries = WeeklyFragment.this.itemList.get(0);
                    binding.period.setText(String.format(Locale.CHINESE, "第%d期 %s", firstSeries.getNumber(), firstSeries.getSubject()));
                })
                .doIt();

    }
}
