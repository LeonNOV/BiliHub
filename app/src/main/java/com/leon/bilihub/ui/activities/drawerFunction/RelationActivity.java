package com.leon.bilihub.ui.activities.drawerFunction;

import com.leon.bilihub.base.baseActivity.BaseActivity;
import com.leon.bilihub.beans.account.RelationTags;
import com.leon.bilihub.databinding.PageRecyclerBinding;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.ui.adapters.drawer.relation.RelationTagsAdapter;
import com.leon.bilihub.ui.dialogs.TipDialog;
import com.leon.bilihub.ui.widget.loader.RecyclerViewLoader;
import com.leon.bilihub.utils.PreferenceUtils;

/**
 * @Author Leon
 * @Time 2022/7/12
 * @Desc
 */
public class RelationActivity extends BaseActivity<PageRecyclerBinding> {
    @Override
    public PageRecyclerBinding getViewBinding() {
        return PageRecyclerBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        binding.topBar.setTopBarTitle("我的关注");
        if (PreferenceUtils.getLoginStatus(context)) {
            RecyclerViewLoader<RelationTags, RelationTags.Data> loader = new RecyclerViewLoader<>(binding.content, new RelationTagsAdapter(context));
            loader
                    .setGuide(RelationTags::getData)
                    .setObservable(new RetrofitClient(BaseUrl.API, context).getHttpApi().getUserRelationGroups())
                    .obtain(false);
        } else {
            TipDialog.showLoginTipDialog(context);
        }
    }
}