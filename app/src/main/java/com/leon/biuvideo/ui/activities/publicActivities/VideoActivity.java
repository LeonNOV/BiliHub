package com.leon.biuvideo.ui.activities.publicActivities;

import com.leon.biuvideo.base.baseActivity.AsyncHttpActivity;
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoDetail;
import com.leon.biuvideo.databinding.ActivityVideoBinding;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.RequestData;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.http.TestValue;
import com.leon.biuvideo.ui.fragments.videoFragments.MediaCommentsFragment;
import com.leon.biuvideo.ui.fragments.videoFragments.MediaInfoFragment;
import com.leon.biuvideo.ui.widget.player.PlayerController;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

/**
 * @Author Leon
 * @Time 2022/6/19
 * @Desc todo 部分视图功能待完善
 */
public class VideoActivity extends AsyncHttpActivity<ActivityVideoBinding, VideoDetail> {
    public static final String PARAM_BVID = "bvid";
    public static final String PARAM_SEASON_ID = "seId";
    public static final String PARAM_TYPE = "type";

    private String bvid;

    @Override
    public ActivityVideoBinding getViewBinding() {
        return ActivityVideoBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        if (params.containsKey(PARAM_BVID)) {
            this.bvid = params.getString(PARAM_BVID);
        }
    }

    @Override
    protected RequestData setRequestData() {
        return new RequestData(BaseUrl.API, Map.of(HttpApi.COOKIE, TestValue.TEST_COOKIE));
    }

    @Override
    protected Observable<VideoDetail> createObservable(RetrofitClient retrofitClient) {
        return retrofitClient.getHttpApi().getVideoDetail(bvid);
    }

    @Override
    protected void onAsyncResult(VideoDetail videoDetail) {
        initPlayer(videoDetail.getData().getView().getBvid(), videoDetail.getData().getView().getCid());
        ViewUtils.initTabLayout(this, binding.extra.tabLayout, binding.extra.viewPager,
                List.of(new MediaInfoFragment(videoDetail.getData()), new MediaCommentsFragment(videoDetail.getData().getView().getAid())), "简介", "评论" + videoDetail.getData().getView().getStat().getReply());
    }

    private void initPlayer(String bvid, String cid) {
        binding.player.setUrl("https://cdn.cnbj1.fds.api.mi-img.com/product-images/cyberone/v1.mp4");

        PlayerController playerController = new PlayerController(context);
        playerController.addDefaultControlComponent("Test title", cid);
        binding.player.setVideoController(playerController);
        binding.player.start();

        /*new ApiHelper<>(new RetrofitClient(BaseUrl.API, Map.of(HttpApi.COOKIE, TestValue.TEST_COOKIE))
                .getHttpApi()
                .getVideoStream(bvid, cid, Quality.Q1080))
                .setOnResult(videoStream -> {


                    Log.d(TAG, "initPlayer: " + videoStream.getData().getAcceptDescription());
                    Log.d(TAG, "initPlayer-quality: " + videoStream.getData().getQuality());
                }).doIt();*/
    }

    @Override
    protected void onResume() {
        if (binding.player.isPlaying()) {
            binding.player.pause();
        } else {
            binding.player.resume();
        }

        super.onResume();
    }

    @Override
    protected void onDestroy() {
        binding.player.pause();
        binding.player.release();

        super.onDestroy();
    }
}