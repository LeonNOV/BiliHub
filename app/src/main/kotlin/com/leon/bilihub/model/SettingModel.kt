package com.leon.bilihub.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @Author Leon
 * @Time 2022/08/25
 * @Desc
 */
class SettingModel : ViewModel() {
    /**
     * 已选默认视频画质
     */
    val videoQualityDisplay: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    /**
     * 已选默认直播画质
     */
    val liveQualityDisplay: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    /**
     * 已选样式
     */
    val recommendStyle: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
}