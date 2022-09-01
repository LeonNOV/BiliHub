package com.leon.bilihub.base.baseAdapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

/**
 * @param <B> 展示数据
 * @param <V> ItemViewBinding
 * @Author Leon
 * @Time 2020/11/16
 * @Desc 基本的RecyclerViewAdapter为RecyclerView创建适配器时最好使用该抽象类进行创建ViewHolder为同包下的BaseViewHolder
 */
public abstract class ViewBindingAdapter<B, V extends ViewBinding> extends BaseAdapter<B, BaseAdapter.BaseViewHolder<V>> {
    public ViewBindingAdapter(Context context) {
        super(context);
    }

    /**
     * 获取ViewBinding
     *
     * @param context content
     * @param parent  parent
     * @return ViewBinding
     */
    protected abstract V getItemViewBinding(Context context, ViewGroup parent);

    /**
     * 创建ViewHolder
     *
     * @return 返回BaseViewHolder
     */
    @NonNull
    @Override
    public BaseAdapter.BaseViewHolder<V> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseAdapter.BaseViewHolder<>(getItemViewBinding(context, parent));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.BaseViewHolder<V> holder, int position) {
        onBindViewHolder(DATA_SOURCE.get(position), holder.getBinding(), position);
    }

    /**
     * onBindViewHolder overload
     *
     * @param data     绑定数据
     * @param position position
     * @param binding  binding
     */
    protected abstract void onBindViewHolder(B data, V binding, int position);
}
