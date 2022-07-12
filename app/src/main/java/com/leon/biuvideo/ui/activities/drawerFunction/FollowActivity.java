package com.leon.biuvideo.ui.activities.drawerFunction;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.beans.account.RelationTags;
import com.leon.biuvideo.databinding.PageRecyclerBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.RelationTagsAdapter;
import com.leon.biuvideo.utils.RecyclerViewLoader;

/**
 * @Author Leon
 * @Time 2022/7/12
 * @Desc
 */
public class FollowActivity extends BaseActivity<PageRecyclerBinding> {
    @Override
    public PageRecyclerBinding getViewBinding() {
        return PageRecyclerBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        HttpApi httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
        RecyclerViewLoader<RelationTags, RelationTags.Data> loader = new RecyclerViewLoader<>(binding.getRoot(), new RelationTagsAdapter(context));
        loader
                .setGuide(RelationTags::getData)
                .setObservable(httpApi.getUserRelationTags())
                .obtain(false);
    }
}