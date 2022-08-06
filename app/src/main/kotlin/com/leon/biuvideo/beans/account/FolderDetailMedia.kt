package com.leon.biuvideo.beans.account

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @Author Leon
 * @Time 2022/08/03
 * @Desc
 */
@Parcelize
class FolderDetailMedia(
    val Bvid: String,
    val Cover: String,
    val Duration: Int,
    val Title: String,
    val author: String,
    val Play: Int,
    val Collect: Int
) : Parcelable