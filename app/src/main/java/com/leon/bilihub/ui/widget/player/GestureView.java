package com.leon.bilihub.ui.widget.player;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leon.bilihub.R;
import com.leon.bilihub.databinding.ComponentPlayerGestureBinding;

import xyz.doikki.videoplayer.controller.ControlWrapper;
import xyz.doikki.videoplayer.controller.IGestureComponent;
import xyz.doikki.videoplayer.player.VideoView;
import xyz.doikki.videoplayer.util.PlayerUtils;

/**
 * @Author Leon
 * @Time 2022/08/12
 * @Desc 触摸界面
 */
public class GestureView extends FrameLayout implements IGestureComponent {
    private ControlWrapper controlWrapper;
    private ComponentPlayerGestureBinding binding;

    private OnDraggingListener onDraggingListener;

    /**
     * 是否为第一次出现VideoView.STATE_PLAYING
     */
    private boolean isFirstPlayingState = true;

    public GestureView(@NonNull Context context) {
        super(context);

        init();
    }

    public GestureView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public GestureView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setVisibility(GONE);
        binding = ComponentPlayerGestureBinding.bind(LayoutInflater.from(getContext()).inflate(R.layout.component_player_gesture, this, true));
    }

    @Override
    public void attach(@NonNull ControlWrapper controlWrapper) {
        this.controlWrapper = controlWrapper;
    }

    @Nullable
    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onVisibilityChanged(boolean isVisible, Animation anim) {

    }

    @Override
    public void onPlayStateChanged(int playState) {
        if (playState == VideoView.STATE_IDLE
                || playState == VideoView.STATE_START_ABORT
                || playState == VideoView.STATE_PREPARING
                || playState == VideoView.STATE_PREPARED
                || playState == VideoView.STATE_ERROR
                || playState == VideoView.STATE_PLAYBACK_COMPLETED) {
            setVisibility(GONE);
        } else {
            if (playState == VideoView.STATE_PLAYING && isFirstPlayingState) {
                setVisibility(GONE);
                isFirstPlayingState = false;
            } else {
                setVisibility(VISIBLE);
            }
        }
    }

    @Override
    public void onPlayerStateChanged(int playerState) {

    }

    @Override
    public void setProgress(int duration, int position) {

    }

    @Override
    public void onLockStateChanged(boolean isLocked) {

    }

    /**
     * 开始滑动
     */
    @Override
    public void onStartSlide() {
        // 显示控制窗口
        controlWrapper.hide();

        binding.getRoot().setVisibility(VISIBLE);
        binding.getRoot().setAlpha(1f);
    }

    @Override
    public void onStopSlide() {
        binding.getRoot()
                .animate()
                .alpha(0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        binding.getRoot().setVisibility(GONE);
                    }
                })
                .start();
    }

    @Override
    public void onPositionChange(int slidePosition, int currentPosition, int duration) {
        binding.progress.setVisibility(GONE);

        if (slidePosition > currentPosition) {
            binding.icon.setImageResource(R.drawable.video_fast_forward);
        } else {
            binding.icon.setImageResource(R.drawable.video_fast_retreat);
        }

        binding.percent.setText(String.format("%s/%s", PlayerUtils.stringForTime(slidePosition), PlayerUtils.stringForTime(duration)));

        if (onDraggingListener != null) {
            onDraggingListener.onDragging(duration, slidePosition);
        }
    }

    @Override
    public void onBrightnessChange(int percent) {
        binding.progress.setVisibility(VISIBLE);
        binding.progress.setProgress(percent);

        binding.icon.setImageResource(R.drawable.video_brightness);
        binding.percent.setText("%" + percent);
    }

    @Override
    public void onVolumeChange(int percent) {
        binding.progress.setVisibility(VISIBLE);

        if (percent <= 0) {
            binding.icon.setImageResource(R.drawable.video_volume_off);
        } else {
            binding.icon.setImageResource(R.drawable.video_volume);
        }

        binding.percent.setText(percent + "%");
        binding.progress.setProgress(percent);
    }

    public void setOnDraggingListener(OnDraggingListener onDraggingListener) {
        this.onDraggingListener = onDraggingListener;
    }

    public interface OnDraggingListener {
        /**
         * 手势快进/快退
         *
         * @param duration      总进度
         * @param slidePosition 快进进度
         */
        void onDragging(int duration, int slidePosition);
    }
}
