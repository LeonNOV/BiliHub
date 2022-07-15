package com.leon.biuvideo.ui.activities.drawerFunction.partition;

import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.beans.partition.Partition;
import com.leon.biuvideo.databinding.ActivityPartitionDetailBinding;
import com.leon.biuvideo.parser.PartitionParser;
import com.leon.biuvideo.ui.fragments.drawerFragments.partition.PartitionFragment;
import com.leon.biuvideo.ui.fragments.drawerFragments.partition.PartitionHomeFragment;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Leon
 * @Time 2022/7/13
 * @Desc
 */
public class PartitionDetailActivity extends BaseActivity<ActivityPartitionDetailBinding> {
    public static final String PARAM_NAME = "name";
    public static final String PARAM_TITLE = "title";

    @Override
    public ActivityPartitionDetailBinding getViewBinding() {
        return ActivityPartitionDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        String partitionName = params.getString(PARAM_NAME);
        binding.topBar.setTopBarTitle(params.getString(PARAM_TITLE));

        ArrayList<Partition> partitionData = PartitionParser.Companion.getPartitionData(partitionName);
        if (partitionData != null) {
            int pageCount = partitionData.size();

            List<String> ridList = new ArrayList<>(pageCount);
            String[] titles = new String[pageCount + 1];
            titles[0] = "推荐";

            List<Fragment> fragments = new ArrayList<>(pageCount);
            for (int i = 0; i < pageCount; i++) {
                Partition partition = partitionData.get(i);
                fragments.add(new PartitionFragment(partition.getTags(), partition.getId()));
                titles[i + 1] = partition.getTitle();
                ridList.add(partition.getId());
            }
            fragments.add(0, new PartitionHomeFragment(ridList, titles, binding.content.viewPager));

            ViewUtils.initTabLayout(this, binding.content.tabLayout, binding.content.viewPager, fragments, titles);
        } else {
            backPressed();
            Toast.makeText(context, "无子页面", Toast.LENGTH_SHORT).show();
        }
    }
}