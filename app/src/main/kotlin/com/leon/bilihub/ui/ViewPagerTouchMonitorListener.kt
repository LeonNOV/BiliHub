package com.leon.bilihub.ui

import android.view.MotionEvent

/**
 * @Author Leon
 * @Time 2021/10/29
 * @Desc
 */
interface ViewPagerTouchMonitorListener {

    /**
     * 触摸监听,防止在ViewPager中上下滑动时出现切换页面的情况
     *
     * @param event event
     */
    fun onTouchEvent(event: MotionEvent?)
}