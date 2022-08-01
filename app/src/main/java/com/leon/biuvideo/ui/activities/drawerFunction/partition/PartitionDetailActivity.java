package com.leon.biuvideo.ui.activities.drawerFunction.partition;

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
    public static final String PARAM_A = "tid";
    public static final String PARAM_B = "title";

    @Override
    public ActivityPartitionDetailBinding getViewBinding() {
        return ActivityPartitionDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        Partition partition = PartitionParser.Companion.getPartitionData(params.getInt(PARAM_A));
        binding.topBar.setTopBarTitle(params.getString(PARAM_B));

        int pageCount = partition.getSubs().size();

        List<Integer> ridList = new ArrayList<>(pageCount);
        String[] titles = new String[pageCount + 1];
        titles[0] = "推荐";

        List<Fragment> fragments = new ArrayList<>(pageCount);
        for (int i = 0; i < pageCount; i++) {
            Partition.Sub sub = partition.getSubs().get(i);

            fragments.add(new PartitionFragment(sub.getTid()));

            titles[i + 1] = sub.getName();
            ridList.add(sub.getTid());
        }
        fragments.add(0, new PartitionHomeFragment(ridList, titles, binding.content.viewPager));

        ViewUtils.initTabLayout(this, binding.content.tabLayout, binding.content.viewPager, fragments, titles);
    }
}