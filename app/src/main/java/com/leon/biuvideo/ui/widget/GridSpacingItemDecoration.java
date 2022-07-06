package com.leon.biuvideo.ui.widget;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author Leon
 * @Time 2021/10/09
 * @Desc 为RecyclerViewItem添加间距
 */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
    /**
     * item间距
     */
    public static final int SPACING = 25;

    /**
     * 是否包含边缘
     */
    public static final boolean INCLUDE_EDGE = true;

    /**
     * 列数
     */
    private final int spanCount;

    /**
     * 间隔
     */
    private final int spacing;

    /**
     * 是否包含边缘
     */
    private final boolean includeEdge;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    /**
     * 直接设置间距
     */
    public static void setItemDecoration (int spanCount, RecyclerView recyclerView) {
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, SPACING, INCLUDE_EDGE));
    }

    /**
     * 直接设置间距
     */
    public static void setItemDecoration (int spanCount, int spacing, boolean includeEdge, RecyclerView recyclerView) {
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, RecyclerView parent, @NonNull RecyclerView.State state) {

        // item position
        int position = parent.getChildAdapterPosition(view);

        // item column
        int column = position % spanCount;

        if (includeEdge) {
            // spacing - column * ((1f / spanCount) * spacing)
            outRect.left = spacing - column * spacing / spanCount;

            // (column + 1) * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount;

            // top edge
//            if (position < spanCount) {
//                outRect.top = spacing;
//            }
            outRect.top = spacing;

            // item bottom
            outRect.bottom = spacing;
        } else {
            // column * ((1f / spanCount) * spacing)
//            outRect.left = column * spacing / spanCount;

            // spacing - (column + 1) * ((1f /    spanCount) * spacing)
//            outRect.right = spacing - (column + 1) * spacing / spanCount;
            if (position >= spanCount) {

                // item top
                outRect.top = spacing;
            }
        }
    }
}
