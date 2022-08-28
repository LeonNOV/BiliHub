package com.leon.biuvideo.ui.fragments.drawerFragments.favorite;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.account.FavoriteFolder;
import com.leon.biuvideo.databinding.ComposeListBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.drawer.FolderAdapter;
import com.leon.biuvideo.ui.widget.loader.RecyclerViewLoader;
import com.leon.biuvideo.utils.PreferenceUtils;

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
