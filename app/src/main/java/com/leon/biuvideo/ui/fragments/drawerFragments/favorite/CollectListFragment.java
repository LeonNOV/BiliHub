package com.leon.biuvideo.ui.fragments.drawerFragments.favorite;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.account.Collect;
import com.leon.biuvideo.databinding.RefreshContentBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.http.TestValue;
import com.leon.biuvideo.ui.adapters.drawer.CollectAdapter;
import com.leon.biuvideo.ui.widget.loader.PaginationLoader;

import java.util.Map;

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
        HttpApi httpApi = new RetrofitClient(BaseUrl.API, Map.of(HttpApi.COOKIE, TestValue.TEST_COOKIE)).getHttpApi();
        loader = new PaginationLoader<>(binding, new CollectAdapter(context));
        loader.setGuide(collect -> collect.getData().getList());
        loader.setUpdateInterface(loadType -> httpApi.getUserCollect(++pageNum, "49405324"));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }
}
