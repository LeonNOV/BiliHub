package com.leon.biuvideo.base.baseAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Leon
 * @Time 2020/11/16
 * @Desc 基本的RecyclerViewAdapter为RecyclerView创建适配器时最好使用该抽象类进行创建ViewHolder为同包下的BaseViewHolder
 * @param <B>   展示数据
 * @param <V>   ItemViewBinding
 */
public abstract class BaseViewBindingAdapter<B, V extends ViewBinding> extends RecyclerView.Adapter<BaseViewBindingAdapter.BaseViewHolder> {
    private final List<B> beans;
    public final Context context;

    public View itemView;

    public BaseViewBindingAdapter(Context context) {
        this.context = context;
        this.beans = new ArrayList<>();
    }

    /**
     * 用于RecyclerView绑定item使用
     *
     * @param viewType  itemID
     * @return  返回itemID
     */
    public abstract @LayoutRes int getLayout(int viewType);

    /**
     * 获取ViewBinding
     *
     * @return  ViewBinding
     */
    protected abstract V getItemViewBinding();

    /**
     * 创建ViewHolder
     *
     * @return  返回BaseViewHolder
     */
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        this.itemView = layoutInflater.inflate(getLayout(viewType), parent, false);

        return new BaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        onBindViewHolder(beans.get(position), getItemViewBinding(), position);
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
    public void appendTail(List<B> addOns) {
        int positionStart = this.beans.size();

        this.beans.addAll(addOns);

        notifyItemRangeInserted(positionStart, addOns.size());
    }

    /**
     * 在头部加载数据
     *
     * @param addOns    要加入的数据
     */
    public void appendHead (List<B> addOns) {
        this.beans.addAll(0, addOns);

        notifyItemRangeInserted(0, this.beans.size());
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

        this.beans.remove(index);
        notifyItemRemoved(index);
    }

    /**
     * 清空已存在的数据
     */
    public void removeAll() {
        int count = this.beans.size();
        this.beans.clear();

        notifyItemRangeRemoved(0, count);
    }

    public boolean hasData() {
        return this.beans.size() > 0;
    }


    /**
     * @Author Leon
     * @Time 2020/11/16
     * @Desc 基本的ViewHolder
     */
    protected static class BaseViewHolder extends RecyclerView.ViewHolder {
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
