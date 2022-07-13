package com.leon.biuvideo;

import android.app.Application;

import com.leon.biuvideo.parser.PartitionParser;

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
    }
}
