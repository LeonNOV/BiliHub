package com.leon.bilihub.beans.home

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeRecommend(
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
        @SerializedName("item")
        val item: List<Item>
    ) : Parcelable {
        @Parcelize
        data class Item(
            @SerializedName("bvid")
            val bvid: String,
            @SerializedName("cid")
            val cid: String,
            @SerializedName("duration")
            val duration: Int,
            @SerializedName("id")
            val id: String,
            @SerializedName("is_followed")
            val isFollowed: Int,
            @SerializedName("owner")
            val owner: Owner,
            @SerializedName("pic")
            val pic: String,
            @SerializedName("pubdate")
            val pubdate: Int,
            @SerializedName("stat")
            val stat: Stat,
            @SerializedName("title")
            val title: String,
            @SerializedName("uri")
            val uri: String
        ) : Parcelable {
            @Parcelize
            data class Owner(
                @SerializedName("face")
                val face: String,
                @SerializedName("mid")
                val mid: String,
                @SerializedName("name")
                val name: String
            ) : Parcelable

            @Parcelize
            data class Stat(
                @SerializedName("danmaku")
                val danmaku: Int,
                @SerializedName("like")
                val like: Int,
                @SerializedName("view")
                val view: Int
            ) : Parcelable
        }
    }
}