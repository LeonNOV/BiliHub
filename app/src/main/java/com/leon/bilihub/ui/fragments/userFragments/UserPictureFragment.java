package com.leon.bilihub.ui.fragments.userFragments;

import androidx.recyclerview.widget.GridLayoutManager;

import com.leon.bilihub.base.baseFragment.BaseLazyFragment;
import com.leon.bilihub.beans.publicBeans.user.UserPicture;
import com.leon.bilihub.databinding.RefreshContentBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.user.UserPictureAdapter;
import com.leon.bilihub.ui.widget.loader.PaginationLoader;

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
        httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
        loader = new PaginationLoader<>(binding, new UserPictureAdapter(context), new GridLayoutManager(context, 2));
        loader.setGuide(userPicture -> userPicture.getData().getItems());
        loader.setUpdateInterface(loadType -> httpApi.getUserPicture(mid, ++pageNum));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }
}
