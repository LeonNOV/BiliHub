package com.leon.biuvideo.ui.activities.drawerFunction.channel;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivityChannelDetailBinding;
import com.leon.biuvideo.ui.fragments.channelFragments.ChannelFeaturedFragment;
import com.leon.biuvideo.ui.fragments.channelFragments.ChannelMultipleFragment;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/7/15
 * @Desc
 */
public class ChannelDetailActivity extends BaseActivity<ActivityChannelDetailBinding> {
    public static final String PARAM_ID = "id";
    public static final String PARAM_NAME = "name";
    public String channelId;

    @Override
    public ActivityChannelDetailBinding getViewBinding() {
        return ActivityChannelDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        this.channelId = params.getString(PARAM_ID);
        binding.topBar.setTopBarTitle(params.getString(PARAM_NAME));

        ViewUtils.initTabLayout(this, binding.content.tabLayout, binding.content.viewPager,
                List.of(new ChannelFeaturedFragment(channelId), new ChannelMultipleFragment(channelId)), "精选", "综合");
    }
}