package com.leon.bilihub.ui.activities.publicActivities;

import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.AppBarLayout;
import com.leon.bilihub.base.baseActivity.BaseActivity;
import com.leon.bilihub.beans.publicBeans.resources.video.PgcDetail;
import com.leon.bilihub.beans.publicBeans.resources.video.VideoDetail;
import com.leon.bilihub.beans.publicBeans.resources.video.VideoQuality;
import com.leon.bilihub.beans.publicBeans.resources.video.VideoStream;
import com.leon.bilihub.databinding.ActivityVideoBinding;
import com.leon.bilihub.http.ApiHelper;
import com.leon.bilihub.http.BaseUrl;
import com.leon.bilihub.http.HttpApi;
import com.leon.bilihub.http.Quality;
import com.leon.bilihub.http.RetrofitClient;
import com.leon.bilihub.model.VideoPlayerModel;
import com.leon.bilihub.ui.fragments.videoFragments.MediaCommentsFragment;
import com.leon.bilihub.ui.fragments.videoFragments.MediaPgcInfoFragment;
import com.leon.bilihub.ui.fragments.videoFragments.MediaVideoInfoFragment;
import com.leon.bilihub.ui.widget.player.PlayerController;
import com.leon.bilihub.utils.PreferenceUtils;
import com.leon.bilihub.utils.ValueUtils;
import com.leon.bilihub.utils.ViewUtils;
import com.leon.bilihub.wraps.VideoResourceWrap;

import java.util.ArrayList;
import java.util.List;

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

    private boolean isPgc;
    private String id;
    private PlayerController playerController;
    private HttpApi httpApi;

    private VideoPlayerModel videoPlayerModel;
    private Observer<VideoResourceWrap> videoResourceObserver;
    private Observer<Float> videoSpeedObserver;

    @Override
    public ActivityVideoBinding getViewBinding() {
        return ActivityVideoBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void init() {
        closeImmersion();

        if (params != null && params.containsKey(PARAM_ID)) {
            this.id = params.getString(PARAM_ID);

            setVideoController();
            initObserver();

            httpApi = new RetrofitClient(BaseUrl.API, context).getHttpApi();
            if (TYPE_VIDEO.equals(params.getString(PARAM_TYPE))) {
                this.isPgc = false;
                new ApiHelper<>(httpApi.getVideoDetail(id)).setOnResult(this::setVideoInfo).doIt();
            } else {
                this.isPgc = true;
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
    }

    private void setVideoInfo(VideoDetail videoDetail) {
        VideoDetail.Data.View view = videoDetail.getData().getView();
        videoPlayerModel.getVideoResource().setValue(new VideoResourceWrap(null, view.getCid(), PreferenceUtils.getVideoQuality(context)));

        ViewUtils.initTabLayout(this, binding.extra.tabLayout, binding.extra.viewPager,
                List.of(new MediaVideoInfoFragment(videoDetail.getData()), new MediaCommentsFragment(view.getAid())),
                "简介", "评论" + ValueUtils.generateCN(view.getStat().getReply()));
    }

    private void setPgcInfo(PgcDetail pgcDetail) {
        final MediaCommentsFragment commentsFragment;
        if (pgcDetail.getResult().getEpisodes().isEmpty()) {
            commentsFragment = new MediaCommentsFragment(null);
        } else {
            commentsFragment = new MediaCommentsFragment(String.valueOf(pgcDetail.getResult().getEpisodes().get(0).getAid()));
        }

        ViewUtils.initTabLayout(this, binding.extra.tabLayout, binding.extra.viewPager,
                List.of(new MediaPgcInfoFragment(pgcDetail.getResult()), commentsFragment),
                "简介", "评论");
    }

    /**
     * 设置视频
     *
     * @param videoResourceWrap VideoResourceWrap
     */
    private void setVideoResource(VideoResourceWrap videoResourceWrap) {
        if (videoResourceWrap == null) {
            binding.player.pause();
            binding.player.release();
            Toast.makeText(context, "没有选集可用来播放~~~", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isPgc) {
            new ApiHelper<>(httpApi.getPgcVideoStream(videoResourceWrap.getBvid(), videoResourceWrap.getCid(), videoResourceWrap.getQuality()))
                    .setOnResult(pgcStream -> setVideoStream(pgcStream.getResult().getQuality(), pgcStream.getResult().getSupportFormats(),
                            pgcStream.getResult().getDurl().get(0).getUrl())).doIt();
            videoPlayerModel.getVideoRecommend().setValue(videoResourceWrap.getBvid());
        } else {
            new ApiHelper<>(httpApi.getVideoStream(id, videoResourceWrap.getCid(), videoResourceWrap.getQuality()))
                    .setOnResult(videoStream -> setVideoStream(videoStream.getData().getQuality(), videoStream.getData().getSupportFormats(),
                            videoStream.getData().getDurl().get(0).getUrl())).doIt();
        }

    }

    private void setVideoStream(int nowQuality, List<VideoStream.Data.SupportFormat> supportFormatList, String url) {
        updateQualityList(nowQuality, supportFormatList);

        if (binding.player.isPlaying()) {
            binding.player.pause();
        }
        binding.player.release();

        binding.player.setUrl(url, ValueUtils.createPlayerVideoHeader(id, isPgc));
        binding.player.start();
    }

    /**
     * 更新对应视频的清晰度列表或更新已选画质
     *
     * @param nowQuality        当前已获取的质量
     * @param supportFormatList 可选的所有质量
     */
    private void updateQualityList(int nowQuality, List<VideoStream.Data.SupportFormat> supportFormatList) {
        List<VideoQuality> videoQualityList = new ArrayList<>();
        boolean loginStatus = PreferenceUtils.getLoginStatus(context);
        boolean vipStatus = PreferenceUtils.getVipStatus(context);

        supportFormatList.forEach(supportFormat -> {
            String extra = null;
            boolean isOrdinary = false;

            int i = ValueUtils.videoIdentify(supportFormat.getQuality());
            if (i == 3) {
                isOrdinary = true;
            } else if (i == 2 && !loginStatus) {
                extra = "登录";
            } else if (i == 1 && !vipStatus) {
                extra = "大会员";
            } else {
                isOrdinary = true;
            }

            Quality quality = Quality.valueOf("Q" + supportFormat.getQuality());
            videoQualityList.add(new VideoQuality(extra, isOrdinary, nowQuality == supportFormat.getQuality(),
                    quality, supportFormat.getQuality(), supportFormat.getNewDescription()));
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

        super.onDestroy();
    }
}