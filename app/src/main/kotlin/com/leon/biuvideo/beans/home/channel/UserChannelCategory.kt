package com.leon.biuvideo.beans.home.channel

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserChannelCategory(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("ttl")
    val ttl: Int
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("normal_channels")
        val normalChannels: List<NormalChannel>,
        @SerializedName("stick_channels")
        val stickChannels: List<StickChannel>,
        @SerializedName("total")
        val total: Int
    ) : Parcelable {
        @Parcelize
        data class NormalChannel(
            @SerializedName("alpha")
            val alpha: Int?,
            @SerializedName("archive_count")
            val archiveCount: String,
            @SerializedName("cover")
            val cover: String?,
            @SerializedName("ctype")
            val ctype: Int,
            @SerializedName("id")
            val id: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("subscribed_count")
            val subscribedCount: Int,
            @SerializedName("theme_color")
            val themeColor: String?,
            @SerializedName("view_count")
            val viewCount: String
        ) : Parcelable

        @Parcelize
        data class StickChannel(
            @SerializedName("alpha")
            val alpha: Int,
            @SerializedName("archive_count")
            val archiveCount: String,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("ctype")
            val ctype: Int,
            @SerializedName("id")
            val id: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("subscribed_count")
            val subscribedCount: Int,
            @SerializedName("theme_color")
            val themeColor: String,
            @SerializedName("view_count")
            val viewCount: String
        ) : Parcelable
    }
}