package com.leon.biuvideo.ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.leon.biuvideo.base.baseActivity.BaseActivity;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/06/16
 * @Desc
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
}
