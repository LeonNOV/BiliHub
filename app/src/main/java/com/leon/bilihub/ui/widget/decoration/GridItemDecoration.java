package com.leon.bilihub.ui.widget.decoration;

import android.graphics.Rect;
import android.util.SparseArray;
import android.view.View;

import androidx.annotation.IntDef;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author Leon
 * @Time 2022/07/31
 * @Desc
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {
    public static final int GRID_OFFSETS_HORIZONTAL = GridLayoutManager.HORIZONTAL;
    public static final int GRID_OFFSETS_VERTICAL = GridLayoutManager.VERTICAL;

    private final SparseArray<OffsetsCreator> mTypeOffsetsFactories = new SparseArray<>();

    @IntDef({
            GRID_OFFSETS_HORIZONTAL,
            GRID_OFFSETS_VERTICAL
    })
    @Retention(RetentionPolicy.SOURCE)
    private @interface Orientation {
    }

    @Orientation
    private int mOrientation;
    private int mVerticalItemOffsets;
    private int mHorizontalItemOffsets;

    private boolean mIsOffsetEdge;
    private boolean mIsOffsetLast;

    public GridItemDecoration(@Orientation int orientation) {
        setOrientation(orientation);
        mIsOffsetLast = true;
        mIsOffsetEdge = true;
    }

    public void setOrientation(int orientation) {
        this.mOrientation = orientation;
    }

    public void setVerticalItemOffsets(int verticalItemOffsets) {
        this.mVerticalItemOffsets = verticalItemOffsets;
    }

    public void setHorizontalItemOffsets(int horizontalItemOffsets) {
        this.mHorizontalItemOffsets = horizontalItemOffsets;
    }

    public void setOffsetEdge(boolean isOffsetEdge) {
        this.mIsOffsetEdge = isOffsetEdge;
    }

    public void setOffsetLast(boolean isOffsetLast) {
        this.mIsOffsetLast = isOffsetLast;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        int adapterPosition = parent.getChildAdapterPosition(view);

        int horizontalOffsets = getHorizontalOffsets(parent, view);
        int verticalOffsets = getVerticalOffsets(parent, view);

        boolean isFirstRow = isFirstRow(adapterPosition, spanCount, childCount);
        boolean isLastRow = isLastRow(adapterPosition, spanCount, childCount);
        boolean isFirstColumn = isFirstColumn(adapterPosition, spanCount, childCount);
        boolean isLastColumn = isLastColumn(adapterPosition, spanCount, childCount);

        outRect.set(0, 0, horizontalOffsets, verticalOffsets);
        outRect.bottom = mOrientation != GRID_OFFSETS_VERTICAL && isLastRow ? 0 : verticalOffsets;
        outRect.right = mOrientation != GRID_OFFSETS_HORIZONTAL && isLastColumn ? 0 : horizontalOffsets;

        if (mIsOffsetEdge) {
            outRect.top = isFirstRow ? verticalOffsets : 0;
            outRect.bottom = verticalOffsets;
//            outRect.left = isFirstColumn ? horizontalOffsets : 0;
//            outRect.right = horizontalOffsets;

            if (isFirstColumn) {
                outRect.left = horizontalOffsets;
                outRect.right = horizontalOffsets / 2;
            } else if (isLastColumn) {
                outRect.left = horizontalOffsets / 2;
                outRect.right = horizontalOffsets;
            }
        }

        if (!mIsOffsetLast) {
            if (mOrientation == GRID_OFFSETS_VERTICAL && isLastRow) {
                outRect.bottom = 0;
            } else if (mOrientation == GRID_OFFSETS_HORIZONTAL && isLastColumn) {
                outRect.right = 0;
            }
        }
    }

    private int getHorizontalOffsets(RecyclerView parent, View view) {
        if (mTypeOffsetsFactories.size() == 0) {
            return mHorizontalItemOffsets;
        }

        final int adapterPosition = parent.getChildAdapterPosition(view);
        final int itemType = parent.getAdapter().getItemViewType(adapterPosition);
        final OffsetsCreator offsetsCreator = mTypeOffsetsFactories.get(itemType);

        if (offsetsCreator != null) {
            return offsetsCreator.createHorizontal(parent, adapterPosition);
        }

        return 0;
    }

    private int getVerticalOffsets(RecyclerView parent, View view) {
        if (mTypeOffsetsFactories.size() == 0) {
            return mVerticalItemOffsets;
        }

        final int adapterPosition = parent.getChildAdapterPosition(view);
        final int itemType = parent.getAdapter().getItemViewType(adapterPosition);
        final OffsetsCreator offsetsCreator = mTypeOffsetsFactories.get(itemType);

        if (offsetsCreator != null) {
            return offsetsCreator.createVertical(parent, adapterPosition);
        }

        return 0;
    }

    private boolean isFirstColumn(int position, int spanCount, int childCount) {
        if (mOrientation == GRID_OFFSETS_VERTICAL) {
            return position % spanCount == 0;
        } else {
            int lastColumnCount = childCount % spanCount;
            lastColumnCount = lastColumnCount == 0 ? spanCount : lastColumnCount;
            return position < childCount - lastColumnCount;
        }
    }

    private boolean isLastColumn(int position, int spanCount, int childCount) {
        if (mOrientation == GRID_OFFSETS_VERTICAL) {
            return (position + 1) % spanCount == 0;
        } else {
            int lastColumnCount = childCount % spanCount;
            lastColumnCount = lastColumnCount == 0 ? spanCount : lastColumnCount;
            return position >= childCount - lastColumnCount;
        }
    }

    private boolean isFirstRow(int position, int spanCount, int childCount) {
        if (mOrientation == GRID_OFFSETS_VERTICAL) {
            return position < spanCount;
        } else {
            return position % spanCount == 0;
        }
    }

    private boolean isLastRow(int position, int spanCount, int childCount) {
        if (mOrientation == GRID_OFFSETS_VERTICAL) {
            int lastColumnCount = childCount % spanCount;
            lastColumnCount = lastColumnCount == 0 ? spanCount : lastColumnCount;
            return position >= childCount - lastColumnCount;
        } else {
            return (position + 1) % spanCount == 0;
        }
    }

    private int getSpanCount(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        } else {
            throw new UnsupportedOperationException("the GridDividerItemDecoration can only be used in " +
                    "the RecyclerView which use a GridLayoutManager or StaggeredGridLayoutManager");
        }
    }

    public void registerTypeDrawable(int itemType, OffsetsCreator offsetsCreator) {
        mTypeOffsetsFactories.put(itemType, offsetsCreator);
    }

    public interface OffsetsCreator {
        int createVertical(RecyclerView parent, int adapterPosition);

        int createHorizontal(RecyclerView parent, int adapterPosition);
    }
}
