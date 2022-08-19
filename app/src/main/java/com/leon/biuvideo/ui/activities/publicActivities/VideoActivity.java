package com.leon.biuvideo.ui.activities.publicActivities;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.leon.biuvideo.base.baseActivity.AsyncHttpActivity;
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoDetail;
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoQuality;
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoSpeed;
import com.leon.biuvideo.databinding.ActivityVideoBinding;
import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.Quality;
import com.leon.biuvideo.http.RequestData;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.http.TestValue;
import com.leon.biuvideo.ui.fragments.videoFragments.MediaCommentsFragment;
import com.leon.biuvideo.ui.fragments.videoFragments.MediaInfoFragment;
import com.leon.biuvideo.ui.widget.player.PlayerController;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;
import com.leon.biuvideo.wraps.VideoEpisodeWrap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

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
    private VideoEpisodeWrap videoEpisodeWrap;
    private Observer<String> titleObserver;
    private Observer<String> resourceObserver;
    private Observer<VideoQuality> qualityObserver;
    private Observer<VideoSpeed> speedObserver;

    private final List<VideoQuality> videoQualityList = new ArrayList<>();

    @Override
    public ActivityVideoBinding getViewBinding() {
        return ActivityVideoBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        if (params.containsKey(PARAM_ID)) {
            this.bvid = params.getString(PARAM_ID);
            this.type = params.getString(PARAM_TYPE);

            videoEpisodeWrap = new ViewModelProvider(this).get(VideoEpisodeWrap.class);
            setVideoController();

            // todo 待修改为用户自定义清晰度，开发期间默认{Quality.Q1080}
            resourceObserver = cid -> setVideo(cid, Quality.Q80);
            videoEpisodeWrap.getResource().observeForever(resourceObserver);

            titleObserver = title -> playerController.setTitle(title);
            videoEpisodeWrap.getTitle().observeForever(titleObserver);

            qualityObserver = videoQuality -> {
                setVideo(videoEpisodeWrap.getResource().getValue(), videoQuality.getQuality());
                playerController.setDisplayQn(videoQuality.getDisplayQn());
            };
            videoEpisodeWrap.getQuality().observeForever(qualityObserver);

            speedObserver = videoSpeed -> {
                playerController.setSpeed(videoSpeed.getSpeedStr());
                binding.player.setSpeed(videoSpeed.getSpeed());
            };
            videoEpisodeWrap.getSpeed().observeForever(speedObserver);
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

        videoEpisodeWrap.getResource().setValue(view.getCid());
        videoEpisodeWrap.getTitle().setValue(view.getTitle());

        ViewUtils.initTabLayout(this, binding.extra.tabLayout, binding.extra.viewPager,
                List.of(new MediaInfoFragment(videoDetail.getData()), new MediaCommentsFragment(view.getAid())),
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
                    if (videoQualityList.isEmpty()) {
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
                        playerController.setVideoQuality(videoQualityList);
                    }

                    if (binding.player.isPlaying()) {
                        binding.player.pause();
                        binding.player.release();
                    }

                    if (binding.player.getSpeed() != 1.0f) {
                        // 每次设置视频都需将视频速度设置为1.0x
                        videoEpisodeWrap.getSpeed().setValue(new VideoSpeed(1.0f, "1.0x"));
                    }

                    binding.player.setUrl(videoStream.getData().getDurl().get(0).getUrl(), ValueUtils.createPlayerVideoHeader(bvid));
                    binding.player.start();
                }).doIt();
    }

    /**
     * 设置视频控制器
     */
    private void setVideoController() {
        playerController = new PlayerController(context);
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
        binding.player.pause();
        binding.player.release();

        videoEpisodeWrap.getTitle().removeObserver(titleObserver);
        videoEpisodeWrap.getResource().removeObserver(resourceObserver);
        videoEpisodeWrap.getQuality().removeObserver(qualityObserver);
        videoEpisodeWrap.getSpeed().removeObserver(speedObserver);

        super.onDestroy();
    }
}