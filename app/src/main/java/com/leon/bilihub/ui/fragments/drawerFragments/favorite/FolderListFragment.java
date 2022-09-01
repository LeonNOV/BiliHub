package com.leon.bilihub.ui.fragments.drawerFragments.favorite;

import com.leon.bilihub.base.baseFragment.BaseLazyFragment;
import com.leon.bilihub.beans.account.FavoriteFolder;
import com.leon.bilihub.databinding.ComposeListBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.drawer.favFolder.FolderAdapter;
import com.leon.bilihub.ui.widget.loader.RecyclerViewLoader;
import com.leon.bilihub.utils.PreferenceUtils;

/**
 * @Author Leon
 * @Time 2022/07/12
 * @Desc
 */
public class FolderListFragment extends BaseLazyFragment<ComposeListBinding> {

    private RecyclerViewLoader<FavoriteFolder, FavoriteFolder.Data.Folder> loader;

    @Override
    public ComposeListBinding getViewBinding() {
        return ComposeListBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        HttpApi httpApi = new RetrofitClient(BaseUrl.API, context).getHttpApi();
        loader = new RecyclerViewLoader<FavoriteFolder, FavoriteFolder.Data.Folder>(binding.getRoot(), new FolderAdapter(context))
                .setGuide(favoriteFolder -> favoriteFolder.getData().getList())
                .setObservable(httpApi.getFavoriteFolder(PreferenceUtils.getUid(context)));
    }

    @Override
    protected void onLazyLoad() {
        loader.obtain(false);
    }
}
