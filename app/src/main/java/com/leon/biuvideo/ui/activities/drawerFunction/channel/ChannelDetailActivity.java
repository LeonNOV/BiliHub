package com.leon.biuvideo.ui.activities.drawerFunction.channel;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivityChannelDetailBinding;

/**
 * @Author Leon
 * @Time 2022/7/15
 * @Desc
 */
public class ChannelDetailActivity extends BaseActivity<ActivityChannelDetailBinding> {
    public static final String PARAM_A = "id";
    public static final String PARAM_B = "name";

    @Override
    public ActivityChannelDetailBinding getViewBinding() {
        return ActivityChannelDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        binding.topBar.setTopBarTitle(params.getString(PARAM_B));
        binding.channelId.setText(params.getString(PARAM_A));
    }
}