package com.leon.biuvideo.ui.fragments.drawerFragments.favorite;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.account.FavoriteFolder;
import com.leon.biuvideo.databinding.ComposeListBinding;
import com.leon.biuvideo.databinding.PageRecyclerBinding;
import com.leon.biuvideo.databinding.RefreshContentBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.http.TestValue;
import com.leon.biuvideo.ui.adapters.drawer.FolderAdapter;
import com.leon.biuvideo.utils.RecyclerViewLoader;

import java.util.Map;

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
        HttpApi httpApi = new RetrofitClient(BaseUrl.API, Map.of(HttpApi.COOKIE, TestValue.TEST_COOKIE)).getHttpApi();
        loader = new RecyclerViewLoader<FavoriteFolder, FavoriteFolder.Data.Folder>(binding.getRoot(), new FolderAdapter(context))
                .setGuide(favoriteFolder -> favoriteFolder.getData().getList())
                .setObservable(httpApi.getUserFolder("29120845"));
    }

    @Override
    protected void onLazyLoad() {
        loader.obtain(false);
    }
}
