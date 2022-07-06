package com.leon.biuvideo.base.baseAction;

import com.leon.biuvideo.beans.Partition;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/06/18
 * @Desc
 */
public class ActionData {
    private final String title;
    private final String[] tabTitles;
    private List<Partition> partitionList;
    private final int subPageCount;
    private final boolean isSingle;

    public ActionData(String title) {
        this.title = title;
        this.tabTitles = null;
        this.subPageCount = 0;
        this.isSingle = true;
    }

    public ActionData(String title, List<Partition> partitionList) {
        this.title = title;
        this.partitionList = partitionList;
        this.isSingle = false;
        this.subPageCount = partitionList.size();
        this.tabTitles = new String[partitionList.size()];
        for (int i = 0; i < partitionList.size(); i++) {
            this.tabTitles[i] = partitionList.get(i).getTitle();
        }
    }

    public ActionData(String title, String[] tabTitles) {
        this.title = title;
        this.tabTitles = tabTitles;

        if (tabTitles == null) {
            this.subPageCount = 0;
            this.isSingle = true;
        } else {
            this.subPageCount = tabTitles.length;
            this.isSingle = false;
        }
    }

    public String getTitle() {
        return title;
    }

    public String[] getTabTitles() {
        return tabTitles;
    }

    public int getSubPageCount() {
        return subPageCount;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public List<Partition> getPartitionList() {
        return partitionList;
    }
}
