package com.leon.bilihub.base.baseActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;
import java.util.Stack;

/**
 * @Author Leon
 * @Time 2021/08/18
 * @Desc activities manager
 */
public class ActivityManager {
    /**
     * Activity Stack
     */
    private final static Stack<AppCompatActivity> ACTIVITY_STACK = new Stack<>();

    /**
     * 打开一个Activity
     */
    public static void startActivity (Context context, Class<? extends AppCompatActivity> target) {
        startActivity(createIntent(context, target), context);
    }

    public static void startActivity (Context context, Class<? extends AppCompatActivity> target,Map<String, String> params) {
        Intent intent = createIntent(context, target);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            intent.putExtra(entry.getKey(), entry.getValue());
        }

        startActivity(intent, context);
    }

    public static void startActivity (Context context, Class<? extends AppCompatActivity> target, Bundle bundle) {
        Intent intent = createIntent(context, target);
        intent.putExtras(bundle);

        startActivity(intent, context);
    }

    /**
     * 开启一个新的Activity并关闭当前Activity
     */
    public static void startWithFinishActivity(Context context, Class<? extends AppCompatActivity> target) {
        finishTopActivity();
        startActivity(context, target);
    }

    public static void startWithFinishActivity(Context context, Class<? extends AppCompatActivity> target, Bundle bundle) {
        finishTopActivity();
        startActivity(context, target, bundle);
    }

    public static void startWithFinishActivity(Context context, Class<? extends AppCompatActivity> target, Map<String, String> params) {
        finishTopActivity();
        startActivity(context, target, params);
    }

    /**
     * 弹出指定的第一次出现的activity
     */
    public static void popTo (Class<? extends AppCompatActivity> target) {
        String targetTypeName = target.getSimpleName();

        for (AppCompatActivity appCompatActivity : ACTIVITY_STACK) {
            String typeName = appCompatActivity.getClass().getSimpleName();
            if (typeName.equals(targetTypeName)) {
                appCompatActivity.finish();
                ACTIVITY_STACK.remove(appCompatActivity);
                return;
            }
        }
    }

    /**
     * 创建Intent
     *
     * @param context   context
     * @param target    target
     * @return  Intent
     */
    private static Intent createIntent (Context context, Class<? extends AppCompatActivity> target) {
        Intent intent = new Intent(context, target);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;
    }

    /**
     * 启动Activity
     *
     * @param intent    intent
     * @param context   context
     */
    private static void startActivity (Intent intent, Context context) {
        context.startActivity(intent);
    }

    /**
     * 将Activity加入到管理容器中
     *
     * @param activity  activity
     */
    public static void addActivity (AppCompatActivity activity) {
        ACTIVITY_STACK.add(activity);
    }

    /**
     * 关闭栈顶Activity
     */
    private static void finishTopActivity () {
        ACTIVITY_STACK.pop().finish();
    }

    /**
     * back pressed
     */
    public static void BackPressed () {
        finishTopActivity();
    }
}
