package com.leon.biuvideo.beans.discover

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @Author Leon
 * @Time 2021/11/03
 * @Desc
 */
@Parcelize
data class HotSearch(
    val icon: String,
    val keyword: String,
    val position: Int,
    val show_name: String,
) : Parcelable