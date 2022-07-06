package com.leon.biuvideo.ui.activities.actionActivities;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.leon.biuvideo.base.baseAction.BaseAction;
import com.leon.biuvideo.base.baseAction.ActionData;
import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivityDataListBinding;
import com.leon.biuvideo.ui.fragments.DataListFragment;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Leon
 * @Time 2022/6/18
 * @Desc
 */
public class DataListActivity extends BaseActivity<ActivityDataListBinding> {
    @Override
    public ActivityDataListBinding getViewBinding() {
        return ActivityDataListBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        try {
            BaseAction action = (BaseAction) Class.forName(params.getString(BaseAction.ACTION)).newInstance();
            ActionData actionData = action.createActionData();

            binding.dataListTopBar.setTopBarTitle(actionData.getTitle());

            if (actionData.isSingle()) {
                binding.dataListSingle.setVisibility(View.VISIBLE);
                binding.getRoot().removeView(binding.dataListMulti.getRoot());

                action.createAdapter(0);
            } else {
                binding.dataListMulti.getRoot().setVisibility(View.VISIBLE);
                binding.getRoot().removeView(binding.dataListSingle);

                List<Fragment> fragments = new ArrayList<>();
                for (int i = 0; i < actionData.getSubPageCount(); i++) {
                    fragments.add(new DataListFragment(action, i));
                }

                ViewUtils.initTabLayout(this, binding.dataListMulti.dataListContentTab, binding.dataListMulti.dataListContentViewPager,
                        fragments, actionData.getTabTitles());
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}