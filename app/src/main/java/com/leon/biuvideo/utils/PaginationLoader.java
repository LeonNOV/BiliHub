package com.leon.biuvideo.utils;

import android.os.Parcelable;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import androidx.annotation.IntDef;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.databinding.RefreshContentBinding;
import com.leon.biuvideo.ui.widget.GridSpacingItemDecoration;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @param <T> Retrofit响应数据
 * @param <B> 列表展示数据，需通过{@link GuideInterface}来指定
 *
 * @Author Leon
 * @Time 2021/11/02
 * @Desc 分页加载器，适用于多页面加载；单一页面使用{@link RecyclerViewLoader}
 */
public class PaginationLoader<T extends Parcelable, B extends Parcelable> {
    /**
     * 首次插入标记
     */
    private boolean isFirstInsertFlag = false;

    private final RefreshContentBinding binding;
    private final BaseViewBindingAdapter<B, ? extends ViewBinding> adapter;

    private Observable<T> observable;
    private UpdateInterface updateInterface;
    private GuideInterface<T, B> guideInterface;

    public PaginationLoader(@NonNull RefreshContentBinding binding, BaseViewBindingAdapter<B, ? extends ViewBinding> adapter) {
        this.binding = binding;
        this.adapter = adapter;

        init();
    }

    private void init() {
        initRecyclerView();
        initSmart();
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        this.adapter.setHasStableIds(true);

        RecyclerView recyclerView = binding.container.content;
        recyclerView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        recyclerView.setMotionEventSplittingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(binding.getRoot().getContext(), R.anim.recycler_view_fall_down);
        recyclerView.setLayoutAnimation(animationController);

        int spanCount;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            spanCount = 1;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        } else {
            spanCount = 1;
        }

        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, GridSpacingItemDecoration.SPACING, GridSpacingItemDecoration.INCLUDE_EDGE));
    }

    public void setObservable(Observable<T> observable) {
        this.observable = observable;
    }

    /**
     * 初始化 customViewRefreshViewSmart
     */
    private void initSmart() {
        binding.container.container.setOnRefreshListener((refreshLayout) -> insertData(LoadType.LOAD_TYPE_HEAD, refreshLayout));
        binding.container.container.setOnLoadMoreListener((refreshLayout) -> insertData(LoadType.LOAD_TYPE_TAIL, refreshLayout));
    }

    /**
     * 获取数据并显示
     *
     * @param loadType {LOAD_TYPE_HEAD/LOAD_TYPE_TAIL}
     */
    private void insertData(@LoadType int loadType, RefreshLayout refreshLayout) {
        if (updateInterface != null) {
            updateInterface.update(loadType);

            observable
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull T t) {
                            if (guideInterface != null) {
                                List<B> bs = guideInterface.guide(t);

                                if (bs == null || bs.isEmpty()) {
                                    onError(new RuntimeException("已经到底了~"));
                                } else {
                                    if (loadType == LoadType.LOAD_TYPE_TAIL) {
                                        adapter.appendTail(bs);
                                    } else if (loadType == LoadType.LOAD_TYPE_HEAD) {
                                        adapter.appendHead(bs);
                                    } else {
                                        onError(new RuntimeException());
                                    }
                                }
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            // TODO 待替换为SnackBar
                            Toast.makeText(binding.getRoot().getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            insertFinish(loadType, refreshLayout, false);
                        }

                        @Override
                        public void onComplete() {
                            insertFinish(loadType, refreshLayout, true);
                        }
                    });
        }
    }

    /**
     * 获取并插入数据结束后
     *
     * @param loadType      {LoadType.LOAD_TYPE_HEAD} {LoadType.LOAD_TYPE_TAIL}
     * @param refreshLayout refreshLayout
     * @param isComplete    数据是否已成功插入
     */
    private void insertFinish(int loadType, RefreshLayout refreshLayout, boolean isComplete) {
        if (refreshLayout != null) {
            if (loadType == LoadType.LOAD_TYPE_TAIL) {
                refreshLayout.finishLoadMore();
                binding.container.container.finishLoadMore();
            } else if (loadType == LoadType.LOAD_TYPE_HEAD) {
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
                if (adapter.getItemCount() == 0) {
                    binding.empty.container.setVisibility(View.VISIBLE);
                }

                closeSmart();
            }
        }
    }

    public void firstObtain () {
        if (!isFirstInsertFlag) {
            // 获取第一页数据
            insertData(LoadType.LOAD_TYPE_HEAD, binding.container.container);
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

    public void setUpdateInterface(UpdateInterface updateInterface) {
        this.updateInterface = updateInterface;
    }

    public interface UpdateInterface {
        /**
         * 更新请求参数
         *
         * @param loadType  LoadType
         */
        void update(@LoadType int loadType);
    }
}

/**
 * @Author Leon
 * @Time 2022/06/30
 * @Desc
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.PARAMETER})
@IntDef(value = {LoadType.LOAD_TYPE_HEAD, LoadType.LOAD_TYPE_TAIL})
@interface LoadType {
    /**
     * 加载至顶部标记
     */
    int LOAD_TYPE_HEAD = 1;

    /**
     * 加载至尾部标记
     */
    int LOAD_TYPE_TAIL = 2;
}
