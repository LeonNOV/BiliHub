package com.leon.biuvideo.ui.activities.publicActivities;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.beans.account.RelationDetail;
import com.leon.biuvideo.databinding.PagePaginationBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.drawer.relation.RelationDetailAdapter;
import com.leon.biuvideo.ui.widget.loader.PaginationLoader;

/**
 * @Author Leon
 * @Time 2022/7/12
 * @Desc
 */
public class RelationDetailActivity extends BaseActivity<PagePaginationBinding> {
    public static final String PARAM_A = "tagId";
    public static final String PARAM_B = "tagName";
    private int pageNum = 0;

    @Override
    public PagePaginationBinding getViewBinding() {
        return PagePaginationBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        String tagId = params.getString(PARAM_A);
        binding.topBar.setTopBarTitle(params.getString(PARAM_B));

        HttpApi httpApi = new RetrofitClient(BaseUrl.API, context).getHttpApi();
        PaginationLoader<RelationDetail, RelationDetail.Data> loader = new PaginationLoader<>(binding.content, new RelationDetailAdapter(context));
        loader.setGuide(RelationDetail::getData);
        loader.setUpdateInterface(loadType -> httpApi.getUserRelationDetail(tagId, ++pageNum)).obtain();
    }
}