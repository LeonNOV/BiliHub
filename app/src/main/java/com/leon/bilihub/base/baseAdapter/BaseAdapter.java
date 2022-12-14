package com.leon.bilihub.base.baseAdapter;

import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.leon.bilihub.base.baseActivity.ActivityManager;
import com.leon.bilihub.base.baseActivity.BaseActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Author Leon
 * @Time 2022/09/01
 * @Desc
 */
public abstract class BaseAdapter<B, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected final List<B> DATA_SOURCE = new ArrayList<>();
    protected final Context context;

    public BaseAdapter(Context context) {
        this.context = context;
    }

    /**
     * 在尾部加载数据
     *
     * @param addOns    要加入的数据
     */
    public void appendTail(Collection<B> addOns) {
        int positionStart = this.DATA_SOURCE.size();
        this.DATA_SOURCE.addAll(addOns);

        notifyItemRangeInserted(positionStart, addOns.size());
    }

    /**
     * 在头部加载数据
     *
     * @param addOns    要加入的数据
     */
    public void appendHead (Collection<B> addOns) {
        this.DATA_SOURCE.addAll(0, addOns);

        notifyItemRangeInserted(0, addOns.size());
    }

    /**
     * 刷新加载数据
     *
     * @param addOn    要加入的数据
     */
    public void appendTail(B addOn) {
        this.DATA_SOURCE.add(addOn);

        notifyItemInserted(this.DATA_SOURCE.size());
    }

    /**
     * 根据其对象进行删除
     *
     * @param b     对象
     */
    public void remove(B b) {
        int index = this.DATA_SOURCE.indexOf(b);

        this.DATA_SOURCE.remove(b);
        notifyItemRemoved(index);
    }

    /**
     * 清空已存在的数据
     */
    public void removeAll() {
        if (hasData()) {
            int count = this.DATA_SOURCE.size();
            this.DATA_SOURCE.clear();

            notifyItemRangeRemoved(0, count);
        }
    }

    public boolean hasData() {
        return this.DATA_SOURCE.size() > 0;
    }

    /**
     * 获取数据总数
     *
     * @return  返回数据总数
     */
    @Override
    public int getItemCount() {
        return DATA_SOURCE.size();
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
     * 重写该方法，防止数据出现错乱的问题
     *
     * @param position position
     * @return position
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public List<B> getDataSource() {
        return DATA_SOURCE;
    }

    public Context getContext() {
        return context;
    }

    /**
     * @Author Leon
     * @Time 2020/11/16
     * @Desc 基本的ViewHolder
     */
    public static class BaseViewHolder<V extends ViewBinding> extends RecyclerView.ViewHolder {
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
