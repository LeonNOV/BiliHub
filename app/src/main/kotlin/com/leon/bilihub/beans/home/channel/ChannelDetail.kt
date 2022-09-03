package com.leon.bilihub.beans.home.channel

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChannelDetail(
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
        @SerializedName("alpha")
        val alpha: Int,
        @SerializedName("archive_count")
        val archiveCount: String,
        @SerializedName("background")
        val background: String,
        @SerializedName("cover")
        val cover: String,
        @SerializedName("ctype")
        val ctype: Int,
        @SerializedName("featured_count")
        val featuredCount: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("is_subscribed")
        val isSubscribed: Boolean,
        @SerializedName("name")
        val name: String,
        @SerializedName("subscribed_count")
        val subscribedCount: Int,
        @SerializedName("tabs")
        val tabs: List<Tab>,
        @SerializedName("tag_channels")
        val tagChannels: List<TagChannel>,
        @SerializedName("theme_color")
        val themeColor: String,
        @SerializedName("view_count")
        val viewCount: String
    ) : Parcelable {
        @Parcelize
        data class Tab(
            @SerializedName("options")
            val options: List<Option>,
            @SerializedName("type")
            val type: String
        ) : Parcelable {
            @Parcelize
            data class Option(
                @SerializedName("icon")
                val icon: String?,
                @SerializedName("title")
                val title: String,
                @SerializedName("value")
                val value: String
            ) : Parcelable
        }

        @Parcelize
        data class TagChannel(
            @SerializedName("archive_count")
            val archiveCount: String,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("is_subscribed")
            val isSubscribed: Boolean?,
            @SerializedName("name")
            val name: String,
            @SerializedName("subscribed_count")
            val subscribedCount: Int
        ) : Parcelable
    }
}