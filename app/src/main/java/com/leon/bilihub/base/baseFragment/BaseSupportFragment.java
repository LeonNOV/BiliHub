package com.leon.bilihub.base.baseFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.leon.bilihub.base.BindingInterface;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @Author Leon
 * @Time 2021/3/1
 * @Desc 基本的SupportFragment
 */
public abstract class BaseSupportFragment<T extends ViewBinding> extends SupportFragment implements BindingInterface<T> {
    protected Context context;
    protected View rootView;
    protected T binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding = getViewBinding();
        rootView = binding.getRoot();

        initView();
    }

    /**
     * 初始化控件
     */
    protected abstract void initView();

    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart(this.getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
    }

    /**
     * 返回方法，用于fragment的返回
     */
    protected void backPressed() {
        _mActivity.onBackPressed();
        onDestroy();
    }

    @Override
    public boolean onBackPressedSupport() {
        onDestroy();
        return super.onBackPressedSupport();
    }
}