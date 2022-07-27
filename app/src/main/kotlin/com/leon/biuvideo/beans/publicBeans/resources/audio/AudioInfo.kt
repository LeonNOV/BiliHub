package com.leon.biuvideo.beans.publicBeans.resources.audio

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AudioInfo(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("msg")
    val msg: String
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("activityId")
        val activityId: Int,
        @SerializedName("aid")
        val aid: Int,
        @SerializedName("attr")
        val attr: Int,
        @SerializedName("author")
        val author: String,
        @SerializedName("bvid")
        val bvid: String,
        @SerializedName("cid")
        val cid: Int,
        @SerializedName("coin_num")
        val coinNum: Int,
        @SerializedName("cover")
        val cover: String,
        @SerializedName("crtype")
        val crtype: Int,
        @SerializedName("curtime")
        val curtime: Int,
        @SerializedName("duration")
        val duration: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("intro")
        val intro: String,
        @SerializedName("limit")
        val limit: Int,
        @SerializedName("limitdesc")
        val limitdesc: String,
        @SerializedName("lyric")
        val lyric: String,
        @SerializedName("msid")
        val msid: Int,
        @SerializedName("passtime")
        val passtime: Int,
        @SerializedName("statistic")
        val statistic: Statistic,
        @SerializedName("title")
        val title: String,
        @SerializedName("uid")
        val uid: Int,
        @SerializedName("uname")
        val uname: String,
        @SerializedName("vipInfo")
        val vipInfo: VipInfo
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

        @Parcelize
        data class VipInfo(
            @SerializedName("due_date")
            val dueDate: Long,
            @SerializedName("status")
            val status: Int,
            @SerializedName("type")
            val type: Int,
            @SerializedName("vip_pay_type")
            val vipPayType: Int
        ) : Parcelable
    }
}