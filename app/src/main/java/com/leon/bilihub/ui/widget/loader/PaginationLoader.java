package com.leon.bilihub.ui.widget.loader;

import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.IntDef;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.leon.bilihub.base.baseAdapter.BaseAdapter;
import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.databinding.RefreshContentBinding;
import com.leon.bilihub.utils.ViewUtils;
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
 * @Author Leon
 * @Time 2021/11/02
 * @Desc 分页加载器，适用于多页面加载；单一页面使用{@link RecyclerViewLoader}
 */
public class PaginationLoader<T extends Parcelable, B extends Parcelable> {
    /**
     * 首次插入标记
     */
    private boolean isFirstInsertFlag = false;

    private boolean isEnabled = true;

    private final RefreshContentBinding binding;
    private final BaseAdapter<B, ?> adapter;

    private UpdateInterface<T> updateInterface;
    private GuideInterface<T, B> guideInterface;

    public PaginationLoader(@NonNull RefreshContentBinding binding, BaseAdapter<B, ?> adapter) {
        this.binding = binding;
        this.adapter = adapter;

        init();
    }

    public PaginationLoader(@NonNull RefreshContentBinding binding, BaseAdapter<B, ?> adapter, RecyclerView.LayoutManager layoutManager) {
        this.binding = binding;
        this.adapter = adapter;
        this.binding.container.content.setLayoutManager(layoutManager);

        init();
    }

    private void init() {
        initRecyclerView();
        initSmart();

        // 默认关闭下拉刷新
        enabledRefresh(false);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        ViewUtils.listInitializer(binding.container.content, adapter);
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
            Observable<T> observable = updateInterface.update(loadType);

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
                                        binding.container.content.smoothScrollToPosition(0);
                                    } else {
                                        onError(new RuntimeException());
                                    }
                                }
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
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

                toggleSmart();
            }
        }
    }

    /**
     * 适合在页面初始化后调用
     */
    public void firstObtain() {
        if (!isFirstInsertFlag) {
            // 获取第一页数据
            insertData(LoadType.LOAD_TYPE_HEAD, binding.container.container);
        }
    }

    /**
     * 适合在页面数据清空后调用
     */
    public void obtain() {
        binding.empty.getRoot().setVisibility(View.GONE);

        // 获取第一页数据
        insertData(LoadType.LOAD_TYPE_HEAD, binding.container.container);
    }

    /**
     * 关闭/开启 刷新和加载控件
     */
    public void toggleSmart() {
        isEnabled = !isEnabled;

        binding.container.container.setEnableLoadMore(isEnabled);
    }

    /**
     * 关闭刷新控件
     */
    public void enabledRefresh(boolean enabled) {
        binding.container.container.setEnableRefresh(enabled);
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

    /**
     * 更新接口参数时调用该方法，并实现{@link UpdateInterface}
     *
     * @param updateInterface updateInterface
     */
    public PaginationLoader<T, B> setUpdateInterface(UpdateInterface<T> updateInterface) {
        if (updateInterface != null && !isEnabled) {
            toggleSmart();
        }
        this.updateInterface = updateInterface;

        return this;
    }

    public interface UpdateInterface<T> {
        /**
         * 更新请求参数
         *
         * @param loadType LoadType
         * @return Observable<T>
         */
        Observable<T> update(@LoadType int loadType);
    }

    public interface OnInit<B> {
        void onInit(RecyclerView content, ViewBindingAdapter<B, ? extends ViewBinding> adapter);
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
