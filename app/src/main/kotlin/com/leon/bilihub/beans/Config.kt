package com.leon.bilihub.beans


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Config(
    @SerializedName("proxy")
    val proxy: Proxy,
    @SerializedName("version")
    val version: Version
) : Parcelable {
    @Parcelize
    data class Proxy(
        @SerializedName("url")
        val url: String
    ) : Parcelable

    @Parcelize
    data class Version(
        @SerializedName("versionCode")
        val versionCode: Int,
        @SerializedName("versionName")
        val versionName: String,
        @SerializedName("desc")
        val desc: String,
        @SerializedName("apkList")
        val apkList: String,
        @SerializedName("apk")
        val apk: String
    ) : Parcelable
}