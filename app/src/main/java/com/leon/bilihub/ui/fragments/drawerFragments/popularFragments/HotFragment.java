package com.leon.bilihub.ui.fragments.drawerFragments.popularFragments;

import com.leon.bilihub.base.baseFragment.BaseLazyFragment;
import com.leon.bilihub.beans.home.drawerFunction.popular.PopularData;
import com.leon.bilihub.beans.home.drawerFunction.popular.PopularHot;
import com.leon.bilihub.databinding.RefreshContentBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.drawer.popular.PopularAdapter;
import com.leon.bilihub.ui.widget.loader.PaginationLoader;

import java.util.ArrayList;
import java.util.List;

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
        httpApi = new RetrofitClient(BaseUrl.API, context).getHttpApi();
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
