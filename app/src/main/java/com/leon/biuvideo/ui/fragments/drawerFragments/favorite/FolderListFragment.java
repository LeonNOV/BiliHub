package com.leon.biuvideo.ui.fragments.drawerFragments.favorite;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.account.FavoriteFolder;
import com.leon.biuvideo.databinding.PageRecyclerBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.drawerAdapters.FolderAdapter;
import com.leon.biuvideo.utils.RecyclerViewLoader;

/**
 * @Author Leon
 * @Time 2022/07/12
 * @Desc
 */
public class FolderListFragment extends BaseLazyFragment<PageRecyclerBinding> {

    private RecyclerViewLoader<FavoriteFolder, FavoriteFolder.Data.Folder> loader;

    @Override
    public PageRecyclerBinding getViewBinding() {
        return PageRecyclerBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        HttpApi httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
        loader = new RecyclerViewLoader<FavoriteFolder, FavoriteFolder.Data.Folder>(binding.content, new FolderAdapter(context))
                .setGuide(favoriteFolder -> favoriteFolder.getData().getList())
                .setObservable(httpApi.getUserFolder("29120845"));
    }

    @Override
    protected void onLazyLoad() {
        loader.obtain(false);
    }
}
