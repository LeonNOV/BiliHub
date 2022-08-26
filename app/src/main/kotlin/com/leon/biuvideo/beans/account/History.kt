package com.leon.biuvideo.beans.account

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class History(
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
        @SerializedName("cursor")
        val cursor: Cursor,
        @SerializedName("list")
        val list: List<Data>,
        @SerializedName("tab")
        val tab: List<Tab>
    ) : Parcelable {
        @Parcelize
        data class Cursor(
            @SerializedName("business")
            val business: String,
            @SerializedName("max")
            val max: Int,
            @SerializedName("ps")
            val ps: Int,
            @SerializedName("view_at")
            val viewAt: Int
        ) : Parcelable

        @Parcelize
        data class Data(
            @SerializedName("author_face")
            val authorFace: String,
            @SerializedName("author_mid")
            val authorMid: Int,
            @SerializedName("author_name")
            val authorName: String,
            @SerializedName("badge")
            val badge: String,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("covers")
            val covers: List<String>?,
            @SerializedName("current")
            val current: String,
            @SerializedName("duration")
            val duration: Int,
            @SerializedName("history")
            val history: History,
            @SerializedName("is_fav")
            val isFav: Int,
            @SerializedName("is_finish")
            val isFinish: Int,
            @SerializedName("kid")
            val kid: Int,
            @SerializedName("live_status")
            val liveStatus: Int,
            @SerializedName("long_title")
            val longTitle: String,
            @SerializedName("new_desc")
            val newDesc: String,
            @SerializedName("progress")
            val progress: Int,
            @SerializedName("show_title")
            val showTitle: String,
            @SerializedName("tag_name")
            val tagName: String,
            @SerializedName("title")
            val title: String,
            @SerializedName("total")
            val total: Int,
            @SerializedName("uri")
            val uri: String,
            @SerializedName("videos")
            val videos: Int,
            @SerializedName("view_at")
            val viewAt: Int
        ) : Parcelable {
            @Parcelize
            data class History(
                @SerializedName("business")
                val business: String,
                @SerializedName("bvid")
                val bvid: String,
                @SerializedName("cid")
                val cid: Int,
                @SerializedName("dt")
                val dt: Int,
                @SerializedName("epid")
                val epid: Int,
                @SerializedName("oid")
                val oid: String,
                @SerializedName("page")
                val page: Int,
                @SerializedName("part")
                val part: String
            ) : Parcelable
        }

        @Parcelize
        data class Tab(
            @SerializedName("name")
            val name: String,
            @SerializedName("type")
            val type: String
        ) : Parcelable
    }
}