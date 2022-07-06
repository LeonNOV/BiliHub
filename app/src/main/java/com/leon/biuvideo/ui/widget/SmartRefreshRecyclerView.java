package com.leon.biuvideo.ui.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import java.util.List;

/**
 * @Author Leon
 * @Time 2021/3/9
 * @Desc 带有上拉加载功能的LoadingRecyclerView
 */
public class SmartRefreshRecyclerView<T> extends SmartRefreshLayout {
    public static final int NO_DATA = 0;
    public static final int LOADING_FINISHING = 1;

    private final Context context;
    public LoadingRecyclerView loadingRecyclerView;
//    private BaseAdapter<T> adapter;

    public SmartRefreshRecyclerView(@NonNull Context context) {
        super(context);
        this.context = context;

        initView();
    }

    public SmartRefreshRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        initView();
    }

    public SmartRefreshRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        // 关闭顶部刷新控件(下拉刷新控件)
        setEnableRefresh(false);

        // 显示下拉高度/手指真实下拉高度=阻尼效果
//        setDragRate(0.3f);

        // 设置不自动刷新，释放之后再刷新
        setEnableAutoLoadMore(false);
        
        addLoadingRecyclerView();
        addClassicsFooter();

        // 默认关闭加载功能，在第一次加载数据后开启(需要调用setLoadingRecyclerViewStatus)
        setEnableLoadMore(false);
    }

    /**
     * 添加底部刷新控件
     */
    private void addClassicsFooter() {
        ClassicsFooter classicsFooter = new ClassicsFooter(context);
        ClassicsFooter.REFRESH_FOOTER_RELEASE = "释放加载更多";
        addView(classicsFooter);

        LayoutParams layoutParams = (LayoutParams) classicsFooter.getLayoutParams();
        layoutParams.width = LayoutParams.MATCH_PARENT;
        layoutParams.height = LayoutParams.WRAP_CONTENT;
        classicsFooter.setLayoutParams(layoutParams);
    }

    /**
     * 添加LoadingRecyclerView
     */
    private void addLoadingRecyclerView() {
        loadingRecyclerView = new LoadingRecyclerView(context);
        addView(loadingRecyclerView);

        LayoutParams layoutParams = (LayoutParams) loadingRecyclerView.getLayoutParams();
        layoutParams.width = LayoutParams.MATCH_PARENT;
        layoutParams.height = LayoutParams.MATCH_PARENT;

        loadingRecyclerView.setLayoutParams(layoutParams);
    }

    public void setProgressBarVisibility(@LoadingRecyclerView.Visibility int visibility) {
        this.loadingRecyclerView.progressBar.setVisibility(visibility);
    }

    public void setImageViewVisibility(@LoadingRecyclerView.Visibility int visibility) {
        this.loadingRecyclerView.imageView.setVisibility(visibility);
    }

    public void setRecyclerViewVisibility(@LoadingRecyclerView.Visibility int visibility) {
        this.loadingRecyclerView.recyclerView.setVisibility(visibility);
    }

//    public void setRecyclerViewAdapter(BaseAdapter<T> adapter) {
//        this.adapter = adapter;
//        this.loadingRecyclerView.recyclerView.setAdapter(adapter);
//    }

    public void setRecyclerViewLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.loadingRecyclerView.recyclerView.setLayoutManager(layoutManager);
    }

    public void setLoadingRecyclerViewStatus(@LoadingRecyclerView.SRRStatus int status) {
        if (status == LoadingRecyclerView.LOADING_FINISH && !isEnableLoadMore()) {
            setEnableLoadMore(true);
        }
        loadingRecyclerView.setLoadingRecyclerViewStatus(status);
    }

    /**
     * 追加数据
     *
     * @param appends  追加的数据
     */
//    public void append(List<T> appends) {
//        if (adapter != null) {
//            adapter.append(appends);
//        }
//    }

    public void setSmartRefreshStatus(int status) {
        if (NO_DATA == status) {
            finishLoadMore();
            setEnableLoadMore(false);
        } else if (LOADING_FINISHING == status) {
            finishLoadMore(true);
        }
    }
}
