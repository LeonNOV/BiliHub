package com.leon.bilihub.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.leon.bilihub.R;
import com.leon.bilihub.base.baseActivity.BaseActivity;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.ui.ViewPagerTouchMonitorListener;
import com.leon.bilihub.ui.adapters.ViewPager2Adapter;
import com.leon.bilihub.ui.widget.decoration.GridItemDecoration;
import com.leon.bilihub.ui.widget.decoration.LinearItemDecoration;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.BaseItemAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;

/**
 * @Author Leon
 * @Time 2021/11/03
 * @Desc
 */
public class ViewUtils {
    @StyleableRes
    private static final int[] ATTRS = new int[android.R.attr.selectableItemBackground];

    private static final int SCROLLABLE_THRESHOLD = 5;
    private static final int ITEM_ANIMATOR_DURATION = 100;

    public static void setRipple(View view) {
        TypedArray typedArray = view.getContext().obtainStyledAttributes(ATTRS);
        Drawable defaultFocusHighlightCache = typedArray.getDrawable(0);
        typedArray.recycle();

        view.setForeground(defaultFocusHighlightCache);
    }

    /**
     * 初始化TabLayout和ViewPager2
     *
     * @param activity   {@link BaseActivity}
     * @param tabLayout  {@link TabLayout}
     * @param viewPager2 {@link ViewPager2}
     * @param fragments  fragments
     */
    public static void initTabLayout(BaseActivity<? extends ViewBinding> activity, TabLayout tabLayout, ViewPager2 viewPager2,
                                     @NonNull List<Fragment> fragments, @NonNull String... titles) {
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager2.setCurrentItem(0);
        viewPager2.setAdapter(new ViewPager2Adapter(activity, fragments));

        if (titles.length > SCROLLABLE_THRESHOLD) {
            tabLayout.setTabMode(TabLayout.MODE_AUTO);
        }

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
//            if (position == 0) {
//                ViewUtils.changeTabTitle(tab, true);
//            }

            tab.setText(titles[position]);
        }).attach();

        setViewPagerSensitivity(activity, viewPager2);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                ViewUtils.changeTabTitle(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                ViewUtils.changeTabTitle(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    /**
     * 改变Tab的样式
     *
     * @param tab        Tab
     * @param isSelected 是否已选中
     */
    private static void changeTabTitle(TabLayout.Tab tab, boolean isSelected) {
        if (isSelected) {
            View view = tab.getCustomView();
            if (view == null) {
                tab.setCustomView(R.layout.tab_layout_title);
            }

            TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setTextColor(textView.getContext().getColor(R.color.primaryContrary));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        } else {
            View view = tab.getCustomView();
            if (view == null) {
                tab.setCustomView(R.layout.tab_layout_title);
            }

            TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
            textView.setTypeface(Typeface.DEFAULT);
            textView.setTextColor(textView.getContext().getColor(R.color.tabTextColor));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        }
    }

    /**
     * 设置ViewPager2灵敏度
     *
     * @param viewPager2 viewPager2
     */
    private static void setViewPagerSensitivity(BaseActivity<? extends ViewBinding> baseActivity, ViewPager2 viewPager2) {
        ViewPagerTouchMonitorListener monitorListener = new ViewPagerTouchMonitorListener() {
            private int startX = 0;
            private int startY = 0;

            @Override
            public void onTouchEvent(@Nullable MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int endX = (int) event.getX();
                        int endY = (int) event.getY();

                        int disX = Math.abs(endX - startX);
                        int disY = Math.abs(endY - startY);
                        if (disX < disY) {
                            // 如果水平滑动的距离小于垂直的滑动距离，就关闭左右滑动操作
                            viewPager2.setUserInputEnabled(false);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        startX = 0;
                        startY = 0;
                        viewPager2.setUserInputEnabled(true);
                        break;
                    default:
                        break;
                }
            }
        };

        baseActivity.registerTouchEvenListener(monitorListener);
    }

    /**
     * 缩放效果
     *
     * @param event MotionEvent
     * @return 是否被消费
     */
    public static boolean zoom(MotionEvent event, View affectedView) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            affectedView.setScaleX(0.9F);
            affectedView.setScaleY(0.9F);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            affectedView.setScaleX(1F);
            affectedView.setScaleY(1F);
        }
        return false;
    }

    public static void setImg(Context context, ImageView imageView, String imgUrl) {
        RequestCreator creator = Picasso
                .get()
                .load(imgUrl);

        if (!PreferenceUtils.getImgMode(context)) {
            creator = creator.config(Bitmap.Config.RGB_565);
        }

        creator.into(imageView);
    }

    /**
     * recyclerView initializer
     *
     * @param recyclerView {@link RecyclerView}
     * @param adapter      {@link ViewBindingAdapter}
     */
    public static void listInitializer(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        if (recyclerView.getAdapter() == null) {
            recyclerViewInitializer(recyclerView, adapter);
        }
    }

    /**
     * connecting adapter
     * <br/>
     * Item之间无间隔
     *
     * @param recyclerView {@link RecyclerView}
     * @param adapter      {@link ViewBindingAdapter}
     */
    public static void linkAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(new AlphaInAnimationAdapter(adapter));
            recyclerView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
            recyclerView.setMotionEventSplittingEnabled(false);
            recyclerView.setHasFixedSize(true);
        }
    }

    /**
     * Item之间有间隔
     *
     * @param recyclerView recyclerView
     * @param adapter      adapter
     */
    private static void recyclerViewInitializer(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        linkAdapter(recyclerView, adapter);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridItemDecoration itemDecoration = new GridItemDecoration(GridItemDecoration.GRID_OFFSETS_VERTICAL);
            itemDecoration.setVerticalItemOffsets(recyclerView.getContext().getResources().getDimensionPixelSize(R.dimen.GridItemVerticalOffset));
            itemDecoration.setHorizontalItemOffsets(recyclerView.getContext().getResources().getDimensionPixelSize(R.dimen.GridItemHorizontalOffset));
            recyclerView.addItemDecoration(itemDecoration);
        } else if (layoutManager instanceof LinearLayoutManager) {
            LinearItemDecoration itemDecoration;
            if (((LinearLayoutManager) layoutManager).getOrientation() == LinearLayoutManager.HORIZONTAL) {
                itemDecoration = new LinearItemDecoration(LinearItemDecoration.LINEAR_OFFSETS_HORIZONTAL);
            } else {
                itemDecoration = new LinearItemDecoration(LinearItemDecoration.LINEAR_OFFSETS_VERTICAL);
            }
            itemDecoration.setItemOffsets(recyclerView.getContext().getResources().getDimensionPixelSize(R.dimen.LinearItemOffset));
            recyclerView.addItemDecoration(itemDecoration);
        } else {
            throw new RuntimeException("fuck~~~");
        }

        if (PreferenceUtils.getListAnimStatus(recyclerView.getContext())) {
            BaseItemAnimator itemAnimator = new ScaleInAnimator(new OvershootInterpolator());
            itemAnimator.setAddDuration(ITEM_ANIMATOR_DURATION);
            itemAnimator.setRemoveDuration(ITEM_ANIMATOR_DURATION);
            itemAnimator.setChangeDuration(ITEM_ANIMATOR_DURATION);
            itemAnimator.setMoveDuration(ITEM_ANIMATOR_DURATION);

            recyclerView.setItemAnimator(itemAnimator);
        }
    }

    public static AppCompatActivity scanForActivity(Context context) {
        if (context == null) {
            return null;
        }

        if (context instanceof Activity) {
            return (AppCompatActivity) context;
        } else if (context instanceof ContextWrapper) {
            return scanForActivity(((ContextWrapper) context).getBaseContext());
        }

        return null;
    }
}
