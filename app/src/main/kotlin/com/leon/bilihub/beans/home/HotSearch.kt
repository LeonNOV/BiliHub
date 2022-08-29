package com.leon.bilihub.beans.home

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HotSearch(
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
        @SerializedName("trending")
        val trending: Trending
    ) : Parcelable {
        @Parcelize
        data class Trending(
            @SerializedName("list")
            val list: List<Data>,
            @SerializedName("title")
            val title: String,
            @SerializedName("trackid")
            val trackid: String
        ) : Parcelable {
            @Parcelize
            data class Data(
                @SerializedName("goto")
                val goto: String,
                @SerializedName("icon")
                val icon: String,
                @SerializedName("keyword")
                val keyword: String,
                @SerializedName("show_name")
                val showName: String,
                @SerializedName("uri")
                val uri: String
            ) : Parcelable
        }
    }
}