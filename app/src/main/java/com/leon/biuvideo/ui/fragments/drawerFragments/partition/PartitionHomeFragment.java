package com.leon.biuvideo.ui.fragments.drawerFragments.partition;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseFragment.BaseFragment;
import com.leon.biuvideo.databinding.FragmentPartitionHomeBinding;
import com.leon.biuvideo.ui.adapters.partition.PartitionHomeRecommendAdapter;
import com.leon.biuvideo.ui.widget.LinearItemDecoration;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;

/**
 * @Author Leon
 * @Time 2022/07/13
 * @Desc
 */
public class PartitionHomeFragment extends BaseFragment<FragmentPartitionHomeBinding> {
    private final List<Integer> ridList;
    private final String[] partitionTagTitles;
    private final ViewPager2 viewPager2;

    public PartitionHomeFragment(List<Integer> ridList, String[] partitionTagTitles, ViewPager2 viewPager2) {
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
        adapter.setOnMoveToPage(rid -> {
            int indexOf = ridList.indexOf(rid) + 1;

            viewPager2.setCurrentItem(indexOf, true);
        });


        ArrayList<String> tagTitles = new ArrayList<>(List.of(partitionTagTitles));
        tagTitles.remove(0);

        adapter.appendHead(tagTitles);
        binding.content.setAdapter(adapter);
    }
}
