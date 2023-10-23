package com.leon.bilihub;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatDelegate;

import com.leon.bilihub.parser.PartitionParser;
import com.leon.bilihub.utils.PreferenceUtils;
import com.umeng.commonsdk.UMConfigure;

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

        boolean darkModeMode = PreferenceUtils.getDarkModeMode(getApplicationContext());
        AppCompatDelegate.setDefaultNightMode(darkModeMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);

        new PartitionParser().initMemData(getApplicationContext());

        // 默认使用IjkPlayer解码
        VideoViewManager.setConfig(VideoViewConfig.newBuilder().setPlayerFactory(IjkPlayerFactory.create()).build());

        /**
         * 初始化common库
         * 参数1:上下文，必须的参数，不能为空
         * 参数2:友盟 app key，非必须参数，如果Manifest文件中已配置app key，该参数可以传空，则使用Manifest中配置的app key，否则该参数必须传入
         * 参数3:友盟 channel，非必须参数，如果Manifest文件中已配置channel，该参数可以传空，则使用Manifest中配置的channel，否则该参数必须传入，channel命名请详见channel渠道命名规范
         * 参数4:设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机
         * 参数5:Push推送业务的secret，需要集成Push功能时必须传入Push的secret，否则传空
         */
        try {
            ApplicationInfo info = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            UMConfigure.preInit(this, "630ee3c888ccdf4b7e1b2b5f", info.metaData.getString("UMENG_CHANNEL"));
            UMConfigure.init(this, "630ee3c888ccdf4b7e1b2b5f", info.metaData.getString("UMENG_CHANNEL"), UMConfigure.DEVICE_TYPE_PHONE, "");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
