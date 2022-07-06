package com.leon.biuvideo.actions.partitionActions;

import com.leon.biuvideo.base.baseAction.ActionData;
import com.leon.biuvideo.base.baseAction.BaseAction;
import com.leon.biuvideo.parser.PartitionParser;

import java.util.Objects;

/**
 * @Author Leon
 * @Time 2022/06/23
 * @Desc
 */
public class LifeAction extends BaseAction {
    @Override
    public void createAdapter(int pageIndex) {

    }

    @Override
    public ActionData createActionData() {
        return new ActionData("生活", Objects.requireNonNull(PartitionParser.Companion.getPartitionData("life")));
    }
}
