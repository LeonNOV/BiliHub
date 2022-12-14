package com.leon.bilihub.beans.account

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteFolder(
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
        @SerializedName("list")
        val list: List<Folder>
    ) : Parcelable {
        @Parcelize
        data class Folder(
            @SerializedName("attr")
            val attr: Int,
            @SerializedName("fav_state")
            val favState: Int,
            @SerializedName("fid")
            val fid: Int,
            @SerializedName("id")
            val id: String,
            @SerializedName("media_count")
            val mediaCount: Int,
            @SerializedName("mid")
            val mid: String,
            @SerializedName("title")
            val title: String
        ) : Parcelable
    }
}