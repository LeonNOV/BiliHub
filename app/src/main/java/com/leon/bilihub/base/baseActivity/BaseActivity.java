package com.leon.bilihub.base.baseActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.gyf.immersionbar.ImmersionBar;
import com.leon.bilihub.R;
import com.leon.bilihub.base.BindingInterface;
import com.leon.bilihub.ui.ViewPagerTouchMonitorListener;

import java.util.Map;
import java.util.Stack;

/**
 * @Author Leon
 * @Time 2021/10/10
 * @Desc
 */
public abstract class BaseActivity<T extends ViewBinding> extends AppCompatActivity implements BindingInterface<T> {
    public static final String TAG = "WwwW";

    private final int enterIn = R.anim.translate_enter_in;
    private final int enterOut = R.anim.translate_enter_out;

    private final int exitIn = R.anim.translate_exit_in;
    private final int exitOut = R.anim.translate_exit_out;

    protected T binding;
    protected Context context;
    protected View rootView;
    protected Bundle params;

    /**
     * ViewPagerTouchMonitorListener stack
     */
    private static final Stack<ViewPagerTouchMonitorListener> onTouchEventListenerList = new Stack<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get params
        this.params = getIntent().getExtras();

        // 添加Activity至管理容器中
        ActivityManager.addActivity(this);

        // 获取ViewBinding对象
        binding = getViewBinding();
        context = this;
        rootView = binding.getRoot();

        // 设置布局内容
        setContentView(rootView);

        // 处理沉浸式状态栏
        onImmersion();

        // 设置进入动画
        overridePendingTransition(enterIn, exitOut);

        init();
    }

    /**
     * 初始化
     */
    protected abstract void init();

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (onTouchEventListenerList != null) {
            for (ViewPagerTouchMonitorListener viewPagerTouchMonitorListener : onTouchEventListenerList) {
                if (viewPagerTouchMonitorListener != null) {
                    viewPagerTouchMonitorListener.onTouchEvent(event);
                }
            }
        }

        return super.dispatchTouchEvent(event);
    }

    /**
     * 注册Touch事件
     * 即push至stack栈顶
     *
     * @param viewPagerTouchMonitorListener onTouchListener
     */
    public void registerTouchEvenListener(ViewPagerTouchMonitorListener viewPagerTouchMonitorListener) {
        onTouchEventListenerList.push(viewPagerTouchMonitorListener);
    }

    /**
     * 取消注册当前ActivityTouch事件
     * 即pop栈顶对象
     */
    public void unregisterTouchEvenListener() {
        if (!onTouchEventListenerList.isEmpty()) {
            onTouchEventListenerList.pop();
        }
    }

    /**
     * 初始化沉浸式
     */
    protected void onImmersion() {
        ImmersionBar.with(this).statusBarColorInt(context.getColor(R.color.black)).fitsSystemWindows(true).init();
    }

    protected void startActivity(Class<? extends BaseActivity<? extends ViewBinding>> targetClass) {
        ActivityManager.startActivity(context, targetClass);
    }

    protected void startActivity(Class<? extends BaseActivity<? extends ViewBinding>> targetClass, Map<String, String> params) {
        ActivityManager.startActivity(context, targetClass, params);
    }

    protected void startActivity(Class<? extends BaseActivity<? extends ViewBinding>> targetClass, Bundle bundle) {
        ActivityManager.startActivity(context, targetClass, bundle);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backPressed();
    }

    /**
     * 结束顶部Activity
     */
    public void backPressed () {
        ActivityManager.BackPressed();
    }

    @Override
    public void finish() {
        super.finish();

        // 设置退出动画
        overridePendingTransition(exitIn, enterOut);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterTouchEvenListener();
        binding = null;
    }
}
