package com.leon.biuvideo.actions.drawerActions;

import android.util.Log;

import com.leon.biuvideo.base.baseAction.ActionData;
import com.leon.biuvideo.base.baseAction.BaseAction;

/**
 * @Author Leon
 * @Time 2022/06/19
 * @Desc
 */
public class OrderAction extends BaseAction {
    @Override
    public void createAdapter(int pageIndex) {
        switch (pageIndex) {
            case 0:
                Log.d(TAG, "letsGo: 番剧");
                break;
            case 1:
                Log.d(TAG, "letsGo: 剧集");
                break;
            default:
                break;
        }
    }

    @Override
    public ActionData createActionData() {
        return new ActionData("订阅", new String[]{"番剧", "剧集"});
    }
}
