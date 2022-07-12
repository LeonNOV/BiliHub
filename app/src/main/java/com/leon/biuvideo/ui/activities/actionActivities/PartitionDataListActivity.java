package com.leon.biuvideo.ui.activities.actionActivities;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.leon.biuvideo.base.baseAction.ActionData;
import com.leon.biuvideo.base.baseAction.BaseAction;
import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.beans.Partition;
import com.leon.biuvideo.databinding.ActivityPartitionDataListBinding;
import com.leon.biuvideo.ui.fragments.PartitionDataListFragment;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Leon
 * @Time 2022/6/22
 * @Desc
 */
public class PartitionDataListActivity extends BaseActivity<ActivityPartitionDataListBinding> {
    @Override
    public ActivityPartitionDataListBinding getViewBinding() {
        return ActivityPartitionDataListBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        try {
            BaseAction action = (BaseAction) Class.forName(params.getString(BaseAction.ACTION)).newInstance();
            ActionData actionData = action.createActionData();

            binding.partitionDataListTopBar.setTopBarTitle(actionData.getTitle());

            List<Fragment> fragments = new ArrayList<>();
            for (int i = 0; i < actionData.getSubPageCount(); i++) {
                Partition partition = actionData.getPartitionList().get(i);
                fragments.add(new PartitionDataListFragment(partition, action, i));
            }

            binding.multi.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            ViewUtils.initTabLayout(this, binding.multi.tabLayout, binding.multi.viewPager,
                    fragments, actionData.getTabTitles());

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}