package com.leon.biuvideo.base.baseActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.gyf.immersionbar.ImmersionBar;
import com.leon.biuvideo.R;
import com.leon.biuvideo.base.BindingInterface;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * @Author Leon
 * @Time 2021/10/10
 * @Desc
 */
public abstract class BaseSupportActivity<T extends ViewBinding> extends SupportActivity implements BindingInterface<T> {
    public static final String TAG = "BaseActivity";

    protected T binding;
    protected Context context;
    protected View rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 添加Activity至管理容器中
        ActivityManager.addActivity(this);

        // 获取ViewBinding对象
        binding = getViewBinding();
        context = getApplicationContext();
        rootView = binding.getRoot();

        // 设置布局内容
        setContentView(rootView);

        // 处理沉浸式状态栏
        onImmersion();

        // 设置进入动画
        overridePendingTransition(R.anim.translate_enter_in, R.anim.translate_exit_out);

        init();
    }

    /**
     * 初始化
     */
    protected abstract void init();

    /**
     * 初始化沉浸式
     */
    protected void onImmersion() {
        ImmersionBar.with(this).init();
    }

    @Override
    public void finish() {
        super.finish();

        // 设置退出动画
        overridePendingTransition(R.anim.translate_exit_in, R.anim.translate_enter_out);
    }
}
