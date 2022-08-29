package com.leon.bilihub.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leon.bilihub.wraps.AudioProgressWrap

/**
 * @Author Leon
 * @Time 2022/08/29
 * @Desc
 */
class AudioProgressModel : ViewModel() {
    /**
     * 音频 播放进度/缓冲进度/长度
     */
    val audioProgress: MutableLiveData<AudioProgressWrap> by lazy {
        MutableLiveData<AudioProgressWrap>()
    }

}