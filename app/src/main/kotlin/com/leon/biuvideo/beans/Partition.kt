package com.leon.biuvideo.beans

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @Author Leon
 * @Time 2022/06/21
 * @Desc
 */
@Parcelize
data class Partition(
    val title: String,
    val id: String,
    val tags: MutableList<PartitionTag>
) : Parcelable

@Parcelize
data class PartitionTag(
    val title: String,
    val id: String
) : Parcelable