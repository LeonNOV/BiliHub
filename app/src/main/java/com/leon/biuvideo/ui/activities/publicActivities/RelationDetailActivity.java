package com.leon.biuvideo.ui.activities.publicActivities;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.beans.account.RelationDetail;
import com.leon.biuvideo.databinding.RefreshContentBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.drawer.relation.RelationDetailAdapter;
import com.leon.biuvideo.utils.PaginationLoader;

/**
 * @Author Leon
 * @Time 2022/7/12
 * @Desc
 */
public class RelationDetailActivity extends BaseActivity<RefreshContentBinding> {
    public static final String PARAM = "tagId";
    private int pageNum = 0;

    @Override
    public RefreshContentBinding getViewBinding() {
        return RefreshContentBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        String tagId = params.getString(PARAM);

        HttpApi httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
        PaginationLoader<RelationDetail, RelationDetail.Data> loader = new PaginationLoader<>(binding, new RelationDetailAdapter(context));
        loader.closeRefresh();
        loader.setGuide(RelationDetail::getData);

        loader
                .setUpdateInterface(loadType -> httpApi.getUserRelationDetail(tagId, ++pageNum))
                .obtain();
    }
}