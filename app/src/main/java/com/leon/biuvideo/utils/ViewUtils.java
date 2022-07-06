package com.leon.biuvideo.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleableRes;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.ui.ViewPagerTouchMonitorListener;
import com.leon.biuvideo.ui.adapters.ViewPager2Adapter;

import java.util.List;

/**
 * @Author Leon
 * @Time 2021/11/03
 * @Desc
 */
public class ViewUtils {
    @StyleableRes
    private static final int[] ATTRS = new int[android.R.attr.selectableItemBackground];

    private static final int SCROLLABLE_THRESHOLD = 5;


    public static void setImage(Context context, String imgUrl, ImageView imageView) {
        Glide.with(context).load(imgUrl).into(imageView);
    }

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
            textView.setTextColor(textView.getContext().getColor(R.color.black));
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
    public static boolean Zoom(MotionEvent event, View affectedView) {
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
        Glide
                .with(context)
                .load(imgUrl)
                .into(imageView);
    }
}
