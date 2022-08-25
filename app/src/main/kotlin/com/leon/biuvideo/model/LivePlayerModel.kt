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
class LivePlayerModel : ViewModel() {
    /**
     * 当前已选画质
     */
    val liveQuality: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    /**
     * 可选画质列表
     */
    val liveQualityList: MutableLiveData<List<LiveStream.Data.QualityDescription>> by lazy {
        MutableLiveData<List<LiveStream.Data.QualityDescription>>()
    }

    /**
     * 当前已选路线
     */
    val liveRoad: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    /**
     * 直播线路
     */
    val liveRoadList: MutableLiveData<List<LiveStream.Data.Durl>> by lazy {
        MutableLiveData<List<LiveStream.Data.Durl>>()
    }
}