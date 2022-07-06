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
public class MovieAction extends BaseAction {
    @Override
    public void createAdapter(int pageIndex) {

    }

    @Override
    public ActionData createActionData() {
        return new ActionData("电影", Objects.requireNonNull(PartitionParser.Companion.getPartitionData("movie")));
    }
}
