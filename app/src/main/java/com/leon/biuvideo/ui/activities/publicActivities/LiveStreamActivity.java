package com.leon.biuvideo.ui.activities.publicActivities;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.databinding.ActivityLiveStreamBinding;
import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.model.LivePlayerModel;
import com.leon.biuvideo.ui.widget.player.PlayerController;

/**
 * @Author Leon
 * @Time 2022/6/19
 * @Desc
 */
public class LiveStreamActivity extends BaseActivity<ActivityLiveStreamBinding> {
    public static final String PARAM = "roomId";

    private String roomId;
    private PlayerController playerController;
    private HttpApi httpApi;

    private LivePlayerModel livePlayerModel;
    private Observer<Integer> liveQualityObserver;
    private Observer<String> liveRoadObserver;

    @Override
    public ActivityLiveStreamBinding getViewBinding() {
        return ActivityLiveStreamBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        this.roomId = params.getString(PARAM);
        this.livePlayerModel = new ViewModelProvider(this).get(LivePlayerModel.class);

        httpApi = new RetrofitClient(BaseUrl.LIVE).getHttpApi();

        liveQualityObserver = this::getLiveResource;
        livePlayerModel.getLiveQuality().observeForever(liveQualityObserver);

        liveRoadObserver = liveRoad -> {
            if (binding.player.isPlaying()) {
                binding.player.pause();
            }
            binding.player.release();

            binding.player.setUrl(liveRoad);
            binding.player.start();
        };
        livePlayerModel.getLiveRoad().observeForever(liveRoadObserver);

        new ApiHelper<>(httpApi.getLiveInfo(roomId)).setOnResult(liveInfo -> {
            playerController = new PlayerController(context);
            playerController.addDefaultControlComponent(liveInfo);
            binding.player.setVideoController(playerController);
        }).doIt();

        getLiveResource(10000);
    }

    private void getLiveResource(int qn) {
        new ApiHelper<>(httpApi.getLiveStream(roomId, qn)).setOnResult(liveStream -> {
            liveStream.getData().getDurl().forEach(road -> road.setSelected(false));
            liveStream.getData().getDurl().get(0).setSelected(true);

            liveStream.getData().getQualityDescription().forEach(qualityDescription -> qualityDescription.setSelected(qn == qualityDescription.getQn()));
            liveStream.getData().getQualityDescription().get(0).setSelected(true);

            livePlayerModel.getLiveRoad().setValue(liveStream.getData().getDurl().get(0).getUrl());
            livePlayerModel.getLiveRoadList().setValue(liveStream.getData().getDurl());
            livePlayerModel.getLiveQualityList().setValue(liveStream.getData().getQualityDescription());
        }).doIt();
    }

    @Override
    public void onBackPressed() {
        if (!playerController.onBackPressed()) {
            backPressed();
        }
    }

    @Override
    protected void onResume() {
        if (!binding.player.isPlaying()) {
            binding.player.resume();
        }

        super.onResume();
    }

    @Override
    protected void onPause() {
        if (binding.player.isPlaying()) {
            binding.player.pause();
        }

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        binding.player.pause();
        binding.player.release();

        livePlayerModel.getLiveRoad().removeObserver(liveRoadObserver);
        livePlayerModel.getLiveQuality().removeObserver(liveQualityObserver);

        super.onDestroy();
    }
}