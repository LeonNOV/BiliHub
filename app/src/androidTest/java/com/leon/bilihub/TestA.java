package com.leon.bilihub;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @Author Leon
 * @Time 2022/06/22
 * @Desc
 */
@RunWith(AndroidJUnit4.class)
public class TestA {
    private static final String TAG = "WwwW";

    @Test
    public void aaa() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }
}
