package com.leon.biuvideo.ui.widget.player;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leon.biuvideo.R;
import com.leon.biuvideo.databinding.ComponentPlayerCompleteBinding;

import xyz.doikki.videoplayer.controller.ControlWrapper;
import xyz.doikki.videoplayer.controller.IControlComponent;
import xyz.doikki.videoplayer.player.VideoView;
import xyz.doikki.videoplayer.util.PlayerUtils;

/**
 * @Author Leon
 * @Time 2022/08/12
 * @Desc 播放完成界面
 */
public class CompleteView extends FrameLayout implements IControlComponent {
    private ControlWrapper controlWrapper;

    public CompleteView(@NonNull Context context) {
        super(context);

        init();
    }

    public CompleteView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public CompleteView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setVisibility(GONE);
        setClickable(true);

        ComponentPlayerCompleteBinding binding = ComponentPlayerCompleteBinding.bind(LayoutInflater.from(getContext()).inflate(R.layout.component_player_complete, this, true));
        binding.replay.setOnClickListener(v -> controlWrapper.replay(true));
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
        // 如果此视频已播放完毕,就显示当前界面
        if (playState == VideoView.STATE_PLAYBACK_COMPLETED) {
            setVisibility(VISIBLE);

            // 如果为全屏，则退出全屏并旋转屏幕至竖屏
            if (controlWrapper.isFullScreen()) {
                controlWrapper.toggleFullScreen(PlayerUtils.scanForActivity(getContext()));
            }

        } else {
            setVisibility(GONE);
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
}
