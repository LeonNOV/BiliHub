package com.leon.bilihub.ui.activities.drawerFunction;

import androidx.fragment.app.Fragment;

import com.leon.bilihub.base.baseActivity.BaseActivity;
import com.leon.bilihub.databinding.ActivityFavoriteBinding;
import com.leon.bilihub.ui.dialogs.TipDialog;
import com.leon.bilihub.ui.fragments.drawerFragments.favorite.CollectListFragment;
import com.leon.bilihub.ui.fragments.drawerFragments.favorite.FolderListFragment;
import com.leon.bilihub.utils.PreferenceUtils;
import com.leon.bilihub.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Leon
 * @Time 2022/7/12
 * @Desc 收藏夹界面
 */
public class FavoriteActivity extends BaseActivity<ActivityFavoriteBinding> {

    @Override
    public ActivityFavoriteBinding getViewBinding() {
        return ActivityFavoriteBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        if (PreferenceUtils.getLoginStatus(context)) {
            List<Fragment> fragments = new ArrayList<>(2);
            fragments.add(new FolderListFragment());
            fragments.add(new CollectListFragment());

            ViewUtils.initTabLayout(this, binding.content.tabLayout, binding.content.viewPager, fragments, "收藏夹", "合集和订阅");
        } else {
            TipDialog.ShowLoginTipDialog(context);
        }
    }
}