package com.leon.bilihub.ui.activities.drawerFunction.partition;

import androidx.fragment.app.Fragment;

import com.leon.bilihub.base.baseActivity.BaseActivity;
import com.leon.bilihub.beans.partition.Partition;
import com.leon.bilihub.databinding.ActivityPartitionDetailBinding;
import com.leon.bilihub.parser.PartitionParser;
import com.leon.bilihub.ui.fragments.drawerFragments.partition.PartitionFragment;
import com.leon.bilihub.ui.fragments.drawerFragments.partition.PartitionHomeFragment;
import com.leon.bilihub.utils.ViewUtils;

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
        List<String> titleList = new ArrayList<>(pageCount);
        String[] tabTitles = new String[pageCount + 1];
        tabTitles[0] = "推荐";

        List<Fragment> fragments = new ArrayList<>(pageCount);
        for (int i = 0; i < pageCount; i++) {
            Partition.Sub sub = partition.getSubs().get(i);
            if (sub.getTid() != null) {
                fragments.add(new PartitionFragment(sub.getTid()));
                titleList.add(sub.getName());
                ridList.add(sub.getTid());
                tabTitles[i + 1] = sub.getName();
            }
        }
        fragments.add(0, new PartitionHomeFragment(ridList, titleList, binding.content.viewPager));

        ViewUtils.initTabLayout(this, binding.content.tabLayout, binding.content.viewPager, fragments, tabTitles);
    }
}