package com.leon.biuvideo.beans.publicBeans.resources.article

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleInfo(
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
        @SerializedName("attention")
        val attention: Boolean,
        @SerializedName("author_name")
        val authorName: String,
        @SerializedName("banner_url")
        val bannerUrl: String,
        @SerializedName("coin")
        val coin: Int,
        @SerializedName("favorite")
        val favorite: Boolean,
        @SerializedName("image_urls")
        val imageUrls: List<String>,
        @SerializedName("in_list")
        val inList: Boolean,
        @SerializedName("is_author")
        val isAuthor: Boolean,
        @SerializedName("like")
        val like: Int,
        @SerializedName("location")
        val location: String,
        @SerializedName("mid")
        val mid: String,
        @SerializedName("next")
        val next: Int,
        @SerializedName("origin_image_urls")
        val originImageUrls: List<String>,
        @SerializedName("pre")
        val pre: Int,
        @SerializedName("shareable")
        val shareable: Boolean,
        @SerializedName("show_later_watch")
        val showLaterWatch: Boolean,
        @SerializedName("show_small_window")
        val showSmallWindow: Boolean,
        @SerializedName("stats")
        val stats: Stats,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: Int,
        @SerializedName("video_url")
        val videoUrl: String
    ) : Parcelable {

        @Parcelize
        data class Stats(
            @SerializedName("coin")
            val coin: Int,
            @SerializedName("dislike")
            val dislike: Int,
            @SerializedName("dynamic")
            val `dynamic`: Int,
            @SerializedName("favorite")
            val favorite: Int,
            @SerializedName("like")
            val like: Int,
            @SerializedName("reply")
            val reply: Int,
            @SerializedName("share")
            val share: Int,
            @SerializedName("view")
            val view: Int
        ) : Parcelable
    }
}