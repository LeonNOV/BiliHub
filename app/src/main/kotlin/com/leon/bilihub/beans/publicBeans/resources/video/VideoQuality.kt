package com.leon.bilihub.beans.publicBeans.resources.video

import android.os.Parcelable
import com.leon.bilihub.http.Quality
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
    var selected: Boolean,
    val quality: Quality,
    val qualityInt: Int,
    val qualityStr: String
) : Parcelable
