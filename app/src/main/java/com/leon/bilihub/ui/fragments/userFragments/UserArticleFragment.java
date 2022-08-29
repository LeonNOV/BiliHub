package com.leon.bilihub.ui.fragments.userFragments;

import com.leon.bilihub.base.baseFragment.BaseLazyFragment;
import com.leon.bilihub.beans.publicBeans.user.UserArticle;
import com.leon.bilihub.databinding.RefreshContentBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.user.UserArticleAdapter;
import com.leon.bilihub.ui.widget.loader.PaginationLoader;

/**
 * @Author Leon
 * @Time 2022/07/08
 * @Desc
 */
public class UserArticleFragment extends BaseLazyFragment<RefreshContentBinding> {
    private PaginationLoader<UserArticle, UserArticle.Data.Article> loader;
    private final String mid;
    private int pageNum = 0;

    public UserArticleFragment(String mid) {
        this.mid = mid;
    }

    @Override
    public RefreshContentBinding getViewBinding() {
        return RefreshContentBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        HttpApi httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
        loader = new PaginationLoader<>(binding, new UserArticleAdapter(context));
        loader.setGuide(userArticle -> userArticle.getData().getArticles());
        loader.setUpdateInterface(loadType -> httpApi.getUserArticle(mid, ++pageNum));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }
}
