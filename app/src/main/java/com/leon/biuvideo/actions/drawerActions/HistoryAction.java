package com.leon.biuvideo.actions.drawerActions;

import android.content.Context;

import com.leon.biuvideo.base.baseAction.ActionData;
import com.leon.biuvideo.base.baseAction.BaseAction;
import com.leon.biuvideo.databinding.RefreshContentBinding;

/**
 * @Author Leon
 * @Time 2022/06/19
 * @Desc
 */
public class HistoryAction extends BaseAction {

    @Override
    public ActionData createActionData() {
        return new ActionData("历史记录");
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
