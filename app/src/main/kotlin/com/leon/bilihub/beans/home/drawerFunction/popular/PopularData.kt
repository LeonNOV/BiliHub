package com.leon.bilihub.beans.home.drawerFunction.popular

import android.os.Parcelable
import com.leon.bilihub.ui.adapters.drawer.popular.PopularAdapter
import kotlinx.parcelize.Parcelize

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
    val id: String
) : Parcelable
