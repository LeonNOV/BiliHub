package com.leon.biuvideo;

import android.app.Application;

import com.leon.biuvideo.parser.PartitionParser;

import xyz.doikki.videoplayer.exo.ExoMediaPlayerFactory;
import xyz.doikki.videoplayer.ijk.IjkPlayerFactory;
import xyz.doikki.videoplayer.player.VideoViewConfig;
import xyz.doikki.videoplayer.player.VideoViewManager;

/**
 * @Author Leon
 * @Time 2022/06/23
 * @Desc
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        new PartitionParser().initMemData(getApplicationContext());

        // 默认使用IjkPlayer解码
        VideoViewManager.setConfig(VideoViewConfig.newBuilder().setPlayerFactory(IjkPlayerFactory.create()).build());
    }
}
