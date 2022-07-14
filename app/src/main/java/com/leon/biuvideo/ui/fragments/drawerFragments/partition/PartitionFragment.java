package com.leon.biuvideo.ui.fragments.drawerFragments.partition;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.leon.biuvideo.base.baseFragment.BaseLazyFragment;
import com.leon.biuvideo.beans.partition.PartitionTag;
import com.leon.biuvideo.databinding.FragmentPartitionBinding;
import com.leon.biuvideo.ui.adapters.PartitionFilterAdapter;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/07/13
 * @Desc
 */
public class PartitionFragment extends BaseLazyFragment<FragmentPartitionBinding> {
    private final List<PartitionTag> partitionTags;

    public PartitionFragment(List<PartitionTag> partitionTags) {
        this.partitionTags = partitionTags;
    }

    @Override
    public FragmentPartitionBinding getViewBinding() {
        return FragmentPartitionBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        if (partitionTags.size() > 0) {
            binding.filter.getRoot().setVisibility(View.VISIBLE);

            PartitionFilterAdapter adapter = new PartitionFilterAdapter(context);
            adapter.appendHead(partitionTags);

            binding.filter.subTab.setAdapter(adapter);
            binding.filter.subTab.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
            binding.filter.subTab.setMotionEventSplittingEnabled(false);
            binding.filter.subTab.setNestedScrollingEnabled(false);
            binding.filter.subTab.setHasFixedSize(true);

            binding.filter.more.setOnClickListener(v -> {
                Toast.makeText(context, "More", Toast.LENGTH_SHORT).show();
            });
        }

    }

    @Override
    protected void onLazyLoad() {

    }
}
