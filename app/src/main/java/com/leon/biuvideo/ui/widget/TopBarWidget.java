package com.leon.biuvideo.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.res.ResourcesCompat;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseActivity.ActivityManager;

/**
 * @Author Leon
 * @Time 2021/3/7
 * @Desc 一个带有返回键、标题和更多按钮的自定义TopBar
 */
public class TopBarWidget extends LinearLayoutCompat {
    private final Context context;
    private AttributeSet attrs;

    private String topBarTitle = "";
    private boolean withBottomLine = true;

    private TextView topBarTitleView;
    private LinearLayout linearLayout;

    public TopBarWidget(Context context) {
        super(context);
        this.context = context;

        initView();
    }

    public TopBarWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;

        initView();
    }

    public TopBarWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.attrs = attrs;

        initView();
    }

    private void initView() {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBarWidget);
        this.topBarTitle = typedArray.getString(R.styleable.TopBarWidget_title);
        this.withBottomLine = typedArray.getBoolean(R.styleable.TopBarWidget_withBottomLine, true);
        typedArray.recycle();
        this.setOrientation(LinearLayoutCompat.VERTICAL);

        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setBackgroundResource(R.color.white);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        addView(linearLayout);

        LayoutParams layoutParams = (LayoutParams) linearLayout.getLayoutParams();
        layoutParams.width = LayoutParams.MATCH_PARENT;
        layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.topBar_height);
        linearLayout.setLayoutParams(layoutParams);

        addBackView();
        addTitle();

        if (withBottomLine) {
            addBottomLine();
        }
    }

    /**
     * 添加左侧按键
     */
    private void addBackView() {
        ImageView backView = new ImageView(context);
        backView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.back, null));
        backView.setElevation(getResources().getDimensionPixelSize(R.dimen.elevation));
        backView.setPadding(getResources().getDimensionPixelOffset(R.dimen.topBar_padding), getResources().getDimensionPixelOffset(R.dimen.topBar_padding),
                getResources().getDimensionPixelOffset(R.dimen.topBar_padding), getResources().getDimensionPixelOffset(R.dimen.topBar_padding));
        backView.setOnClickListener(v -> ActivityManager.BackPressed());

        linearLayout.addView(backView);
        LinearLayout.LayoutParams backViewLayoutParams = (LinearLayout.LayoutParams) backView.getLayoutParams();
        backViewLayoutParams.width = getResources().getDimensionPixelOffset(R.dimen.back_WH);
        backViewLayoutParams.height = getResources().getDimensionPixelOffset(R.dimen.back_WH);
        backViewLayoutParams.setMarginStart(getResources().getDimensionPixelOffset(R.dimen.topBar_margin));
        backView.setLayoutParams(backViewLayoutParams);
    }

    /**
     * 添加标题
     */
    private void addTitle() {
        topBarTitleView = new TextView(context);
        topBarTitleView.setText(topBarTitle);
        topBarTitleView.setTypeface(Typeface.DEFAULT_BOLD);
        topBarTitleView.setTextColor(Color.BLACK);
        topBarTitleView.setTextSize(18);
        topBarTitleView.setMaxLines(1);
        topBarTitleView.setEllipsize(TextUtils.TruncateAt.END);

        linearLayout.addView(topBarTitleView);

        LinearLayout.LayoutParams topBarTitleViewParams = (LinearLayout.LayoutParams) topBarTitleView.getLayoutParams();
        topBarTitleViewParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        topBarTitleViewParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        topBarTitleViewParams.setMarginStart(getResources().getDimensionPixelOffset(R.dimen.topBar_margin));
        topBarTitleViewParams.setMarginEnd(getResources().getDimensionPixelOffset(R.dimen.topBar_margin));
        topBarTitleView.setLayoutParams(topBarTitleViewParams);
    }

    private void addBottomLine() {
        View view = new View(context);
        view.setBackgroundResource(R.color.topBarBottomLine);
        addView(view);

        LinearLayoutCompat.LayoutParams layoutParams = (LinearLayoutCompat.LayoutParams) view.getLayoutParams();
        layoutParams.width = LinearLayoutCompat.LayoutParams.MATCH_PARENT;
        layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.topBarBottomLineHeight);
        view.setLayoutParams(layoutParams);
    }

    /**
     * 动态标题
     *
     * @param title 标题
     */
    public TopBarWidget setTopBarTitle(String title) {
        this.topBarTitleView.setText(title);
        return this;
    }
}
