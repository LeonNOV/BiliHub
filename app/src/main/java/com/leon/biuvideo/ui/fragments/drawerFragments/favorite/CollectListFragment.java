package com.leon.biuvideo.ui.fragments.drawerFragments.favorite;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.account.Collect;
import com.leon.biuvideo.databinding.RefreshContentBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.drawer.CollectAdapter;
import com.leon.biuvideo.utils.PaginationLoader;

/**
 * @Author Leon
 * @Time 2022/07/12
 * @Desc
 */
public class CollectListFragment extends BaseLazyFragment<RefreshContentBinding> {
    private PaginationLoader<Collect, Collect.Data.CollectData> loader;
    private int pageNum = 0;

    @Override
    public RefreshContentBinding getViewBinding() {
        return RefreshContentBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        // todo(header)
        HttpApi httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
        loader = new PaginationLoader<>(binding, new CollectAdapter(context));
        loader.closeRefresh();
        loader.setGuide(collect -> collect.getData().getList());
        loader.setObservable(httpApi.getUserCollect(++pageNum, "49405324"));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }
}
