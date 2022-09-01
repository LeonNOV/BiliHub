package com.leon.bilihub.ui.fragments.drawerFragments.favorite;

import com.leon.bilihub.base.baseFragment.BaseLazyFragment;
import com.leon.bilihub.beans.account.CollectFolder;
import com.leon.bilihub.databinding.RefreshContentBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.drawer.favFolder.CollectAdapter;
import com.leon.bilihub.ui.widget.loader.PaginationLoader;
import com.leon.bilihub.utils.PreferenceUtils;

/**
 * @Author Leon
 * @Time 2022/07/12
 * @Desc
 */
public class CollectListFragment extends BaseLazyFragment<RefreshContentBinding> {
    private PaginationLoader<CollectFolder, CollectFolder.Data.CollectData> loader;
    private int pageNum = 0;

    @Override
    public RefreshContentBinding getViewBinding() {
        return RefreshContentBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        HttpApi httpApi = new RetrofitClient(BaseUrl.API, context).getHttpApi();
        loader = new PaginationLoader<>(binding, new CollectAdapter(context));
        loader.setGuide(collectFolder -> collectFolder.getData().getList());
        loader.setUpdateInterface(loadType -> httpApi.getCollectFolder(++pageNum, PreferenceUtils.getUid(context)));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }
}
