package com.leon.biuvideo.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoQuality
import com.leon.biuvideo.http.Quality

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
class VideoEpisodeModel : ViewModel() {
    /**
     * resource 即为视频分集，切换时不会影响界面展示数据
     */
    val resource: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val title: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val quality: MutableLiveData<Quality> by lazy {
        MutableLiveData<Quality>()
    }

    val qualityDisplay: MutableLiveData<Quality> by lazy {
        MutableLiveData<Quality>()
    }

    val qualityList: MutableLiveData<List<VideoQuality>> by lazy {
        MutableLiveData<List<VideoQuality>>()
    }

    val speed: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }
}