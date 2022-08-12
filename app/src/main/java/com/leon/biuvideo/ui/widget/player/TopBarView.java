package com.leon.biuvideo.ui.widget.player;

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

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseActivity.ActivityManager;
import com.leon.biuvideo.databinding.ComponentPlayerTopBarBinding;

import xyz.doikki.videoplayer.controller.ControlWrapper;
import xyz.doikki.videoplayer.controller.IControlComponent;
import xyz.doikki.videoplayer.player.VideoView;
import xyz.doikki.videoplayer.util.PlayerUtils;

/**
 * @Author Leon
 * @Time 2022/08/12
 * @Desc 顶部栏
 */
public class TopBarView extends FrameLayout implements IControlComponent {
    private ControlWrapper controlWrapper;
    private ComponentPlayerTopBarBinding binding;

    /**
     * 是否注册BatteryReceiver
     */
    private boolean isRegister;
    private BatteryReceiver batteryReceiver;

    public TopBarView(@NonNull Context context) {
        super(context);

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
        setVisibility(GONE);
        binding = ComponentPlayerTopBarBinding.bind(LayoutInflater.from(getContext()).inflate(R.layout.component_player_top_bar, this, false));

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

    public TopBarView setTitle(String title) {
        binding.title.setText(title);

        return this;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (isRegister) {
            getContext().unregisterReceiver(batteryReceiver);
            isRegister = false;
        }
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
            case VideoView.STATE_PLAYBACK_COMPLETED:
                setVisibility(GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPlayerStateChanged(int playerState) {
        if (playerState == VideoView.PLAYER_FULL_SCREEN) {
            if (controlWrapper.isShowing() && !controlWrapper.isLocked()) {
                setVisibility(VISIBLE);
                binding.top.setVisibility(VISIBLE);
                binding.time.setText(PlayerUtils.getCurrentSystemTime());
            }
        } else {
            binding.top.setVisibility(GONE);
        }

        if (controlWrapper.hasCutout()) {
            int orientation = PlayerUtils.scanForActivity(getContext()).getRequestedOrientation();
            int cutoutHeight = controlWrapper.getCutoutHeight();

            if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                this.setPadding(0, 0, 0, 0);
            } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                this.setPadding(cutoutHeight, 0, 0, 0);
            } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
                this.setPadding(0, 0, cutoutHeight, 0);
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

            // 当前电量
            int current = extras.getInt("level");

            // 总电量
            int total = extras.getInt("scale");

            // 获取电量比例
            int percent = current * 100 / total;

            // 根据电量百分比设置Drawable资源
            batteryImageView.getDrawable().setLevel(percent);
        }
    }
}
