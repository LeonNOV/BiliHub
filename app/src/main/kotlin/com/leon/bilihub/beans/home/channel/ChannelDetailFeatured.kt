package com.leon.bilihub.beans.home.channel

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChannelDetailFeatured(
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
        @SerializedName("has_more")
        val hasMore: Boolean,
        @SerializedName("list")
        val list: List<Archive>,
        @SerializedName("offset")
        val offset: String
    ) : Parcelable {
        @Parcelize
        data class Archive(
            @SerializedName("author_id")
            val authorId: Int,
            @SerializedName("author_name")
            val authorName: String,
            @SerializedName("bvid")
            val bvid: String,
            @SerializedName("card_type")
            val cardType: String,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("danmaku")
            val danmaku: Int,
            @SerializedName("duration")
            val duration: String,
            @SerializedName("filt")
            val filt: Int,
            @SerializedName("id")
            val id: Int,
            @SerializedName("like_count")
            val likeCount: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("sort")
            val sort: String,
            @SerializedName("view_count")
            val viewCount: String
        ) : Parcelable
    }
}