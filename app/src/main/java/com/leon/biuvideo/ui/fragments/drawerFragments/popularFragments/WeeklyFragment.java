package com.leon.biuvideo.ui.fragments.drawerFragments.popularFragments;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.home.drawerFunction.Series;
import com.leon.biuvideo.beans.home.drawerFunction.popular.PopularData;
import com.leon.biuvideo.beans.home.drawerFunction.popular.PopularWeekly;
import com.leon.biuvideo.databinding.FragmentPopularWeeklyBinding;
import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.SeriesAdapter;
import com.leon.biuvideo.ui.adapters.drawer.PopularAdapter;
import com.leon.biuvideo.ui.dialogs.SeriesPopupWindow;
import com.leon.biuvideo.ui.widget.loader.RecyclerViewLoader;

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

        httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
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
        new ApiHelper<>(new RetrofitClient(BaseUrl.API).getHttpApi().getSeries())
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
