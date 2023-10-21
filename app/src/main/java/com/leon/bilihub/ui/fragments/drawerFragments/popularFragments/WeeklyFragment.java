package com.leon.bilihub.ui.fragments.drawerFragments.popularFragments;

import com.leon.bilihub.base.baseFragment.BaseLazyFragment;
import com.leon.bilihub.beans.home.drawerFunction.Series;
import com.leon.bilihub.beans.home.drawerFunction.popular.PopularData;
import com.leon.bilihub.beans.home.drawerFunction.popular.PopularWeekly;
import com.leon.bilihub.databinding.FragmentPopularWeeklyBinding;
import com.leon.bilihub.http.ApiHelper;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.SeriesAdapter;
import com.leon.bilihub.ui.adapters.drawer.popular.PopularAdapter;
import com.leon.bilihub.ui.dialogs.SeriesPopupWindow;
import com.leon.bilihub.ui.widget.loader.RecyclerViewLoader;

import java.util.ArrayList;
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


    private HttpApi httpApi;
    private PopularAdapter adapter;
    private RecyclerViewLoader<PopularWeekly, PopularData> loader;

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
            seriesAdapter.setOnItemListener((index, series) -> {
                WeeklyFragment.this.selectedIndex = index;
                binding.period.setText(String.format(Locale.CHINESE, "第%d期 %s", series.getNumber(), series.getSubject()));
                seriesPopupWindow.dismiss();

                reload(series);
            });
            seriesPopupWindow.setAdapter(seriesAdapter);
        });

        httpApi = new RetrofitClient(BaseUrl.API, context).getHttpApi();
        adapter = new PopularAdapter(context);

        loader = new RecyclerViewLoader<>(binding.content.getRoot(), adapter);
        loader.setGuide(popularWeekly -> {
            List<PopularData> popularDataList = new ArrayList<>(popularWeekly.getData().getList().size());

            for (PopularWeekly.Data.Media media : popularWeekly.getData().getList()) {
                popularDataList.add(new PopularData(PopularAdapter.PopularType.Hot, media.getPic(), media.getDuration(), media.getTitle(), media.getOwner().getName(),
                        media.getStat().getView(), media.getStat().getDanmaku(), media.getRcmdReason(), media.getBvid()));
            }
            return popularDataList;
        });
    }

    @Override
    protected void onLazyLoad() {
        new ApiHelper<>(new RetrofitClient(BaseUrl.API, context).getHttpApi().getSeries())
                .setOnResult(series -> {
                    WeeklyFragment.this.itemList = series.getData().getList();
                    Series.Data.Item firstSeries = WeeklyFragment.this.itemList.get(0);
                    binding.period.setText(String.format(Locale.CHINESE, "第%d期 %s", firstSeries.getNumber(), firstSeries.getSubject()));

                    reload(firstSeries);
                })
                .doIt();

    }

    private void reload(Series.Data.Item series) {
        adapter.removeAll();
        loader.setObservable(httpApi.getPopularWeekly(series.getNumber())).obtain(true);
    }
}
