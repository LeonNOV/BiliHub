package com.leon.bilihub.ui.activities.drawerFunction;

import androidx.fragment.app.Fragment;

import com.leon.bilihub.base.baseActivity.BaseActivity;
import com.leon.bilihub.databinding.ActivityOrderBinding;
import com.leon.bilihub.ui.dialogs.TipDialog;
import com.leon.bilihub.ui.fragments.drawerFragments.OrderFragment;
import com.leon.bilihub.utils.PreferenceUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Leon
 * @Time 2022/7/12
 * @Desc 订阅界面
 */
public class OrderActivity extends BaseActivity<ActivityOrderBinding> {

    @Override
    public ActivityOrderBinding getViewBinding() {
        return ActivityOrderBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        if (PreferenceUtils.getLoginStatus(context)) {
            List<Fragment> fragments = new ArrayList<>(2);
            fragments.add(new OrderFragment(1, PreferenceUtils.getUid(context)));
            fragments.add(new OrderFragment(2, PreferenceUtils.getUid(context)));

            ViewUtils.initTabLayout(this, binding.content.tabLayout, binding.content.viewPager, fragments, "番剧", "剧集");
        } else {
            TipDialog.showLoginTipDialog(context);
        }
    }
}