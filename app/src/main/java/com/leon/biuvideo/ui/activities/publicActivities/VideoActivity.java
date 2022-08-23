package com.leon.biuvideo.ui.activities.publicActivities;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.AppBarLayout;
import com.leon.biuvideo.base.baseActivity.BaseActivity;
import com.leon.biuvideo.beans.publicBeans.resources.video.PgcDetail;
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoDetail;
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoQuality;
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoStream;
import com.leon.biuvideo.databinding.ActivityVideoBinding;
import com.leon.biuvideo.http.ApiHelper;
import com.leon.biuvideo.http.BaseUrl;
import com.leon.biuvideo.http.HttpApi;
import com.leon.biuvideo.http.Quality;
import com.leon.biuvideo.http.RetrofitClient;
import com.leon.biuvideo.http.TestValue;
import com.leon.biuvideo.model.VideoPlayerModel;
import com.leon.biuvideo.ui.fragments.videoFragments.MediaCommentsFragment;
import com.leon.biuvideo.ui.fragments.videoFragments.MediaPgcInfoFragment;
import com.leon.biuvideo.ui.fragments.videoFragments.MediaVideoInfoFragment;
import com.leon.biuvideo.ui.widget.player.PlayerController;
import com.leon.biuvideo.utils.ValueUtils;
import com.leon.biuvideo.utils.ViewUtils;
import com.leon.biuvideo.wraps.VideoResourceWrap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import xyz.doikki.videoplayer.player.VideoView;

/**
 * @Author Leon
 * @Time 2022/6/19
 * @Desc 普通视频播放界面
 */
public class VideoActivity extends BaseActivity<ActivityVideoBinding> {
    public static final String PARAM_ID = "id";
    public static final String PARAM_TYPE = "type";

    public static final String TYPE_VIDEO = "video";
    public static final String TYPE_PGC = "pgc";

    private String id;
    private PlayerController playerController;

    private VideoPlayerModel videoPlayerModel;
    private Observer<VideoResourceWrap> videoResourceObserver;
    private Observer<Float> videoSpeedObserver;
    private Observer<Quality> videoQualityObserver;

    @Override
    public ActivityVideoBinding getViewBinding() {
        return ActivityVideoBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        if (params.containsKey(PARAM_ID)) {
            this.id = params.getString(PARAM_ID);

            setVideoController();
            initObserver();

            HttpApi httpApi = new RetrofitClient(BaseUrl.API, Map.of(HttpApi.COOKIE, TestValue.TEST_COOKIE)).getHttpApi();
            if (TYPE_VIDEO.equals(params.getString(PARAM_TYPE))) {
                new ApiHelper<>(httpApi.getVideoDetail(id)).setOnResult(this::setVideoInfo).doIt();
            } else {
                new ApiHelper<>(httpApi.getPgcDetail(id)).setOnResult(this::setPgcInfo).doIt();
            }
        } else {
            backPressed();
        }
    }

    private void initObserver() {
        videoPlayerModel = new ViewModelProvider(this).get(VideoPlayerModel.class);

        videoResourceObserver = this::setVideoResource;
        videoPlayerModel.getVideoResource().observeForever(videoResourceObserver);

        videoSpeedObserver = speed -> binding.player.setSpeed(speed);
        videoPlayerModel.getVideoSpeed().observeForever(videoSpeedObserver);

        videoQualityObserver = quality -> {
        };
        videoPlayerModel.getVideoQuality().observeForever(videoQualityObserver);
    }

    private void setVideoInfo(VideoDetail videoDetail) {
        //todo 此处清晰度需为用户默认指定清晰度
        videoPlayerModel.getVideoResource().setValue(new VideoResourceWrap(videoDetail.getData().getView().getCid(), Quality.Q80));

        VideoDetail.Data.View view = videoDetail.getData().getView();

        ViewUtils.initTabLayout(this, binding.extra.tabLayout, binding.extra.viewPager,
                List.of(new MediaVideoInfoFragment(videoDetail.getData()), new MediaCommentsFragment(view.getAid())),
                "简介", "评论" + ValueUtils.generateCN(view.getStat().getReply()));
    }

    private void setPgcInfo(PgcDetail pgcDetail) {
        videoPlayerModel.getVideoResource().setValue(new VideoResourceWrap(pgcDetail.getResult().getEpisodes().get(0).getCid(), Quality.Q80));

        ViewUtils.initTabLayout(this, binding.extra.tabLayout, binding.extra.viewPager,
                List.of(new MediaPgcInfoFragment(pgcDetail.getResult()), new MediaCommentsFragment(String.valueOf(pgcDetail.getResult().getEpisodes().get(0).getAid()))),
                "简介", "评论");
    }

    /**
     * 设置视频
     *
     * todo 普通视频和PGC内容的视频流URL链接不同，待更改
     *
     * @param videoResourceWrap VideoResourceWrap
     */
    private void setVideoResource(VideoResourceWrap videoResourceWrap) {
        new ApiHelper<>(new RetrofitClient(BaseUrl.API, Map.of(HttpApi.COOKIE, TestValue.TEST_COOKIE))
                .getHttpApi()
                .getVideoStream(id, videoResourceWrap.getCid(), videoResourceWrap.getQuality()))
                .setOnResult(videoStream -> {
                    updateQualityList(videoStream);

                    if (binding.player.isPlaying()) {
                        binding.player.pause();
                        binding.player.release();
                    }

                    binding.player.setUrl(videoStream.getData().getDurl().get(0).getUrl(), ValueUtils.createPlayerVideoHeader(id));
                    binding.player.start();
                }).doIt();
    }

    /**
     * 更新对应视频的清晰度列表或更新已选画质
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

            Quality quality = Quality.valueOf("Q" + supportFormat.getQuality());
            videoQualityList.add(new VideoQuality(extra, isOrdinary, videoStream.getData().getQuality() == supportFormat.getQuality(),
                    quality, supportFormat.getQuality(), supportFormat.getDisplayDesc()));
        });

        videoPlayerModel.getVideoSpeed().setValue(1.0F);
        videoPlayerModel.getVideoQualityListDisplay().setValue(videoQualityList);
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
                layoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
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
        binding.player.pause();
        binding.player.release();

        videoPlayerModel.getVideoResource().removeObserver(videoResourceObserver);
        videoPlayerModel.getVideoSpeed().removeObserver(videoSpeedObserver);
        videoPlayerModel.getVideoQuality().removeObserver(videoQualityObserver);

        super.onDestroy();
    }
}