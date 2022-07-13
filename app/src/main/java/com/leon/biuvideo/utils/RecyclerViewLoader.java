package com.leon.biuvideo.utils;

import android.os.Parcelable;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.leon.biuvideo.base.baseAdapter.BaseViewBindingAdapter;
import com.leon.biuvideo.ui.widget.GridSpacingItemDecoration;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @Author Leon
 * @Time 2022/07/07
 * @Desc 需单独指定RecyclerView，适用于单页加载；分页加载使用{@link PaginationLoader}
 *
 * @param <T>   Retrofit响应数据
 * @param <B>   列表展示数据，需通过{@link PaginationLoader.GuideInterface}来指定
 */
public class RecyclerViewLoader<T extends Parcelable, B extends Parcelable>{
    private final RecyclerView recyclerView;
    private final BaseViewBindingAdapter<B, ? extends ViewBinding> adapter;

    private Observable<T> observable;
    private PaginationLoader.GuideInterface<T, B> guideInterface;

    public RecyclerViewLoader(RecyclerView recyclerView, BaseViewBindingAdapter<B, ? extends ViewBinding> adapter) {
        this.recyclerView = recyclerView;
        this.adapter = adapter;

        init();
    }

    private void init() {
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        this.recyclerView.setMotionEventSplittingEnabled(false);
        this.recyclerView.setHasFixedSize(true);

        int spanCount;
        RecyclerView.LayoutManager layoutManager = this.recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            spanCount = 1;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        } else {
            spanCount = 1;
        }

        this.recyclerView.addItemDecoration(new GridSpacingItemDecoration(adapter.context, spanCount, GridSpacingItemDecoration.INCLUDE_EDGE));
    }

    public RecyclerViewLoader<T, B> setObservable(Observable<T> observable) {
        this.observable = observable;

        return this;
    }

    public void obtain(boolean needClean) {
        if (needClean) {
            if (adapter.hasData()) {
                adapter.removeAll();
            }
        }

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
                                onError(new RuntimeException("啥都没有"));
                            } else {
                                adapter.appendHead(bs);
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(adapter.context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public RecyclerViewLoader<T, B> setGuide(PaginationLoader.GuideInterface<T, B> guideInterface) {
        this.guideInterface = guideInterface;

        return this;
    }
}
