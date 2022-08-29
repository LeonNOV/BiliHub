package com.leon.bilihub.ui.widget.player;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leon.bilihub.R;
import com.leon.bilihub.databinding.ComponentPlayerBottomControlBinding;
import com.leon.bilihub.ui.dialogs.LiveQualityDialog;
import com.leon.bilihub.ui.dialogs.QualityDialog;
import com.leon.bilihub.ui.dialogs.RoadDialog;
import com.leon.bilihub.ui.dialogs.SpeedDialog;

import xyz.doikki.videoplayer.controller.ControlWrapper;
import xyz.doikki.videoplayer.controller.IControlComponent;
import xyz.doikki.videoplayer.player.VideoView;
import xyz.doikki.videoplayer.util.PlayerUtils;

/**
 * @Author Leon
 * @Time 2022/08/12
 * @Desc 底部控制栏
 */
public class BottomControlView extends FrameLayout implements IControlComponent, SeekBar.OnSeekBarChangeListener {
    private boolean isLive;

    private ControlWrapper controlWrapper;
    private ComponentPlayerBottomControlBinding binding;

    private boolean isDragging;

    public BottomControlView(@NonNull Context context, boolean isLive) {
        super(context);

        this.isLive = isLive;
        init();
    }

    public BottomControlView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public BottomControlView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setVisibility(GONE);
        binding = ComponentPlayerBottomControlBinding.bind(LayoutInflater.from(getContext()).inflate(R.layout.component_player_bottom_control, this, true));

        binding.seekBar.setOnSeekBarChangeListener(this);
        binding.fullScreen.setOnClickListener(v -> controlWrapper.toggleFullScreen(PlayerUtils.scanForActivity(getContext())));
        binding.play.setOnClickListener(v -> controlWrapper.togglePlay());
        binding.danmakuControl.setOnClickListener(v -> Toast.makeText(getContext(), "开发中…", Toast.LENGTH_SHORT).show());
        binding.quality.setOnClickListener(v -> {
            if (isLive) {
                new LiveQualityDialog(getContext()).show();
            } else {
                new QualityDialog(getContext()).show();
            }
        });

        if (isLive) {
            binding.speed.setVisibility(GONE);
            binding.controlContainer.setVisibility(GONE);
            binding.road.setVisibility(VISIBLE);
            binding.road.setOnClickListener(view -> new RoadDialog(getContext()).show());
            binding.playLive.setVisibility(VISIBLE);
        } else {
            binding.speed.setOnClickListener(v -> new SpeedDialog(getContext()).show());
        }
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
        if (isLive && !controlWrapper.isFullScreen()) {
            return;
        }

        if (isVisible) {
            if (getVisibility() == GONE) {

                setVisibility(VISIBLE);
                if (anim != null) {
                    startAnimation(anim);
                }
            }
        } else {
            if (getVisibility() == VISIBLE) {

                setVisibility(GONE);
                if (anim != null) {
                    startAnimation(anim);
                }
            }
        }
    }

    @Override
    public void onPlayStateChanged(int playState) {
        switch (playState) {
            case VideoView.STATE_IDLE:
            case VideoView.STATE_PLAYBACK_COMPLETED:
                setVisibility(GONE);

                binding.seekBar.setProgress(0);
                binding.seekBar.setSecondaryProgress(0);
                break;
            case VideoView.STATE_START_ABORT:
            case VideoView.STATE_PREPARING:
            case VideoView.STATE_PREPARED:
            case VideoView.STATE_ERROR:
                setVisibility(GONE);
                break;
            case VideoView.STATE_PLAYING:
                binding.play.setSelected(true);
                binding.playLive.setSelected(true);
                setVisibility(GONE);

                controlWrapper.startProgress();
                break;
            case VideoView.STATE_PAUSED:
                binding.play.setSelected(false);
                binding.playLive.setSelected(false);
                break;
            case VideoView.STATE_BUFFERING:
            case VideoView.STATE_BUFFERED:
                binding.play.setSelected(controlWrapper.isPlaying());
                binding.playLive.setSelected(controlWrapper.isPlaying());
                break;
            default:
                break;
        }
    }

    @Override
    public void onPlayerStateChanged(int playerState) {
        if (isLive && playerState != VideoView.PLAYER_FULL_SCREEN) {
            setVisibility(GONE);
            return;
        }

        // 如果处于未全屏状态下则隐藏setting部分,并显示窗口放大按钮
        if (playerState == VideoView.PLAYER_NORMAL) {
            binding.settingContainer.setVisibility(GONE);
            binding.fullScreen.setVisibility(VISIBLE);
        } else {
            binding.settingContainer.setVisibility(VISIBLE);
            binding.fullScreen.setVisibility(GONE);
        }

        if (controlWrapper.hasCutout()) {
            int orientation = PlayerUtils.scanForActivity(getContext()).getRequestedOrientation();
            int cutoutHeight = controlWrapper.getCutoutHeight();

            if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                binding.container.setPadding(0, 0, 0, 0);
            } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                binding.container.setPadding(cutoutHeight, 0, 0, 0);
            } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
                binding.container.setPadding(0, 0, cutoutHeight, 0);
            }
        }
    }

    @Override
    public void setProgress(int duration, int position) {
        if (isDragging) {
            return;
        }

        if (duration > 0) {
            binding.seekBar.setEnabled(true);

            int videoPos = (int) (position * 1.0 / duration * binding.seekBar.getMax());

            binding.seekBar.setProgress(videoPos);
        } else {
            binding.seekBar.setEnabled(false);
        }

        int percent = controlWrapper.getBufferedPercentage();
        if (percent >= 95) {
            binding.seekBar.setSecondaryProgress(binding.seekBar.getMax());
        } else {
            binding.seekBar.setSecondaryProgress(percent * 10);
        }

        binding.duration.setText(PlayerUtils.stringForTime(duration));
        binding.progress.setText(PlayerUtils.stringForTime(position));
    }

    @Override
    public void onLockStateChanged(boolean isLocked) {
        onVisibilityChanged(!isLocked, null);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (!fromUser) {
            return;
        }

        long duration = controlWrapper.getDuration();
        long newPosition = (duration * progress) / binding.seekBar.getMax();

        binding.progress.setText(PlayerUtils.stringForTime((int) newPosition));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        isDragging = true;

        controlWrapper.stopProgress();
        controlWrapper.stopFadeOut();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        long videoDuration = controlWrapper.getDuration();
        long videoNewPosition = (videoDuration * seekBar.getProgress()) / binding.seekBar.getMax();

        controlWrapper.seekTo((int) videoNewPosition);

        isDragging = false;
        controlWrapper.startProgress();
        controlWrapper.startFadeOut();
    }
}
