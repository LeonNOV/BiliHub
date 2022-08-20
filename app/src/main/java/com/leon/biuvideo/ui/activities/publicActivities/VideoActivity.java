package com.leon.biuvideo.ui.activities.publicActivities;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.AppBarLayout;
import com.leon.biuvideo.base.baseActivity.AsyncHttpActivity;
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoDetail;
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoQuality;
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoStream;
import com.leon.biuvideo.databinding.ActivityVideoBinding;
import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.Quality;
import com.leon.biuvideo.http.RequestData;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.http.TestValue;
import com.leon.biuvideo.ui.fragments.videoFragments.MediaCommentsFragment;
import com.leon.biuvideo.ui.fragments.videoFragments.VideoInfoFragment;
import com.leon.biuvideo.ui.widget.player.OnDestroy;
import com.leon.biuvideo.ui.widget.player.PlayerController;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;
import com.leon.biuvideo.model.VideoEpisodeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import xyz.doikki.videoplayer.player.VideoView;

/**
 * @Author Leon
 * @Time 2022/6/19
 * @Desc 视频播放界面
 */
public class VideoActivity extends AsyncHttpActivity<ActivityVideoBinding, VideoDetail> {
    /**
     * 普通视频
     */
    public static final String TYPE_VIDEO = "1";

    /**
     * 番剧
     */
    public static final String TYPE_BANGUMI = "2";

    /**
     * 电影/电视剧
     */
    public static final String TYPE_FT = "3";

    public static final String PARAM_ID = "id";
    public static final String PARAM_SEASON_ID = "seId";
    public static final String PARAM_TYPE = "type";

    private String bvid;
    private String type;

    private PlayerController playerController;
    private VideoEpisodeModel videoEpisodeModel;
    private Observer<String> resourceObserver;
    private Observer<Quality> qualityObserver;
    private Observer<Float> speedObserver;

    @Override
    public ActivityVideoBinding getViewBinding() {
        return ActivityVideoBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        if (params.containsKey(PARAM_ID)) {
            this.bvid = params.getString(PARAM_ID);
            this.type = params.getString(PARAM_TYPE);

            videoEpisodeModel = new ViewModelProvider(this).get(VideoEpisodeModel.class);
            setVideoController();

            // todo 待修改为用户自定义清晰度，开发期间默认{Quality.Q1080}
            resourceObserver = cid -> setVideo(cid, Quality.Q80);
            videoEpisodeModel.getResource().observeForever(resourceObserver);

            qualityObserver = quality -> setVideo(videoEpisodeModel.getResource().getValue(), quality);
            videoEpisodeModel.getQuality().observeForever(qualityObserver);

            speedObserver = speed -> binding.player.setSpeed(speed);
            videoEpisodeModel.getSpeed().observeForever(speedObserver);
        } else {
            backPressed();
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
        VideoDetail.Data.View view = videoDetail.getData().getView();

        videoEpisodeModel.getResource().setValue(view.getCid());
        videoEpisodeModel.getTitle().setValue(view.getTitle());

        ViewUtils.initTabLayout(this, binding.extra.tabLayout, binding.extra.viewPager,
                List.of(new VideoInfoFragment(videoDetail.getData()), new MediaCommentsFragment(view.getAid())),
                "简介", "评论" + ValueUtils.generateCN(view.getStat().getReply()));
    }

    /**
     * 设置视频
     *
     * @param cid     cid
     * @param quality quality
     */
    private void setVideo(String cid, Quality quality) {
        new ApiHelper<>(new RetrofitClient(BaseUrl.API, Map.of(HttpApi.COOKIE, TestValue.TEST_COOKIE))
                .getHttpApi()
                .getVideoStream(bvid, cid, quality))
                .setOnResult(videoStream -> {
                    updateQualityList(videoStream);
                    if (binding.player.isPlaying()) {
                        binding.player.pause();
                        binding.player.release();
                    }

                    binding.player.setUrl(videoStream.getData().getDurl().get(0).getUrl(), ValueUtils.createPlayerVideoHeader(bvid));
                    binding.player.start();
                }).doIt();
    }

    /**
     * 更新对应视频的清晰度列表
     *
     * @param videoStream VideoStream
     */
    private void updateQualityList(VideoStream videoStream) {
        List<VideoQuality> videoQualityList = new ArrayList<>();
        videoStream.getData().getSupportFormats().forEach(supportFormat -> {
            String extra = null;
            boolean isOrdinary = false;

            int i = ValueUtils.videoIdentify(supportFormat.getQuality());
            if (i == 3) {
                isOrdinary = true;
            } else if (i == 2) {
                extra = "需登录";
            } else {
                extra = "大会员";
            }

            videoQualityList.add(new VideoQuality(extra, isOrdinary, Quality.valueOf("Q" + supportFormat.getQuality()),
                    supportFormat.getDisplayDesc(), supportFormat.getNewDescription()));
        });

        videoEpisodeModel.getQualityList().setValue(videoQualityList);
        videoEpisodeModel.getQualityDisplay().setValue(Quality.valueOf("Q" + videoStream.getData().getQuality()));
        videoEpisodeModel.getSpeed().setValue(1.0F);
    }

    /**
     * 设置视频控制器
     */
    private void setVideoController() {
        playerController = new PlayerController(context);
        playerController.setOnPlayStateChangedListener(playState -> {
            if (playState == VideoView.STATE_PLAYING) {
                AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) binding.player.getLayoutParams();
                layoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL);
                binding.player.setLayoutParams(layoutParams);
            } else if (playState == VideoView.STATE_PAUSED) {
                AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) binding.player.getLayoutParams();
                layoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                binding.player.setLayoutParams(layoutParams);
            }
        });
        playerController.addDefaultControlComponent();
        binding.player.setVideoController(playerController);
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
        for (OnDestroy onDestroy : playerController.getOnDestroys()) {
            onDestroy.onDestroy();
        }

        binding.player.pause();
        binding.player.release();

        videoEpisodeModel.getResource().removeObserver(resourceObserver);
        videoEpisodeModel.getQuality().removeObserver(qualityObserver);
        videoEpisodeModel.getSpeed().removeObserver(speedObserver);

        super.onDestroy();
    }
}