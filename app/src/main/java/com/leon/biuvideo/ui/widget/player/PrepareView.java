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
import com.leon.biuvideo.base.baseActivity.ActivityManager;
import com.leon.biuvideo.databinding.ComponentPlayerPrepareBinding;

import xyz.doikki.videoplayer.controller.ControlWrapper;
import xyz.doikki.videoplayer.controller.IControlComponent;
import xyz.doikki.videoplayer.player.VideoView;
import xyz.doikki.videoplayer.player.VideoViewManager;

/**
 * @Author Leon
 * @Time 2022/08/12
 * @Desc 准备播放界面
 */
public class PrepareView extends FrameLayout implements IControlComponent {
    private ControlWrapper controlWrapper;
    private ComponentPlayerPrepareBinding binding;

    public PrepareView(@NonNull Context context) {
        super(context);

        init();
    }

    public PrepareView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public PrepareView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        binding = ComponentPlayerPrepareBinding.bind(LayoutInflater.from(getContext()).inflate(R.layout.component_player_prepare, this, true));

        binding.play.setOnClickListener(v -> controlWrapper.start());
        binding.continuePlay.setOnClickListener(v -> {
            binding.netWarningContainer.setVisibility(GONE);
            VideoViewManager.instance().setPlayOnMobileNetwork(true);
            controlWrapper.start();
        });
//        setOnClickListener(v -> controlWrapper.start());
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
        switch (playState) {
            case VideoView.STATE_PREPARING:
                bringToFront();
                setVisibility(VISIBLE);

                binding.play.setVisibility(GONE);
                binding.netWarningContainer.setVisibility(GONE);
                break;
            case VideoView.STATE_PLAYING:
            case VideoView.STATE_PAUSED:
            case VideoView.STATE_ERROR:
            case VideoView.STATE_BUFFERING:
            case VideoView.STATE_BUFFERED:
            case VideoView.STATE_PLAYBACK_COMPLETED:
                setVisibility(GONE);
                break;
            case VideoView.STATE_IDLE:
                setVisibility(VISIBLE);
                bringToFront();

                binding.play.setVisibility(VISIBLE);
                binding.netWarningContainer.setVisibility(GONE);
                break;
            case VideoView.STATE_START_ABORT:
                setVisibility(VISIBLE);
                binding.netWarningContainer.setVisibility(VISIBLE);
                binding.netWarningContainer.bringToFront();
                break;
            default:
                break;
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
