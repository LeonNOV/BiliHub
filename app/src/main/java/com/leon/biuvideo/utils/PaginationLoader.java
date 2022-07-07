package com.leon.biuvideo.utils;

import android.os.Parcelable;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.databinding.RefreshContentBinding;
import com.leon.biuvideo.ui.widget.GridSpacingItemDecoration;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @Author Leon
 * @Time 2021/11/02
 * @Desc 分页加载器，适用于多页面加载；单一页面使用{@link RecyclerViewLoader}
 *
 * @param <T>   Retrofit响应数据
 * @param <B>   列表展示数据，需通过{@link GuideInterface}来指定
 * //todo
 */
public class PaginationLoader<T extends Parcelable, B extends Parcelable> {
    /**
     * 加载至顶部标记
     */
    private static final int LOAD_TYPE_HEAD = 1;

    /**
     * 加载至尾部标记
     */
    private static final int LOAD_TYPE_TAIL = 2;

    /**
     * 首次插入标记
     */
    private boolean isFirstInsertFlag;

    private Observable<T> observable;
    private RecyclerView recyclerView;
    private View rootView;
    private RefreshContentBinding binding;

    private GuideInterface<T, B> guideInterface;
    private BaseViewBindingAdapter<B, ? extends ViewBinding> baseViewBindingAdapter;

    /**
     * 直接指定RecyclerView，适用于无刷新/加载功能的RecyclerView
     */
    public PaginationLoader(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    /**
     * @param observable    {@link com.leon.biuvideo.http.HttpApi}
     * @param rootView  refresh_content root view
     */
    public PaginationLoader(Observable<T> observable, View rootView) {
        this.observable = observable;
        this.rootView = rootView;

        this.binding = RefreshContentBinding.bind(rootView);
    }

    public void init(BaseViewBindingAdapter<B, ? extends ViewBinding> baseViewBindingAdapter) {
        initContent(baseViewBindingAdapter);

        if (binding != null) {
            initSmart();

            // 获取第一页数据
            insertData(LOAD_TYPE_HEAD, binding.container.container);
        }
    }

    /**
     * 初始化RecyclerView
     */
    private void initContent(BaseViewBindingAdapter<B, ? extends ViewBinding> baseViewBindingAdapter) {
        this.baseViewBindingAdapter = baseViewBindingAdapter;
        this.baseViewBindingAdapter.setHasStableIds(true);

        if (binding != null) {
            this.recyclerView = binding.container.content;
        }

        this.recyclerView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        this.recyclerView.setMotionEventSplittingEnabled(false);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(baseViewBindingAdapter);

        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(baseViewBindingAdapter.context, R.anim.recycler_view_fall_down);
        this.recyclerView.setLayoutAnimation(animationController);

        int spanCount;
        RecyclerView.LayoutManager layoutManager = this.recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            spanCount = 1;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        } else {
            spanCount = 1;
        }

        this.recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, GridSpacingItemDecoration.SPACING, GridSpacingItemDecoration.INCLUDE_EDGE));
    }

    public void setObservable(Observable<T> observable) {
        this.observable = observable;
    }

    /**
     * 单独指定RecyclerView后可调用
     */
    public void obtain() {
        insertData(LOAD_TYPE_HEAD, null);
    }

    /**
     * 初始化 customViewRefreshViewSmart
     */
    private void initSmart() {
        binding.container.container.setOnRefreshListener((refreshLayout) -> insertData(LOAD_TYPE_HEAD, refreshLayout));
        binding.container.container.setOnLoadMoreListener((refreshLayout) -> insertData(LOAD_TYPE_TAIL, refreshLayout));
    }

    /**
     *  设置背景
     *
     * @param color color
     */
    public void setBackground (@ColorRes int color) {
        binding.container.container.setBackgroundResource(color);
    }

    /**
     * 设置管理器
     *
     * @param layoutManager layoutManager
     */
    public void setLayoutManager (RecyclerView.LayoutManager layoutManager) {
        this.recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * 获取数据并显示
     *
     * @param loadType {LOAD_TYPE_HEAD/LOAD_TYPE_TAIL}
     */
    private void insertData(int loadType, RefreshLayout refreshLayout) {
        observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull T t) {
                        if (guideInterface != null) {
                            List<B> bs = guideInterface.guide(t);

                            if (bs == null || bs.isEmpty()) {
                                onError(new RuntimeException("啥都没有"));
                            } else {
                                if (loadType == LOAD_TYPE_TAIL) {
                                    baseViewBindingAdapter.appendTail(bs);
                                } else if (loadType == LOAD_TYPE_HEAD) {
                                    baseViewBindingAdapter.appendHead(bs);
                                } else {
                                    onError(new RuntimeException());
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        // TODO 待替换为SnackBar
                        Toast.makeText(rootView.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        insertFinish(loadType, refreshLayout, false);
                    }

                    @Override
                    public void onComplete() {
                        insertFinish(loadType, refreshLayout, true);
                    }
                });
    }

    /**
     * 获取并插入数据结束后
     *
     * @param loadType  {@value LOAD_TYPE_HEAD} {@value LOAD_TYPE_TAIL}
     * @param refreshLayout refreshLayout
     * @param isComplete 是否已成功插入至 customViewRefreshViewContent
     */
    private void insertFinish(int loadType, RefreshLayout refreshLayout, boolean isComplete) {
        if (refreshLayout != null) {
            if (loadType == LOAD_TYPE_TAIL) {
                refreshLayout.finishLoadMore();
                binding.container.container.finishLoadMore();
            } else if (loadType == LOAD_TYPE_HEAD) {
                refreshLayout.finishRefresh();
                binding.container.container.finishRefresh();

                // 滑动至顶部
//            this.recyclerView.smoothScrollToPosition(0);
            }

            // first insert
            if (!isFirstInsertFlag) {
                binding.loading.container.setVisibility(View.GONE);
                isFirstInsertFlag = true;
            }

            // error
            if (!isComplete) {
                if (baseViewBindingAdapter.getItemCount() == 0) {
                    binding.empty.container.setVisibility(View.VISIBLE);
                }

                closeSmart();
            }
        }
    }

    /**
     * 关闭刷新控件
     */
    public void closeSmart() {
        binding.container.container.setEnableRefresh(false);
        binding.container.container.setEnableLoadMore(false);
    }

    public void setGuide(GuideInterface<T, B> guideInterface) {
        this.guideInterface = guideInterface;
    }

    public interface GuideInterface<T, B> {
        /**
         * 数据路径
         *
         * @param t t
         * @return List<B>
         */
        List<B> guide(T t);
    }
}
