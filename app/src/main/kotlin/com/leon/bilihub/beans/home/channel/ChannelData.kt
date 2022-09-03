package com.leon.bilihub.beans.home.channel


import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChannelData(
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
        @SerializedName("archive_channels")
        val archiveChannels: List<ArchiveChannel>,
        @SerializedName("has_more")
        val hasMore: Boolean,
        @SerializedName("offset")
        val offset: String,
        @SerializedName("total")
        val total: Int
    ) : Parcelable {
        @Parcelize
        data class ArchiveChannel(
            @SerializedName("archive_count")
            val archiveCount: String,
            @SerializedName("archives")
            val archives: List<Archive>,
            @SerializedName("background")
            val background: String,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("featured_count")
            val featuredCount: Int,
            @SerializedName("id")
            val id: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("subscribed_count")
            val subscribedCount: Int,
            @SerializedName("theme_color")
            val themeColor: String
        ) : Parcelable {
            @Parcelize
            data class Archive(
                @SerializedName("author_id")
                val authorId: String,
                @SerializedName("author_name")
                val authorName: String,
                @SerializedName("bvid")
                val bvid: String,
                @SerializedName("cover")
                val cover: String,
                @SerializedName("danmaku")
                val danmaku: Int,
                @SerializedName("duration")
                val duration: String,
                @SerializedName("id")
                val id: Int,
                @SerializedName("like_count")
                val likeCount: String,
                @SerializedName("name")
                val name: String,
                @SerializedName("view_count")
                val viewCount: String
            ) : Parcelable
        }
    }
}