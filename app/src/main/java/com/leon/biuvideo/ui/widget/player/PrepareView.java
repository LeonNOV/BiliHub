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

/**
 * @Author Leon
 * @Time 2022/08/12
 * @Desc 准备播放界面
 */
public class PrepareView extends FrameLayout implements IControlComponent {
    private ControlWrapper controlWrapper;

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
        ComponentPlayerPrepareBinding binding = ComponentPlayerPrepareBinding.bind(LayoutInflater.from(getContext()).inflate(R.layout.component_player_prepare, this, false));

        binding.back.setOnClickListener(v -> ActivityManager.BackPressed());
        binding.cover.setOnClickListener(v -> {
        });
        binding.play.setOnClickListener(v -> {
        });
        setOnClickListener(v -> controlWrapper.start());
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

//                mStartPlay.setVisibility(View.GONE);
//                mNetWarning.setVisibility(GONE);
//                mLoading.setVisibility(View.VISIBLE);
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
//                mLoading.setVisibility(View.GONE);
//                mNetWarning.setVisibility(GONE);
//                mStartPlay.setVisibility(View.VISIBLE);
//                mThumb.setVisibility(View.VISIBLE);
                break;
            case VideoView.STATE_START_ABORT:
                setVisibility(VISIBLE);
//                mNetWarning.setVisibility(VISIBLE);
//                mNetWarning.bringToFront();
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
