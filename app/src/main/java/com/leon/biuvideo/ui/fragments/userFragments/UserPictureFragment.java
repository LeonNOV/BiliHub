package com.leon.biuvideo.ui.fragments.userFragments;

import androidx.recyclerview.widget.GridLayoutManager;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.publicBeans.user.UserPicture;
import com.leon.biuvideo.databinding.RefreshContentBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.user.UserPictureAdapter;
import com.leon.biuvideo.utils.PaginationLoader;

/**
 * @Author Leon
 * @Time 2022/07/08
 * @Desc
 */
public class UserPictureFragment extends BaseLazyFragment<RefreshContentBinding> {
    private HttpApi httpApi;
    private PaginationLoader<UserPicture, UserPicture.Data.Item> loader;
    private final String mid;
    private int pageNum = -1;

    public UserPictureFragment(String mid) {
        this.mid = mid;
    }

    @Override
    public RefreshContentBinding getViewBinding() {
        return RefreshContentBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        binding.container.content.setLayoutManager(new GridLayoutManager(context, 2));

        httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
        loader = new PaginationLoader<>(binding, new UserPictureAdapter(context));
        loader.closeRefresh();
        loader.setGuide(userPicture -> userPicture.getData().getItems());
        loader.setUpdateInterface(loadType -> loader.setObservable(httpApi.getUserPicture(mid, ++pageNum)));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }
}
