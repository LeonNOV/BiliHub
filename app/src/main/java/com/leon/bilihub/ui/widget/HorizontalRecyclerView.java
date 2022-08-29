package com.leon.bilihub.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author Leon
 * @Time 2022/07/14
 * @Desc
 */
public class HorizontalRecyclerView extends RecyclerView {
    public HorizontalRecyclerView(@NonNull Context context) {
        super(context);
    }

    public HorizontalRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private int startX, startY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getX();
                startY = (int) ev.getY();

                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int endX = (int) ev.getX();
                int endY = (int) ev.getY();
                int disX = Math.abs(endX - startX);
                int disY = Math.abs(endY - startY);

                LinearLayoutManager manager = (LinearLayoutManager) getLayoutManager();
                int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
                int firstItemPosition = manager.findFirstCompletelyVisibleItemPosition();
                int itemCount = manager.getItemCount();

                if (disX > disY) {
                    if (lastItemPosition == (itemCount - 1) && startX > endX) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(firstItemPosition != 0 || startX >= endX);
                    }
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(ev);
    }
}
