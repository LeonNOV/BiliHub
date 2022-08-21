package com.leon.biuvideo.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leon.biuvideo.beans.publicBeans.resources.video.VideoQuality
import com.leon.biuvideo.http.Quality
import com.leon.biuvideo.wraps.PgcWrap

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
class PgcModel : ViewModel() {
    val season: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val res: MutableLiveData<PgcWrap> by lazy {
        MutableLiveData<PgcWrap>()
    }
}