package com.leon.biuvideo.beans.partition

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Partition(
    @SerializedName("channelId")
    val channelId: Int,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("route")
    val route: String,
    @SerializedName("sub")
    val subs: ArrayList<Sub>,
    @SerializedName("tid")
    val tid: Int,
    @SerializedName("url")
    val url: String
) : Parcelable {
    @Parcelize
    data class Sub(
        @SerializedName("desc")
        val desc: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("route")
        val route: String,
        @SerializedName("subChannelId")
        val subChannelId: Int,
        @SerializedName("tid")
        val tid: Int?,
        @SerializedName("url")
        val url: String
    ) : Parcelable
}