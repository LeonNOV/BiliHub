package com.leon.biuvideo.ui.fragments.drawerFragments.popularFragments;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.home.drawerFunction.popular.PopularData;
import com.leon.biuvideo.beans.home.drawerFunction.popular.PopularPrecious;
import com.leon.biuvideo.databinding.PageSubRecyclerBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.drawer.PopularAdapter;
import com.leon.biuvideo.ui.widget.loader.RecyclerViewLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Leon
 * @Time 2022/07/03
 * @Desc
 */
public class PreciousFragment extends BaseLazyFragment<PageSubRecyclerBinding> {
    private RecyclerViewLoader<PopularPrecious, PopularData> loader;

    @Override
    public PageSubRecyclerBinding getViewBinding() {
        return PageSubRecyclerBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        loader = new RecyclerViewLoader<>(binding.content.getRoot(), new PopularAdapter(context));
        loader.setGuide(precious -> {
            List<PopularData> popularDataList = new ArrayList<>(precious.getData().getList().size());

            for (PopularPrecious.Data.Media media : precious.getData().getList()) {
                popularDataList.add(new PopularData(PopularAdapter.PopularType.Hot, media.getPic(), media.getDuration(), media.getTitle(), media.getOwner().getName(),
                        media.getStat().getView(), media.getStat().getDanmaku(), media.getRcmdReason(), media.getBvid()));
            }
            return popularDataList;
        });
    }

    @Override
    protected void onLazyLoad() {
        loader.setObservable(new RetrofitClient(BaseUrl.API).getHttpApi().getPopularPrecious()).obtain(false);
    }
}
