package com.leon.biuvideo.base.baseAdapter;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.leon.biuvideo.base.baseActivity.ActivityManager;
import com.leon.biuvideo.base.baseActivity.BaseActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Author Leon
 * @Time 2020/11/16
 * @Desc 基本的RecyclerViewAdapter为RecyclerView创建适配器时最好使用该抽象类进行创建ViewHolder为同包下的BaseViewHolder
 * @param <B>   展示数据
 * @param <V>   ItemViewBinding
 */
public abstract class BaseViewBindingAdapter<B, V extends ViewBinding> extends RecyclerView.Adapter<BaseViewBindingAdapter.BaseViewHolder<V>> {
    private final List<B> beans;
    public final Context context;

    public BaseViewBindingAdapter(Context context) {
        this.context = context;
        this.beans = new ArrayList<>();
    }

    /**
     * 获取ViewBinding
     *
     * @param context content
     * @return  ViewBinding
     */
    protected abstract V getItemViewBinding(Context context, ViewGroup parent);

    /**
     * 创建ViewHolder
     *
     * @return  返回BaseViewHolder
     */
    @NonNull
    @Override
    public BaseViewHolder<V> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder<>(getItemViewBinding(context, parent));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<V> holder, int position) {
        onBindViewHolder(beans.get(position), holder.getBinding(), position);
    }

    /**
     * onBindViewHolder overload
     *
     * @param data  绑定数据
     * @param position  position
     * @param binding binding
     */
    protected abstract void onBindViewHolder (B data, V binding, int position);

    /**
     * 获取数据总数
     *
     * @return  返回数据总数
     */
    @Override
    public int getItemCount() {
        return beans.size();
    }

    /**
     * 重写该方法，防止数据出现错乱的问题
     *
     * @param position  position
     * @return  position
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     * 在尾部加载数据
     *
     * @param addOns    要加入的数据
     */
    public void appendTail(Collection<B> addOns) {
        int positionStart = this.beans.size();
        this.beans.addAll(addOns);

        notifyItemRangeInserted(positionStart, addOns.size());
    }

    /**
     * 在头部加载数据
     *
     * @param addOns    要加入的数据
     */
    public void appendHead (Collection<B> addOns) {
        this.beans.addAll(0, addOns);

        notifyItemRangeInserted(0, addOns.size() - 1);
    }

    /**
     * 刷新加载数据
     *
     * @param addOn    要加入的数据
     */
    public void appendTail(B addOn) {
        this.beans.add(addOn);

        notifyItemInserted(this.beans.size() - 1);
    }

    /**
     * 根据其对象进行删除
     *
     * @param b     对象
     */
    public void remove(B b) {
        int index = this.beans.indexOf(b);

        this.beans.remove(b);
        notifyItemRemoved(index);
    }

    /**
     * 清空已存在的数据
     */
    public void removeAll() {
        if (hasData()) {
            int count = this.beans.size();
            this.beans.clear();

            notifyItemRangeRemoved(0, count);
        }
    }

    public boolean hasData() {
        return this.beans.size() > 0;
    }

    protected void startActivity(Class<? extends BaseActivity<? extends ViewBinding>> targetClass) {
        ActivityManager.startActivity(context, targetClass);
    }

    protected void startActivity(Class<? extends BaseActivity<? extends ViewBinding>> targetClass, Map<String, String> params) {
        ActivityManager.startActivity(context, targetClass, params);
    }

    protected void startActivity(Class<? extends BaseActivity<? extends ViewBinding>> targetClass, Bundle bundle) {
        ActivityManager.startActivity(context, targetClass, bundle);
    }

    /**
     * @Author Leon
     * @Time 2020/11/16
     * @Desc 基本的ViewHolder
     */
    protected static class BaseViewHolder<V extends ViewBinding> extends RecyclerView.ViewHolder {
        private final V binding;

        public BaseViewHolder(V binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public V getBinding() {
            return binding;
        }
    }
}
