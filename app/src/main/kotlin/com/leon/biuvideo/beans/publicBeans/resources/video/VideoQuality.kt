package com.leon.biuvideo.beans.publicBeans.resources.video

import android.os.Parcelable
import com.leon.biuvideo.http.Quality
import kotlinx.android.parcel.Parcelize

/**
 * @Author Leon
 * @Time 2022/08/19
 * @Desc
 */
@Parcelize
data class VideoQuality(
    val extra: String?,

    /**
     * 是否为普通画质，即不需要会员
     */
    val isOrdinary: Boolean,
    val quality: Quality,
    val displayQn: String,
    val qualityStr: String
) : Parcelable
