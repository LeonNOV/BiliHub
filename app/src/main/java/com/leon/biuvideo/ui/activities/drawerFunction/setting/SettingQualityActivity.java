package com.leon.biuvideo.ui.activities.drawerFunction.setting;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivitySettingQualityBinding;
import com.leon.biuvideo.http.Quality;
import com.leon.biuvideo.ui.adapters.drawer.setting.SettingLiveQualityAdapter;
import com.leon.biuvideo.ui.adapters.drawer.setting.SettingVideoQualityAdapter;
import com.leon.biuvideo.utils.PreferenceUtils;
import com.leon.biuvideo.utils.ViewUtils;
import com.leon.biuvideo.values.SettingValueCreator;

import java.util.List;

/**
 * @Author Leon
 * @Time 2022/8/26
 * @Desc
 */
public class SettingQualityActivity extends BaseActivity<ActivitySettingQualityBinding> {
    public static final String PARAM_TYPE = "type";
    public static final String PARAM_VIDEO = "1";
    public static final String PARAM_LIVE = "2";

    @Override
    public ActivitySettingQualityBinding getViewBinding() {
        return ActivitySettingQualityBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        int initSelectedPosition = 0;

        if (PARAM_VIDEO.equals(params.getString(PARAM_TYPE))) {
            binding.topBar.setTopBarTitle("默认视频画质");

            Quality videoQuality = PreferenceUtils.getVideoQuality(context);
            List<SettingValueCreator.SettingVideoQuality> videoQualityList = SettingValueCreator.createVideoQualityList();
            for (int i = 0; i < videoQualityList.size(); i++) {
                SettingValueCreator.SettingVideoQuality quality = videoQualityList.get(i);
                if (videoQuality == quality.getQuality()) {
                    quality.setSelected(true);
                    initSelectedPosition = i;
                } else {
                    quality.setSelected(false);
                }
            }

            SettingVideoQualityAdapter adapter = new SettingVideoQualityAdapter(context, initSelectedPosition);
            adapter.appendHead(videoQualityList);
            adapter.setOnSelectedListener(position -> {
                videoQualityList.get(position).setSelected(false);
                adapter.notifyItemChanged(position);
            });
            ViewUtils.linkAdapter(binding.content, adapter);
        } else {
            binding.topBar.setTopBarTitle("默认直播画质");

            int liveQuality = PreferenceUtils.getLiveQuality(context);
            List<SettingValueCreator.SettingLiveQuality> liveQualityList = SettingValueCreator.createLiveQualityList();
            for (int i = 0; i < liveQualityList.size(); i++) {
                SettingValueCreator.SettingLiveQuality quality = liveQualityList.get(i);
                if (liveQuality == quality.getQn()) {
                    quality.setSelected(true);
                    initSelectedPosition = i;
                } else {
                    quality.setSelected(false);
                }
            }

            SettingLiveQualityAdapter adapter = new SettingLiveQualityAdapter(context, initSelectedPosition);
            adapter.appendHead(liveQualityList);
            adapter.setOnSelectedListener(position -> {
                liveQualityList.get(position).setSelected(false);
                adapter.notifyItemChanged(position);
            });
            ViewUtils.linkAdapter(binding.content, adapter);
        }

    }
}