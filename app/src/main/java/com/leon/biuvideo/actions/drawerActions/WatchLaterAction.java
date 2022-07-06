package com.leon.biuvideo.actions.drawerActions;

import com.leon.biuvideo.base.baseAction.ActionData;
import com.leon.biuvideo.base.baseAction.BaseAction;

/**
 * @Author Leon
 * @Time 2022/06/24
 * @Desc
 */
public class WatchLaterAction extends BaseAction {
    @Override
    public void createAdapter(int pageIndex) {

    }

    @Override
    public ActionData createActionData() {
        return new ActionData("稍后观看");
    }
}
