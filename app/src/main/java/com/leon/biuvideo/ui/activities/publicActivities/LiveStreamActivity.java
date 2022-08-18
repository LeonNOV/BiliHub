package com.leon.biuvideo.ui.activities.publicActivities;

import com.leon.biuvideo.base.baseActivity.AsyncHttpActivity;
import com.leon.biuvideo.beans.publicBeans.resources.live.LiveStream;
import com.leon.biuvideo.databinding.ActivityLiveStreamBinding;
import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.RequestData;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.ui.widget.player.PlayerController;

import io.reactivex.rxjava3.core.Observable;

/**
 * @Author Leon
 * @Time 2022/6/19
 * @Desc
 */
public class LiveStreamActivity extends AsyncHttpActivity<ActivityLiveStreamBinding, LiveStream> {
    public static final String PARAM = "roomId";

    private String roomId;
    private PlayerController playerController;

    @Override
    public ActivityLiveStreamBinding getViewBinding() {
        return ActivityLiveStreamBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        this.roomId = params.getString(PARAM);
    }

    @Override
    protected RequestData setRequestData() {
        return new RequestData(BaseUrl.LIVE);
    }

    @Override
    protected Observable<LiveStream> createObservable(RetrofitClient retrofitClient) {
        return retrofitClient.getHttpApi().getLiveStream(roomId, 10000);
    }

    @Override
    protected void onAsyncResult(LiveStream liveStream) {
        binding.player.setUrl(liveStream.getData().getDurl().get(0).getUrl());
        new ApiHelper<>(new RetrofitClient(BaseUrl.LIVE).getHttpApi().getLiveInfo(roomId)).setOnResult(liveInfo -> {
            playerController = new PlayerController(context);
            playerController.addDefaultControlComponent(liveInfo);
            binding.player.setVideoController(playerController);
            binding.player.start();
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

        super.onDestroy();
    }
}