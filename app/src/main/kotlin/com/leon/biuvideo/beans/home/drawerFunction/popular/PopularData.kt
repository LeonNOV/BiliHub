package com.leon.biuvideo.beans.home.drawerFunction.popular

import android.os.Parcelable
import com.leon.biuvideo.ui.adapters.drawer.PopularAdapter
import kotlinx.android.parcel.Parcelize

/**
 * @Author Leon
 * @Time 2022/08/03
 * @Desc
 */
@Parcelize
data class PopularData(
    val type: PopularAdapter.PopularType,
    val cover: String,
    val duration: Int,
    val title: String,
    val author: String,
    val play: Int,
    val danmaku: Int,
    val reason: String,
    val bvid: String
) : Parcelable
