package com.leon.bilihub.beans.publicBeans.resources.video

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @Author Leon
 * @Time 2022/08/20
 * @Desc
 */
@Parcelize
data class VideoSpeed(val speed: Float, val speedStr: String, var selected: Boolean) : Parcelable
