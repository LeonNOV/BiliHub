package com.leon.biuvideo.ui.activities.actionActivities;

import android.view.View;

import androidx.fragment.app.Fragment;

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
            BaseAction action =
                    (BaseAction) Class.forName(params.getString(BaseAction.ACTION)).newInstance();
            ActionData actionData = action.createActionData();

            binding.topBar.setTopBarTitle(actionData.getTitle());

            if (actionData.isSingle()) {
                binding.contentSingle.getRoot().setVisibility(View.VISIBLE);
                binding.getRoot().removeView(binding.contentMulti.getRoot());


//                action.createAdapter(0);
            } else {
                binding.contentMulti.getRoot().setVisibility(View.VISIBLE);
                binding.getRoot().removeView(binding.contentSingle.getRoot());

                List<Fragment> fragments = new ArrayList<>();
                for (int i = 0; i < actionData.getSubPageCount(); i++) {
                    fragments.add(new DataListFragment(i, actionData.getFilterItems()).setExternalInterface(action));
                }

                ViewUtils.initTabLayout(this, binding.contentMulti.tabLayout, binding.contentMulti.viewPager,
                        fragments, actionData.getTabTitles());
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}