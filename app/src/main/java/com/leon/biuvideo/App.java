package com.leon.biuvideo;

import android.app.Application;
import android.content.Context;

import com.leon.biuvideo.parser.PartitionParser;

/**
 * @Author Leon
 * @Time 2022/06/23
 * @Desc
 */
public class App extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        new PartitionParser().initMemData(getApplicationContext());
    }
}
