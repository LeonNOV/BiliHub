package com.leon.biuvideo.actions.drawerActions;

import android.content.Context;

import com.leon.biuvideo.base.baseAction.ActionData;
import com.leon.biuvideo.base.baseAction.BaseAction;
import com.leon.biuvideo.beans.publicBeans.user.UserOrder;
import com.leon.biuvideo.databinding.RefreshContentBinding;
import com.leon.biuvideo.databinding.UserOrderItemBinding;

/**
 * @Author Leon
 * @Time 2022/06/19
 * @Desc
 */
public class FavoriteAction extends BaseAction {
    @Override
    public ActionData createActionData() {
        return new ActionData("收藏", new String[]{"收藏夹", "收藏", "订阅"});
    }

    @Override
    public void onInit(int index, RefreshContentBinding binding, Context context) {

    }

    @Override
    public void onLazyLoad() {

    }

    @Override
    public void onReload(int index, int tag) {

    }
}
