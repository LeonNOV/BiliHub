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
public class RankAction extends BaseAction {
    @Override
    public void createAdapter(int pageIndex) {

    }

    @Override
    public ActionData createActionData() {
        return new ActionData("全站排行", new String[]{"全站", "番剧", "国产动画", "国创相关", "纪录片",
                "动画", "音乐", "舞蹈", "游戏", "知识",
                "科技", "运动", "汽车", "生活", "美食",
                "动物圈", "鬼畜", "时尚", "娱乐", "影视",
                "电影", "电视剧", "综艺", "原创", "新人"});
    }
}
