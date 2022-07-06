package com.leon.biuvideo.base.baseAction;

/**
 * @Author Leon
 * @Time 2022/06/18
 * @Desc
 */
public abstract class BaseAction implements ActionInterface {
    protected static final String TAG = "BaseAction";

    public static final String ACTION = "action";


    /**
     * create a bundle
     *
     * @return Bundle
     */
    public abstract ActionData createActionData();
}
