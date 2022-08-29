package com.leon.bilihub.beans.account

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CollectFolder(
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
        @SerializedName("count")
        val count: Int,
        @SerializedName("has_more")
        val hasMore: Boolean,
        @SerializedName("list")
        val list: List<CollectData>
    ) : Parcelable {
        @Parcelize
        data class CollectData(
            @SerializedName("attr")
            val attr: Int,
            @SerializedName("cover")
            val cover: String,

            /**
             * 0: 合集
             * 2: 系列
             */
            @SerializedName("cover_type")
            val coverType: Int,
            @SerializedName("fav_state")
            val favState: Int,
            @SerializedName("fid")
            val fid: Int,
            @SerializedName("id")
            val id: Int,
            @SerializedName("intro")
            val intro: String,
            @SerializedName("link")
            val link: String,
            @SerializedName("media_count")
            val mediaCount: Int,
            @SerializedName("mid")
            val mid: Int,
            @SerializedName("mtime")
            val mtime: Int,
            @SerializedName("state")
            val state: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("type")
            val type: Int,
            @SerializedName("upper")
            val upper: Upper,
            @SerializedName("view_count")
            val viewCount: Int
        ) : Parcelable {
            @Parcelize
            data class Upper(
                @SerializedName("face")
                val face: String,
                @SerializedName("mid")
                val mid: Int,
                @SerializedName("name")
                val name: String
            ) : Parcelable
        }
    }
}