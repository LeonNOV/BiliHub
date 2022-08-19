package com.leon.biuvideo.ui.widget.player;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;

import com.leon.biuvideo.R;
import com.leon.biuvideo.base.baseActivity.ActivityManager;
import com.leon.biuvideo.beans.publicBeans.resources.live.LiveInfo;
import com.leon.biuvideo.databinding.ComponentPlayerLiveBinding;
import com.leon.biuvideo.ui.activities.publicActivities.UserActivity;
import com.leon.biuvideo.utils.ViewUtils;

import java.util.Map;

import xyz.doikki.videoplayer.controller.ControlWrapper;
import xyz.doikki.videoplayer.controller.IControlComponent;
import xyz.doikki.videoplayer.player.VideoView;
import xyz.doikki.videoplayer.util.PlayerUtils;

/**
 * @Author Leon
 * @Time 2022/08/15
 * @Desc
 */
public class LiveView extends FrameLayout implements IControlComponent {
    private LiveInfo liveInfo;

    private ControlWrapper controlWrapper;
    private ComponentPlayerLiveBinding binding;

    public LiveView(@NonNull Context context, LiveInfo liveInfo) {
        super(context);
        this.liveInfo = liveInfo;

        init();
    }

    public LiveView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public LiveView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    public void init() {
        binding = ComponentPlayerLiveBinding.bind(LayoutInflater.from(getContext()).inflate(R.layout.component_player_live, this, true));
        binding.close.setOnClickListener(view -> ActivityManager.BackPressed());
        binding.play.setOnClickListener(view -> controlWrapper.togglePlay());
        binding.refresh.setOnClickListener(view -> controlWrapper.replay(true));
        binding.fullScreen.setOnClickListener(view -> controlWrapper.toggleFullScreen(PlayerUtils.scanForActivity(getContext())));
        binding.face.setOnClickListener(view -> ActivityManager.startActivity(getContext(), UserActivity.class, Map.of(UserActivity.PARAM, liveInfo.getData().getRoomInfo().getUid())));

        ViewUtils.setImg(getContext(), binding.face, liveInfo.getData().getAnchorInfo().getBaseInfo().getFace());
        binding.name.setText(liveInfo.getData().getAnchorInfo().getBaseInfo().getUname());
        binding.name.setCompoundDrawablesRelative(AppCompatResources.getDrawable(getContext(), liveInfo.getData().getWatchedShow().getSwitch() ? R.drawable.live_icon_view : R.drawable.live_icon_popular), null, null, null);
        binding.power.setText(liveInfo.getData().getWatchedShow().getTextLarge());
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
            case VideoView.STATE_PLAYING:
                binding.play.setSelected(true);
                break;
            case VideoView.STATE_PAUSED:
                binding.play.setSelected(false);
                break;
            case VideoView.STATE_BUFFERING:
            case VideoView.STATE_BUFFERED:
                binding.play.setSelected(controlWrapper.isPlaying());
                break;
            default:
                break;
        }

    }

    @Override
    public void onPlayerStateChanged(int playerState) {
        setVisibility(playerState == VideoView.PLAYER_FULL_SCREEN ? GONE : VISIBLE);
    }

    @Override
    public void setProgress(int duration, int position) {

    }

    @Override
    public void onLockStateChanged(boolean isLocked) {

    }
}
