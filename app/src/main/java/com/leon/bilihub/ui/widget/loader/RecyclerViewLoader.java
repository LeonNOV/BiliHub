package com.leon.bilihub.ui.widget.loader;

import android.os.Parcelable;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.leon.bilihub.base.baseAdapter.ViewBindingAdapter;
import com.leon.bilihub.utils.ViewUtils;

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
    private final ViewBindingAdapter<B, ? extends ViewBinding> adapter;

    private Observable<T> observable;
    private PaginationLoader.GuideInterface<T, B> guideInterface;

    public RecyclerViewLoader(RecyclerView recyclerView, ViewBindingAdapter<B, ? extends ViewBinding> adapter) {
        this.recyclerView = recyclerView;
        this.adapter = adapter;

        init();
    }

    public RecyclerViewLoader(RecyclerView recyclerView, ViewBindingAdapter<B, ? extends ViewBinding> adapter, PaginationLoader.OnInit<B> onInit) {
        this.recyclerView = recyclerView;
        this.adapter = adapter;

        if (onInit != null) {
            onInit.onInit(recyclerView, adapter);
        } else {
            init();
        }
    }

    private void init() {
        ViewUtils.listInitializer(this.recyclerView, adapter);
    }

    public RecyclerViewLoader<T, B> setObservable(Observable<T> observable) {
        this.observable = observable;

        return this;
    }

    public void obtain(boolean needClean) {
        if (needClean && adapter.hasData()) {
            adapter.removeAll();
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
                        Toast.makeText(adapter.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
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
