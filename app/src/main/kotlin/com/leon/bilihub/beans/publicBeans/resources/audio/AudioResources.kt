package com.leon.bilihub.beans.publicBeans.resources.audio

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AudioResources(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("msg")
    val msg: String
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("cdns")
        val cdns: List<String>,
        @SerializedName("info")
        val info: String,
        @SerializedName("sid")
        val sid: Int,
        @SerializedName("size")
        val size: Int,
        @SerializedName("timeout")
        val timeout: Int,
        @SerializedName("type")
        val type: Int
    ) : Parcelable
}