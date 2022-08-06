package com.leon.biuvideo.ui.fragments.drawerFragments.popularFragments;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.home.drawerFunction.popular.PopularData;
import com.leon.biuvideo.beans.home.drawerFunction.popular.PopularHot;
import com.leon.biuvideo.databinding.RefreshContentBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.http.TestValue;
import com.leon.biuvideo.ui.adapters.drawer.PopularAdapter;
import com.leon.biuvideo.ui.widget.loader.PaginationLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/07/03
 * @Desc
 */
public class HotFragment extends BaseLazyFragment<RefreshContentBinding> {
    private int pageNum = 0;
    private HttpApi httpApi;
    private PaginationLoader<PopularHot, PopularData> loader;

    @Override
    public RefreshContentBinding getViewBinding() {
        return RefreshContentBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        httpApi = new RetrofitClient(BaseUrl.API, Map.of(HttpApi.COOKIE, TestValue.TEST_COOKIE)).getHttpApi();
        loader = new PaginationLoader<>(binding, new PopularAdapter(context));
        loader.setGuide(popularHot -> {
            List<PopularData> popularDataList = new ArrayList<>(popularHot.getData().getList().size());

            for (PopularHot.Data.Media media : popularHot.getData().getList()) {
                popularDataList.add(new PopularData(PopularAdapter.PopularType.Hot, media.getPic(), media.getDuration(), media.getTitle(),media.getOwner().getName(),
                        media.getStat().getView(), media.getStat().getDanmaku(), media.getRcmdReason().getContent(), media.getBvid()));
            }
            return popularDataList;
        });
        loader.setUpdateInterface(loadType -> httpApi.getPopularHot(++pageNum));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }
}
