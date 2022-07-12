package com.leon.biuvideo.ui.activities.publicActivities;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivityRelationListBinding;
import com.leon.biuvideo.databinding.RefreshContainerBinding;
import com.leon.biuvideo.databinding.RefreshContentBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.utils.PaginationLoader;
import com.leon.biuvideo.utils.RecyclerViewLoader;

/**
 * @Author Leon
 * @Time 2022/7/12
 * @Desc
 */
public class RelationListActivity extends BaseActivity<RefreshContentBinding> {
    public static final String PARAM = "tagid";

    @Override
    public RefreshContentBinding getViewBinding() {
        return RefreshContentBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        String tagid = params.getString(PARAM);

        HttpApi httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
//        new RecyclerViewLoader<>()
    }
}