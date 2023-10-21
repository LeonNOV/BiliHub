package com.leon.bilihub.ui.widget.player;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leon.bilihub.beans.publicBeans.resources.video.VideoSubtitle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import xyz.doikki.videoplayer.controller.ControlWrapper;
import xyz.doikki.videoplayer.controller.IControlComponent;
import xyz.doikki.videoplayer.player.VideoView;

/**
 * @Author Leon
 * @Time 2023/05/06
 * @Desc
 */
public class SubtitleView extends FrameLayout implements IControlComponent {
    private ControlWrapper controlWrapper;

    private VideoSubtitle videoSubtitle;
    private Iterator<VideoSubtitle.Body> bodyIterator;

    public SubtitleView(@NonNull Context context) {
        super(context);

        init();
    }

    public SubtitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public SubtitleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        bodyIterator = videoSubtitle.getBody().iterator();

        setVisibility(GONE);
        setClickable(false);
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
            setVisibility(INVISIBLE);
        } else {
            setVisibility(VISIBLE);
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
