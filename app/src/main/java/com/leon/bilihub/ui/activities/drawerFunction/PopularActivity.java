package com.leon.bilihub.ui.activities.drawerFunction;

import com.leon.bilihub.base.baseActivity.BaseActivity;
import com.leon.bilihub.databinding.ActivityPopularBinding;
import com.leon.bilihub.ui.fragments.drawerFragments.popularFragments.HotFragment;
import com.leon.bilihub.ui.fragments.drawerFragments.popularFragments.PreciousFragment;
import com.leon.bilihub.ui.fragments.drawerFragments.popularFragments.RankFragment;
import com.leon.bilihub.ui.fragments.drawerFragments.popularFragments.WeeklyFragment;
import com.leon.bilihub.utils.ViewUtils;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/7/3
 * @Desc
 */
public class PopularActivity extends BaseActivity<ActivityPopularBinding> {

    @Override
    public ActivityPopularBinding getViewBinding() {
        return ActivityPopularBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        ViewUtils.initTabLayout(this, binding.content.tabLayout, binding.content.viewPager,
                List.of(new HotFragment(), new WeeklyFragment(), new PreciousFragment(), new RankFragment()),
                "综合热门", "每周必看", "入站必刷", "全站排行榜");
    }
}