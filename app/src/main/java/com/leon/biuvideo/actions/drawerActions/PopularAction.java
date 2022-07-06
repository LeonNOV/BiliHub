package com.leon.biuvideo.actions.drawerActions;

import android.util.Log;

import com.leon.biuvideo.base.baseAction.ActionData;
import com.leon.biuvideo.base.baseAction.BaseAction;

/**
 * @Author Leon
 * @Time 2022/06/18
 * @Desc
 */
public class PopularAction extends BaseAction {
    @Override
    public void createAdapter(int pageIndex) {
        switch (pageIndex) {
            case 0:
                Log.d(TAG, "letsGo: 综合热门");
                break;
            case 1:
                Log.d(TAG, "letsGo: 每周必看");
                break;
            case 2:
                Log.d(TAG, "letsGo: 入站必刷");
                break;
            case 3:
                Log.d(TAG, "letsGo: 排行榜");
                break;
            default:
                break;
        }
    }

    @Override
    public ActionData createActionData() {
        return new ActionData("热门&排行", new String[]{"综合热门", "每周必看", "入站必刷", "排行榜"});
    }
}
