package com.leon.bilihub.base.baseFragment;

import androidx.viewbinding.ViewBinding;

/**
 * @Author Leon
 * @Time 2021/3/26
 * @Desc 懒加载Fragment，需要配合ViewPager2使用
 */
public abstract class BaseLazyFragment<T extends ViewBinding> extends BaseFragment<T> {
    protected static final String TAG = "WwwW";

    /**
     * 数据读取标记
     */
    protected boolean isLoaded = false;

    @Override
    public void onResume() {
        super.onResume();
        if (!isLoaded) {
            onLazyLoad();
            isLoaded = true;
        }
    }

    /**
     * 加载数据
     */
    protected abstract void onLazyLoad ();
}