package com.leon.biuvideo.ui.activities.drawerFunction;

import androidx.fragment.app.Fragment;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivityOrderBinding;
import com.leon.biuvideo.http.TestValue;
import com.leon.biuvideo.ui.fragments.drawerFragments.order.OrderFragment;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Leon
 * @Time 2022/7/12
 * @Desc 收藏夹界面
 */
public class OrderActivity extends BaseActivity<ActivityOrderBinding> {

    @Override
    public ActivityOrderBinding getViewBinding() {
        return ActivityOrderBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        List<Fragment> fragments = new ArrayList<>(2);
        fragments.add(new OrderFragment(1, TestValue.BVID));
        fragments.add(new OrderFragment(2, TestValue.BVID));

        ViewUtils.initTabLayout(this, binding.content.tabLayout, binding.content.viewPager, fragments, "番剧", "剧集");
    }
}