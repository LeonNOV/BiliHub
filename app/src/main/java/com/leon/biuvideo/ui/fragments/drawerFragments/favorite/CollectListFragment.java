package com.leon.biuvideo.ui.fragments.drawerFragments.favorite;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.account.CollectFolder;
import com.leon.biuvideo.databinding.RefreshContentBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.drawer.CollectAdapter;
import com.leon.biuvideo.ui.widget.loader.PaginationLoader;
import com.leon.biuvideo.utils.PreferenceUtils;

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
