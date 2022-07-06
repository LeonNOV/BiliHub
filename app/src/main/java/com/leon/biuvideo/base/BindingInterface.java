package com.leon.biuvideo.base;

import androidx.viewbinding.ViewBinding;

/**
 * @Author Leon
 * @Time 2021/10/10
 * @Desc
 */
public interface BindingInterface<T extends ViewBinding> {
    /**
     * 获取ViewBinding
     *
     * @return  T extends ViewBinding
     */
    T getViewBinding();
}
