package com.leon.bilihub.ui.widget.player;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.leon.bilihub.R;
import com.leon.bilihub.base.baseActivity.ActivityManager;
import com.leon.bilihub.databinding.ComponentPlayerTopBarBinding;
import com.leon.bilihub.model.VideoPlayerModel;
import com.leon.bilihub.utils.ViewUtils;

import xyz.doikki.videoplayer.controller.ControlWrapper;
import xyz.doikki.videoplayer.controller.IControlComponent;
import xyz.doikki.videoplayer.player.BaseVideoView;
import xyz.doikki.videoplayer.player.VideoView;
import xyz.doikki.videoplayer.util.PlayerUtils;

/**
 * @Author Leon
 * @Time 2022/08/12
 * @Desc 顶部栏
 */
public class TopBarView extends FrameLayout implements IControlComponent {
    private boolean isLive;

    private ControlWrapper controlWrapper;
    private ComponentPlayerTopBarBinding binding;

    /**
     * 是否注册BatteryReceiver
     */
    private boolean isRegister;
    private BatteryReceiver batteryReceiver;

    private VideoPlayerModel playerModel;
    private Observer<String> videoTitleObserver;

    public TopBarView(@NonNull Context context, boolean isLive) {
        super(context);
        this.isLive = isLive;

        init();
    }

    public TopBarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public TopBarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    protected void init() {
        playerModel = new ViewModelProvider(ViewUtils.scanForActivity(getContext())).get(VideoPlayerModel.class);

        videoTitleObserver = title -> binding.title.setText(title);
        playerModel.getVideoTitleDisplay().observeForever(videoTitleObserver);

        setVisibility(GONE);
        binding = ComponentPlayerTopBarBinding.bind(LayoutInflater.from(getContext()).inflate(R.layout.component_player_top_bar, this, true));

        binding.back.setOnClickListener(v -> {
            if (controlWrapper.isFullScreen()) {
                PlayerUtils.scanForActivity(getContext()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
//                PlayerUtils.scanForActivity(getContext()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                controlWrapper.stopFullScreen();
            } else {
                ActivityManager.BackPressed();
            }
        });

        binding.more.setOnClickListener(v -> Toast.makeText(getContext(), "more", Toast.LENGTH_SHORT).show());

        batteryReceiver = new BatteryReceiver(binding.battery);
    }

    public void setTitle(String title) {
        binding.title.setText(title);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (isRegister) {
            getContext().unregisterReceiver(batteryReceiver);
            isRegister = false;
        }

        playerModel.getVideoTitleDisplay().removeObserver(videoTitleObserver);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isRegister) {
            getContext().registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            isRegister = true;
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
                binding.time.setText(PlayerUtils.getCurrentSystemTime());
                setVisibility(VISIBLE);

                if (anim != null) {
                    startAnimation(anim);
                }

                // 在全屏时显示标题、时间等信息，竖屏则不显示
                binding.top.setVisibility(controlWrapper.isFullScreen() ? VISIBLE : GONE);
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
            case VideoView.STATE_START_ABORT:
            case VideoView.STATE_PREPARING:
            case VideoView.STATE_PREPARED:
            case VideoView.STATE_ERROR:
            case BaseVideoView.STATE_PLAYING:
            case VideoView.STATE_PLAYBACK_COMPLETED:
                setVisibility(GONE);
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

        if (playerState == VideoView.PLAYER_FULL_SCREEN && !controlWrapper.isLocked()) {
            binding.top.setVisibility(VISIBLE);
        } else {
            binding.top.setVisibility(GONE);
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

    }

    @Override
    public void onLockStateChanged(boolean isLocked) {
        if (isLocked) {
            setVisibility(GONE);
        } else {
            setVisibility(VISIBLE);
            binding.time.setText(PlayerUtils.getCurrentSystemTime());
        }
    }

    private static class BatteryReceiver extends BroadcastReceiver {
        private final AppCompatImageView batteryImageView;

        public BatteryReceiver(AppCompatImageView batteryImageView) {
            this.batteryImageView = batteryImageView;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();

            if (extras == null) {
                return;
            }

            int current = extras.getInt("level");
            int total = extras.getInt("scale");
            int percent = current * 100 / total;
            batteryImageView.getDrawable().setLevel(percent);
        }
    }
}
