package com.leon.biuvideo.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leon.biuvideo.beans.publicBeans.resources.live.LiveInfo
import com.leon.biuvideo.beans.publicBeans.resources.live.LiveStream
import com.leon.biuvideo.wraps.LiveRoadWrap

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
}