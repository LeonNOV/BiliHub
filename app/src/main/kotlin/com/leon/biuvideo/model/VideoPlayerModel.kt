package com.leon.biuvideo.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoQuality
import com.leon.biuvideo.http.Quality
import com.leon.biuvideo.wraps.VideoResourceWrap

/**
 * @Author Leon
 * @Time 2022/08/22
 * @Desc
 */
class VideoPlayerModel : ViewModel() {
    /**
     * cid
     */
    val videoResource: MutableLiveData<VideoResourceWrap> by lazy {
        MutableLiveData<VideoResourceWrap>()
    }

    /**
     * 设置视频评论
     */
    val videoRecommend: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    /**
     * 设置PGC season
     */
    val videoPgcSeason: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    /**
     * 设置PGC episode
     */
    val videoPgcEpisode: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    /**
     * 播放器所显示标题
     */
    val videoTitleDisplay: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    /**
     * 播放器所显示清晰度
     */
    val videoQuality: MutableLiveData<Quality> by lazy {
        MutableLiveData<Quality>()
    }

    /**
     * 播放器所显示清晰度
     */
    val videoQualityListDisplay: MutableLiveData<List<VideoQuality>> by lazy {
        MutableLiveData<List<VideoQuality>>()
    }

    /**
     * 播放器所显示清晰度
     * 暂时不加入
     */
    val videoQualityDisplay: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    /**
     * 视频播放速度
     */
    val videoSpeed: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }

    /**
     * 播放器所显示速度
     * 暂时不加入
     */
    val videoSpeedDisplay: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}