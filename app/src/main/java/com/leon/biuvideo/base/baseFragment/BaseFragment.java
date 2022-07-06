package com.leon.biuvideo.base.baseFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.leon.biuvideo.base.BindingInterface;

/**
 * @Author Leon
 * @Time 2021/10/10
 * @Desc
 */
public abstract class BaseFragment<V extends ViewBinding> extends Fragment implements BindingInterface<V> {
    protected Context context;
    protected View rootView;
    protected V binding;

    private BaseFragmentOnDestroyListener baseFragmentOnDestroyListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = getViewBinding();
        rootView = binding.getRoot();

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    /**
     * 初始化控件
     */
    protected abstract void initView();

    @Override
    public void onDetach() {
        super.onDetach();

        binding = null;
    }

    @Override
    public void onDestroy() {
        if (baseFragmentOnDestroyListener != null) {
            baseFragmentOnDestroyListener.onDestroy();
        }

        binding = null;
        super.onDestroy();
    }

    public void setBaseFragmentOnDestroyListener(BaseFragmentOnDestroyListener baseFragmentOnDestroyListener) {
        this.baseFragmentOnDestroyListener = baseFragmentOnDestroyListener;
    }

    public interface BaseFragmentOnDestroyListener {
        void onDestroy();
    }
}
