package com.leon.biuvideo.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.leon.biuvideo.R;

/**
 * @Author Leon
 * @Time 2021/3/1
 * @Desc 卡片式标题控件
 */
public class CardTitle extends LinearLayout {
    private String title = "null";
    private String action = "null";

    private final Context context;
    private AttributeSet attrs;

    private RelativeLayout relativeLayout;
    private TextView titleView;

    public CardTitle(Context context) {
        super(context);
        this.context = context;

        initView();
    }

    public CardTitle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;

        initView();
    }

    public CardTitle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.attrs = attrs;

        initView();
    }

    private OnClickActionListener onClickActionListener;

    public interface OnClickActionListener {
        void onClickAction();
    }

    public void setOnClickActionListener(OnClickActionListener onClickActionListener) {
        this.onClickActionListener = onClickActionListener;
    }

    private void initView() {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CardTitle);
        this.title = typedArray.getString(R.styleable.CardTitle_card_title);
        this.action = typedArray.getString(R.styleable.CardTitle_card_action);
        typedArray.recycle();

        addParent();
        addFirstTitle();
        addSecondTitle();
    }

    private void addParent() {
        relativeLayout = new RelativeLayout(context);

        addView(relativeLayout);

        LayoutParams relativeLayoutParams = (LayoutParams) relativeLayout.getLayoutParams();
        relativeLayoutParams.width = LayoutParams.MATCH_PARENT;
        relativeLayoutParams.height = LayoutParams.WRAP_CONTENT;
        relativeLayoutParams.setMargins(0, 0, 0, getResources().getDimensionPixelOffset(R.dimen.card_title_layout_margin_bottom));
        relativeLayout.setLayoutParams(relativeLayoutParams);
    }

    private void addFirstTitle() {
        if (title == null) {
            this.title = "";
        }

        titleView = new TextView(context);
        titleView.setText(title);
        titleView.setTextColor(Color.BLACK);
        titleView.setTypeface(Typeface.DEFAULT_BOLD);
        titleView.setPadding(5, 5, 5, 5);
        titleView.setTextSize(15);

        relativeLayout.addView(titleView);

        RelativeLayout.LayoutParams firstTitleViewLayoutParams = (RelativeLayout.LayoutParams) titleView.getLayoutParams();
        firstTitleViewLayoutParams.width = LayoutParams.WRAP_CONTENT;
        firstTitleViewLayoutParams.height = LayoutParams.WRAP_CONTENT;
        firstTitleViewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        titleView.setLayoutParams(firstTitleViewLayoutParams);
    }

    private void addSecondTitle() {
        // 如果第二个标题没有设置标题，则默认不添加
        if (action == null) {
            return;
        }

        TextView actionView = new TextView(context);
        actionView.setId(R.id.card_title_action);
        actionView.setText(action);
        actionView.setTextColor(Color.BLACK);
        actionView.setTextSize(12);
        actionView.setBackgroundResource(R.drawable.ripple_round_corners6dp_bg);
        actionView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (InternetUtils.checkNetwork(v)) {
                    if (onClickActionListener != null) {
                        onClickActionListener.onClickAction();
                    }
//                }
            }
        });

        relativeLayout.addView(actionView);

        RelativeLayout.LayoutParams secondTitleViewLayoutParams = (RelativeLayout.LayoutParams) actionView.getLayoutParams();
        secondTitleViewLayoutParams.width = LayoutParams.WRAP_CONTENT;
        secondTitleViewLayoutParams.height = LayoutParams.WRAP_CONTENT;
        secondTitleViewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        actionView.setLayoutParams(secondTitleViewLayoutParams);
    }

    public void setTitle (String title) {
        this.titleView.setText(title);
    }
}
