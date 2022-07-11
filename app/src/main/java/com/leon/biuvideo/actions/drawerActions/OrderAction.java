package com.leon.biuvideo.actions.drawerActions;

import android.content.Context;
import android.os.Parcelable;
import android.util.Log;

import androidx.viewbinding.ViewBinding;

import com.leon.biuvideo.base.baseAction.ActionData;
import com.leon.biuvideo.base.baseAction.BaseAction;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.beans.publicBeans.user.UserOrder;
import com.leon.biuvideo.databinding.RefreshContentBinding;
import com.leon.biuvideo.databinding.UserOrderItemBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.adapters.userAdapters.UserArticleAdapter;
import com.leon.biuvideo.ui.adapters.userAdapters.UserOrderAdapter;
import com.leon.biuvideo.utils.PaginationLoader;

/**
 * @Author Leon
 * @Time 2022/06/19
 * @Desc
 */
public class OrderAction extends BaseAction {
    private int pageNum = 0;
    private PaginationLoader<UserOrder, UserOrder.Data.Order> loader;
    private HttpApi httpApi;

    @Override
    public ActionData createActionData() {
        ActionData actionData = new ActionData("订阅", new String[]{"番剧", "剧集"});
        actionData.setFilterItems("全部", "想看", "在看", "看过");
        return actionData;
    }

    @Override
    public void onInit(int index, RefreshContentBinding binding, Context context) {
        httpApi = new RetrofitClient(BaseUrl.API).getHttpApi();
        loader = new PaginationLoader<>(binding, new UserOrderAdapter(context));
        loader.closeRefresh();
        loader.setGuide(userOrder -> userOrder.getData().getList());

        load(index, 0);
    }

    @Override
    public void onLazyLoad() {
        loader.firstObtain();
    }

    @Override
    public void onReload(int index, int tag) {
        load(index, tag);
    }

    private void load(int index, int tag) {
        pageNum = 0;
        loader.setUpdateInterface(loadType -> loader.setObservable(httpApi.getUserOrder(index, tag, ++pageNum, "mid")));
    }
}
