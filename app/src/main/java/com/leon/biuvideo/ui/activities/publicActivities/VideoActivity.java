package com.leon.biuvideo.ui.activities.publicActivities;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivityVideoBinding;
import com.leon.biuvideo.ui.fragments.videoFragments.MediaCommentsFragment;
import com.leon.biuvideo.ui.fragments.videoFragments.MediaInfoFragment;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/6/19
 * @Desc
 */
public class VideoActivity extends BaseActivity<ActivityVideoBinding> {
    public static final String PARAM_BVID = "bvid";
    public static final String PARAM_SEASON_ID = "seId";
    public static final String PARAM_TYPE = "type";

    @Override
    public ActivityVideoBinding getViewBinding() {
        return ActivityVideoBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        if (params.containsKey(PARAM_BVID)) {
            ViewUtils.initTabLayout(this, binding.extra.tabLayout, binding.extra.viewPager,
                    List.of(new MediaInfoFragment(params.getString(PARAM_BVID)), new MediaCommentsFragment(params.getString(PARAM_BVID))), "简介", "评论");
        }
    }
}