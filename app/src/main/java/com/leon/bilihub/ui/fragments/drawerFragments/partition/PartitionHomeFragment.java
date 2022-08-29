package com.leon.bilihub.ui.fragments.drawerFragments.partition;

import androidx.viewpager2.widget.ViewPager2;

import com.leon.bilihub.base.baseFragment.BaseFragment;
import com.leon.bilihub.databinding.FragmentPartitionHomeBinding;
import com.leon.bilihub.ui.adapters.partition.PartitionHomeRecommendAdapter;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/07/13
 * @Desc
 */
public class PartitionHomeFragment extends BaseFragment<FragmentPartitionHomeBinding> {
    private final List<Integer> ridList;
    private final List<String> partitionTagTitles;
    private final ViewPager2 viewPager2;

    public PartitionHomeFragment(List<Integer> ridList, List<String> partitionTagTitles, ViewPager2 viewPager2) {
        this.ridList = ridList;
        this.partitionTagTitles = partitionTagTitles;
        this.viewPager2 = viewPager2;
    }

    @Override
    public FragmentPartitionHomeBinding getViewBinding() {
        return FragmentPartitionHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        PartitionHomeRecommendAdapter adapter = new PartitionHomeRecommendAdapter(context, ridList);
        adapter.setOnMoveToPage(position -> viewPager2.setCurrentItem(position + 1, true));
        adapter.appendHead(partitionTagTitles);
        binding.content.setAdapter(adapter);
    }
}
