package com.leon.biuvideo.ui.widget.player;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leon.biuvideo.R;
import com.leon.biuvideo.beans.publicBeans.resources.live.LiveInfo;
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoQuality;
import com.leon.biuvideo.databinding.ComponentPlayerControllerBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import xyz.doikki.videoplayer.controller.GestureVideoController;
import xyz.doikki.videoplayer.controller.IControlComponent;
import xyz.doikki.videoplayer.player.VideoView;
import xyz.doikki.videoplayer.util.PlayerUtils;

/**
 * @Author Leon
 * @Time 2022/08/12
 * @Desc 播放视图控制器
 */
public class PlayerController extends GestureVideoController {
    private ComponentPlayerControllerBinding binding;
    private TopBarView topBarView;
    private BottomControlView bottomControlView;

    private OnPlayStateChangedListener onPlayStateChangedListener;

    public PlayerController(@NonNull Context context) {
        super(context);
    }

    public PlayerController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayerController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.component_player_controller;
    }

    @Override
    protected void initView() {
        super.initView();

        binding = ComponentPlayerControllerBinding.bind(this);
        binding.lock.setOnClickListener(v -> mControlWrapper.toggleLockState());
    }

    public void addDefaultControlComponent (LiveInfo liveInfo) {
        addControlComponent(new LiveView(getContext(), liveInfo));

        topBarView = new TopBarView(getContext(), true);
        bottomControlView = new BottomControlView(getContext(), false);

        addControlComponent(topBarView, bottomControlView, new PrepareView(getContext()));
        addControlComponent(new CompleteView(getContext()), new ErrorView(getContext()), new GestureView(getContext()));
    }

    public void addDefaultControlComponent () {
        topBarView = new TopBarView(getContext(), false);
        bottomControlView = new BottomControlView(getContext(), false);

        addControlComponent(topBarView, bottomControlView, new PrepareView(getContext()));
        addControlComponent(new CompleteView(getContext()), new ErrorView(getContext()), new GestureView(getContext()));
    }

    @Override
    protected void onLockStateChanged(boolean isLocked) {
        binding.lock.setSelected(isLocked);
    }

    @Override
    protected void onVisibilityChanged(boolean isVisible, Animation anim) {
        if (mControlWrapper.isFullScreen()) {
            if (isVisible) {
                if (binding.lock.getVisibility() == GONE) {
                    binding.lock.setVisibility(VISIBLE);
                    if (anim != null) {
                        binding.lock.startAnimation(anim);
                    }
                }
            } else {
                binding.lock.setVisibility(GONE);
                if (anim != null) {
                    binding.lock.startAnimation(anim);
                }
            }
        }
    }

    @Override
    protected void onPlayStateChanged(int playState) {
        super.onPlayStateChanged(playState);
        if (onPlayStateChangedListener != null) {
            onPlayStateChangedListener.onPlayStateChanged(playState);
        }
        switch (playState) {
            //调用release方法会回到此状态
            case VideoView.STATE_IDLE:
                binding.lock.setSelected(false);
                binding.loading.setVisibility(GONE);
                break;
            case VideoView.STATE_PLAYING:
            case VideoView.STATE_PAUSED:
            case VideoView.STATE_PREPARED:
            case VideoView.STATE_ERROR:
            case VideoView.STATE_BUFFERED:
                binding.loading.setVisibility(GONE);
                break;
            case VideoView.STATE_PREPARING:
            case VideoView.STATE_BUFFERING:
                binding.loading.setVisibility(VISIBLE);
                break;
            case VideoView.STATE_PLAYBACK_COMPLETED:
                binding.loading.setVisibility(GONE);
                binding.lock.setVisibility(GONE);
                binding.lock.setSelected(false);
            case VideoView.STATE_START_ABORT:
                binding.loading.setVisibility(GONE);
                binding.lock.setVisibility(GONE);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPlayerStateChanged(int playerState) {
        super.onPlayerStateChanged(playerState);
        switch (playerState) {
            case VideoView.PLAYER_NORMAL:
                setLayoutParams(new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                binding.lock.setVisibility(GONE);

                // 退出全屏后，隐藏状态栏
                if (mActivity != null) {
                    mActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
                break;
            case VideoView.PLAYER_FULL_SCREEN:
                binding.lock.setVisibility(VISIBLE);
                break;
            default:
                break;
        }

        if (mActivity != null && hasCutout()) {
            int orientation = mActivity.getRequestedOrientation();
            int dp24 = PlayerUtils.dp2px(getContext(), 24);
            int cutoutHeight = getCutoutHeight();
            if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                FrameLayout.LayoutParams layoutParams = (LayoutParams) binding.lock.getLayoutParams();
                layoutParams.setMargins(dp24, 0, dp24, 0);
            } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                FrameLayout.LayoutParams layoutParams = (LayoutParams) binding.lock.getLayoutParams();
                layoutParams.setMargins(dp24 + cutoutHeight, 0, dp24 + cutoutHeight, 0);
            } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
                FrameLayout.LayoutParams layoutParams = (LayoutParams) binding.lock.getLayoutParams();
                layoutParams.setMargins(dp24, 0, dp24, 0);
            }
        }
    }

    @Override
    public boolean onBackPressed() {
        if (isLocked()) {
            show();
            return true;
        }

        if (mControlWrapper.isFullScreen()) {
            return stopFullScreen();
        }

        return super.onBackPressed();
    }

    public void setOnPlayStateChangedListener(OnPlayStateChangedListener onPlayStateChangedListener) {
        this.onPlayStateChangedListener = onPlayStateChangedListener;
    }

    public interface OnPlayStateChangedListener {
        void onPlayStateChanged(int playState);
    }

    public static interface OnSelectedListener<T> {
        void onSelected(T t);
    }
}
