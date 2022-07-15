package com.leon.biuvideo.ui.fragments.userFragments;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.publicBeans.user.UserArticle;
import com.leon.biuvideo.databinding.RefreshContentBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.user.UserArticleAdapter;
import com.leon.biuvideo.utils.PaginationLoader;

/**
 * @Author Leon
 * @Time 2022/07/08
 * @Desc
 */
public class UserArticleFragment extends BaseLazyFragment<RefreshContentBinding> {
    private HttpApi httpApi;
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
        httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
        loader = new PaginationLoader<>(binding, new UserArticleAdapter(context));
        loader.closeRefresh();
        loader.setGuide(userArticle -> userArticle.getData().getArticles());
        loader.setUpdateInterface(loadType -> loader.setObservable(httpApi.getUserArticle(mid, ++pageNum)));
    }

    @Override
    protected void onLazyLoad() {
        loader.firstObtain();
    }
}
