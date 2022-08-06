package com.leon.biuvideo.ui.activities.drawerFunction;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivityPopularBinding;
import com.leon.biuvideo.ui.fragments.drawerFragments.popularFragments.HotFragment;
import com.leon.biuvideo.ui.fragments.drawerFragments.popularFragments.PreciousFragment;
import com.leon.biuvideo.ui.fragments.drawerFragments.popularFragments.RankFragment;
import com.leon.biuvideo.ui.fragments.drawerFragments.popularFragments.WeeklyFragment;
import com.leon.biuvideo.utils.ViewUtils;

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