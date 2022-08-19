package com.leon.biuvideo.wraps

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoQuality
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoSpeed

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
class VideoEpisodeWrap : ViewModel() {
    /**
     * resource 即为视频分集，切换时不会影响界面展示数据
     */
    val resource: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val title: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val quality: MutableLiveData<VideoQuality> by lazy {
        MutableLiveData<VideoQuality>()
    }

    val speed: MutableLiveData<VideoSpeed> by lazy {
        MutableLiveData<VideoSpeed>()
    }
}