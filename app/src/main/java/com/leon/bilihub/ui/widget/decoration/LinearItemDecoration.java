package com.leon.bilihub.ui.widget.decoration;

import android.graphics.Rect;
import android.util.SparseArray;
import android.view.View;

import androidx.annotation.IntDef;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author Leon
 * @Time 2021/10/09
 * @Desc 为RecyclerViewItem添加间距
 */
public class LinearItemDecoration extends RecyclerView.ItemDecoration {

    public static final int LINEAR_OFFSETS_HORIZONTAL = LinearLayoutManager.HORIZONTAL;
    public static final int LINEAR_OFFSETS_VERTICAL = LinearLayoutManager.VERTICAL;

    private final SparseArray<OffsetsCreator> mTypeOffsetsFactories = new SparseArray<>();

    @IntDef({
            LINEAR_OFFSETS_HORIZONTAL,
            LINEAR_OFFSETS_VERTICAL
    })
    @Retention(RetentionPolicy.SOURCE)
    private @interface Orientation {
    }

    @Orientation
    private int mOrientation;
    private int mItemOffsets;

    private boolean mIsOffsetEdge;
    private boolean mIsOffsetLast;

    public LinearItemDecoration(@Orientation int orientation) {
        this.mOrientation = orientation;
        mIsOffsetLast = true;
        mIsOffsetEdge = true;
    }

    public void setOrientation(@Orientation int orientation) {
        this.mOrientation = orientation;
    }

    public void setItemOffsets(int itemOffsets) {
        this.mItemOffsets = itemOffsets;
    }

    public void setOffsetEdge(boolean isOffsetEdge) {
        this.mIsOffsetEdge = isOffsetEdge;
    }

    public void setOffsetLast(boolean isOffsetLast) {
        this.mIsOffsetLast = isOffsetLast;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int adapterPosition = parent.getChildAdapterPosition(view);

        if (mOrientation == LINEAR_OFFSETS_HORIZONTAL) {
            outRect.right = getDividerOffsets(parent, view);
        } else {
            outRect.bottom = getDividerOffsets(parent, view);
        }

        if (mIsOffsetEdge) {
            if (mOrientation == LINEAR_OFFSETS_HORIZONTAL) {
                outRect.left = adapterPosition == 0 ? outRect.right : 0;
                outRect.top = outRect.right;
                outRect.bottom = outRect.right;
            } else {
                outRect.top = adapterPosition == 0 ? outRect.bottom : 0;
                outRect.left = outRect.bottom;
                outRect.right = outRect.bottom;
            }
        }

        if ((adapterPosition == parent.getAdapter().getItemCount() - 1) && !mIsOffsetLast) {
            if (mOrientation == LINEAR_OFFSETS_HORIZONTAL) {
                outRect.right = 0;
            } else {
                outRect.bottom = 0;
            }
        }
    }

    private int getDividerOffsets(RecyclerView parent, View view) {
        if (mTypeOffsetsFactories.size() == 0) {
            return mItemOffsets;
        }

        final int adapterPosition = parent.getChildAdapterPosition(view);
        final int itemType = parent.getAdapter().getItemViewType(adapterPosition);
        final OffsetsCreator offsetsCreator = mTypeOffsetsFactories.get(itemType);

        if (offsetsCreator != null) {
            return offsetsCreator.create(parent, adapterPosition);
        }

        return 0;
    }

    public void registerTypeOffset(int itemType, OffsetsCreator offsetsCreator) {
        mTypeOffsetsFactories.put(itemType, offsetsCreator);
    }

    public interface OffsetsCreator {
        int create(RecyclerView parent, int adapterPosition);
    }
}
