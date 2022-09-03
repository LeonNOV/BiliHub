package com.leon.bilihub.beans.account

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CollectFolderDetail(
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
        @SerializedName("info")
        val info: Info,
        @SerializedName("medias")
        val medias: List<Media>?
    ) : Parcelable {
        @Parcelize
        data class Info(
            @SerializedName("cnt_info")
            val cntInfo: CntInfo,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("media_count")
            val mediaCount: Int,
            @SerializedName("season_type")
            val seasonType: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("upper")
            val upper: Upper
        ) : Parcelable {
            @Parcelize
            data class CntInfo(
                @SerializedName("collect")
                val collect: Int,
                @SerializedName("play")
                val play: Int
            ) : Parcelable

            @Parcelize
            data class Upper(
                @SerializedName("mid")
                val mid: String,
                @SerializedName("name")
                val name: String
            ) : Parcelable
        }

        @Parcelize
        data class Media(
            @SerializedName("bvid")
            val bvid: String,
            @SerializedName("cnt_info")
            val cntInfo: CntInfo,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("duration")
            val duration: Int,
            @SerializedName("id")
            val id: Int,
            @SerializedName("pubtime")
            val pubtime: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("upper")
            val upper: Upper
        ) : Parcelable {
            @Parcelize
            data class CntInfo(
                @SerializedName("collect")
                val collect: Int,
                @SerializedName("play")
                val play: Int
            ) : Parcelable

            @Parcelize
            data class Upper(
                @SerializedName("mid")
                val mid: String,
                @SerializedName("name")
                val name: String
            ) : Parcelable
        }
    }
}