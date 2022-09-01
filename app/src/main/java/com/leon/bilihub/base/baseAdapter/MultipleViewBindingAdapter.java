package com.leon.bilihub.base.baseAdapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

/**
 * @param <B> 展示数据
 * @Author Leon
 * @Time 2020/11/16
 * @Desc 基本的RecyclerViewAdapter为RecyclerView创建适配器时最好使用该抽象类进行创建ViewHolder为同包下的BaseViewHolder
 */
public abstract class MultipleViewBindingAdapter<B> extends BaseAdapter<B, BaseAdapter.BaseViewHolder<? extends ViewBinding>> {
    public MultipleViewBindingAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        return getItemViewType(DATA_SOURCE.get(position));
    }

    /**
     * 获取当前Item style
     *
     * @param data data
     * @return item layout id
     */
    protected abstract int getItemViewType(B data);

    /**
     * 创建ViewHolder
     *
     * @return 返回BaseViewHolder
     */
    @NonNull
    @Override
    public BaseAdapter.BaseViewHolder<? extends ViewBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseAdapter.BaseViewHolder<>(getItemViewBinding(context, parent, viewType));
    }

    /**
     * 获取ViewBinding
     *
     * @param context  content
     * @param parent   parent
     * @param viewType viewType
     * @return ViewBinding
     */
    protected abstract ViewBinding getItemViewBinding(Context context, ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.BaseViewHolder<? extends ViewBinding> holder, int position) {
        onBindViewHolder(DATA_SOURCE.get(position), holder.getBinding(), position);
    }

    /**
     * onBindViewHolder overload
     *
     * @param data     绑定数据
     * @param position position
     * @param binding  binding
     */
    protected abstract void onBindViewHolder(B data, ViewBinding binding, int position);
}
