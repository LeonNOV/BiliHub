package com.leon.bilihub.beans.publicBeans.user

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserAudio(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("msg")
    val msg: String
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("curPage")
        val curPage: Int,
        @SerializedName("data")
        val audioList: List<Audio>,
        @SerializedName("pageCount")
        val pageCount: Int,
        @SerializedName("pageSize")
        val pageSize: Int,
        @SerializedName("totalSize")
        val totalSize: Int
    ) : Parcelable {
        @Parcelize
        data class Audio(
            @SerializedName("coin_num")
            val coinNum: Int,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("crtype")
            val crtype: Int,
            @SerializedName("ctime")
            val ctime: Long,
            @SerializedName("curtime")
            val curtime: Int,
            @SerializedName("duration")
            val duration: Int,
            @SerializedName("id")
            val id: Int,
            @SerializedName("lyric")
            val lyric: String,
            @SerializedName("passtime")
            val passtime: Int,
            @SerializedName("statistic")
            val statistic: Statistic,
            @SerializedName("title")
            val title: String,
            @SerializedName("uid")
            val uid: Int,
            @SerializedName("uname")
            val uname: String
        ) : Parcelable {
            @Parcelize
            data class Statistic(
                @SerializedName("collect")
                val collect: Int,
                @SerializedName("comment")
                val comment: Int,
                @SerializedName("play")
                val play: Int,
                @SerializedName("share")
                val share: Int,
                @SerializedName("sid")
                val sid: Int
            ) : Parcelable
        }
    }
}