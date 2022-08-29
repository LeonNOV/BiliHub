package com.leon.bilihub.ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.leon.bilihub.base.baseActivity.BaseActivity;

import java.util.List;

/**
 * @Author Leon
 * @Time 2021/03/20
 * @Desc ViewPager2适配器
 */
public class ViewPager2Adapter extends FragmentStateAdapter {
    private final List<Fragment> fragments;

    public ViewPager2Adapter(BaseActivity fragmentActivity, List<Fragment> fragments) {
        super(fragmentActivity);

        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.setItemViewCacheSize(getItemCount());
    }
}
