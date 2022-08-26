package com.leon.biuvideo.ui.fragments.drawerFragments.popularFragments;

import android.os.Parcelable;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.home.drawerFunction.popular.PopularData;
import com.leon.biuvideo.beans.home.drawerFunction.popular.PopularRank;
import com.leon.biuvideo.beans.home.drawerFunction.popular.PopularRankBangumi;
import com.leon.biuvideo.beans.home.drawerFunction.popular.PopularRankPgc;
import com.leon.biuvideo.databinding.PageFilterRecyclerBinding;
import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.Condition;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.Rank;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.drawer.PopularAdapter;
import com.leon.biuvideo.ui.adapters.drawer.relation.PopularRankAdapter;
import com.leon.biuvideo.utils.ViewUtils;
import com.leon.biuvideo.utils.filter.FilterAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Leon
 * @Time 2022/08/03
 * @Desc
 */
public class RankFragment extends BaseLazyFragment<PageFilterRecyclerBinding> {
    private int pre = -1;

    private PopularRankAdapter adapter;
    private List<Rank<? extends Parcelable>> rankList;

    @Override
    public PageFilterRecyclerBinding getViewBinding() {
        return PageFilterRecyclerBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        HttpApi httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
        rankList = new ArrayList<>();
        rankList.add(new Rank<>("全站", httpApi.getPopularRank("0"), 0));
        rankList.add(new Rank<>("番剧", httpApi.getPopularRankBangumi(), 2));
        rankList.add(new Rank<>("国产动画", httpApi.getPopularRankPgc(Condition.SeasonType.GcAnime), 1));
        rankList.add(new Rank<>("国创相关", httpApi.getPopularRank("168"), 0));
        rankList.add(new Rank<>("纪录片", httpApi.getPopularRankPgc(Condition.SeasonType.Document), 1));
        rankList.add(new Rank<>("动画", httpApi.getPopularRank("1"), 0));
        rankList.add(new Rank<>("音乐", httpApi.getPopularRank("3"), 0));
        rankList.add(new Rank<>("舞蹈", httpApi.getPopularRank("129"), 0));
        rankList.add(new Rank<>("游戏", httpApi.getPopularRank("4"), 0));
        rankList.add(new Rank<>("知识", httpApi.getPopularRank("36"), 0));
        rankList.add(new Rank<>("科技", httpApi.getPopularRank("188"), 0));
        rankList.add(new Rank<>("运动", httpApi.getPopularRank("234"), 0));
        rankList.add(new Rank<>("汽车", httpApi.getPopularRank("223"), 0));
        rankList.add(new Rank<>("生活", httpApi.getPopularRank("160"), 0));
        rankList.add(new Rank<>("美食", httpApi.getPopularRank("211"), 0));
        rankList.add(new Rank<>("动物圈", httpApi.getPopularRank("217"), 0));
        rankList.add(new Rank<>("鬼畜", httpApi.getPopularRank("119"), 0));
        rankList.add(new Rank<>("时尚", httpApi.getPopularRank("155"), 0));
        rankList.add(new Rank<>("娱乐", httpApi.getPopularRank("5"), 0));
        rankList.add(new Rank<>("影视", httpApi.getPopularRank("181"), 0));
        rankList.add(new Rank<>("电影", httpApi.getPopularRankPgc(Condition.SeasonType.Movie), 1));
        rankList.add(new Rank<>("电视剧", httpApi.getPopularRankPgc(Condition.SeasonType.TelePlay), 1));
        rankList.add(new Rank<>("综艺", httpApi.getPopularRankPgc(Condition.SeasonType.Variety), 1));
        rankList.add(new Rank<>("原创", httpApi.getPopularRank("0", "origin"), 0));
        rankList.add(new Rank<>("新人", httpApi.getPopularRank("0", "rookie"), 0));

        FilterAdapter<Rank<? extends Parcelable>> filterAdapter = new FilterAdapter<>(context);
        filterAdapter.setOnFilterCallback(new FilterAdapter.OnFilterCallback<>() {
            @Override
            public void onReload(Rank<? extends Parcelable> rank) {
                reload(rankList.indexOf(rank), rank);
            }

            @Override
            public String onGuide(Rank<? extends Parcelable> rank) {
                return rank.getName();
            }
        });
        filterAdapter.appendHead(rankList);
        binding.filter.setAdapter(filterAdapter);

        adapter = new PopularRankAdapter(context);
        ViewUtils.listInitializer(binding.content, adapter);
    }

    @Override
    protected void onLazyLoad() {
        reload(0, rankList.get(0));
    }

    private void reload(int index, Rank<? extends Parcelable> rank) {
        if (pre != index) {
            pre = index;

            adapter.removeAll();
            if (rank.getType() == 0) {
                adapter.setLayoutId(PopularRankAdapter.ITEM_VIDEO);
            } else {
                adapter.setLayoutId(PopularRankAdapter.ITEM_RANK_PGC);
            }

            new ApiHelper<>(rank.getObserver()).setOnResult(parcelable -> {
                List<PopularData> popularDataList = new ArrayList<>();

                if (parcelable instanceof PopularRank) {
                    PopularRank popularRank = (PopularRank) parcelable;
                    for (PopularRank.Data.Media media : popularRank.getData().getList()) {
                        popularDataList.add(new PopularData(PopularAdapter.PopularType.Rank, media.getPic(), media.getDuration(), media.getTitle(), media.getOwner().getName(),
                                media.getStat().getView(), media.getStat().getDanmaku(), "", media.getBvid()));
                    }
                } else if (parcelable instanceof PopularRankPgc) {
                    PopularRankPgc popularRankPgc = (PopularRankPgc) parcelable;
                    for (PopularRankPgc.Data.Media media : popularRankPgc.getData().getList()) {
                        popularDataList.add(new PopularData(PopularAdapter.PopularType.Rank, media.getCover(), 0, media.getTitle(), media.getNewEp().getIndexShow(),
                                media.getStat().getView(), media.getStat().getDanmaku(), "", media.getSeasonId()));
                    }
                } else if (parcelable instanceof PopularRankBangumi) {
                    PopularRankBangumi rankBangumi = (PopularRankBangumi) parcelable;
                    for (PopularRankBangumi.Result.Media media : rankBangumi.getResult().getList()) {
                        popularDataList.add(new PopularData(PopularAdapter.PopularType.Rank, media.getCover(), 0, media.getTitle(), media.getNewEp().getIndexShow(),
                                media.getStat().getView(), media.getStat().getDanmaku(), "", media.getSeasonId()));
                    }
                } else {
                    return;
                }

                adapter.appendHead(popularDataList);
            }).doIt();
        }
    }
}
