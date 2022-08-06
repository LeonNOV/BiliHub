package com.leon.biuvideo.beans.home.drawerFunction.popular

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PopularRankPgc(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("list")
        val list: List<Media>,
        @SerializedName("note")
        val note: String,
        @SerializedName("season_type")
        val seasonType: Int
    ) : Parcelable {
        @Parcelize
        data class Media(
            @SerializedName("badge")
            val badge: String,
            @SerializedName("badge_info")
            val badgeInfo: BadgeInfo,
            @SerializedName("badge_type")
            val badgeType: Int,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("desc")
            val desc: String,
            @SerializedName("new_ep")
            val newEp: NewEp,
            @SerializedName("rank")
            val rank: Int,
            @SerializedName("rating")
            val rating: String,
            @SerializedName("season_id")
            val seasonId: Int,
            @SerializedName("ss_horizontal_cover")
            val ssHorizontalCover: String,
            @SerializedName("stat")
            val stat: Stat,
            @SerializedName("title")
            val title: String,
            @SerializedName("url")
            val url: String
        ) : Parcelable {
            @Parcelize
            data class BadgeInfo(
                @SerializedName("bg_color")
                val bgColor: String,
                @SerializedName("bg_color_night")
                val bgColorNight: String,
                @SerializedName("text")
                val text: String
            ) : Parcelable

            @Parcelize
            data class NewEp(
                @SerializedName("cover")
                val cover: String,
                @SerializedName("index_show")
                val indexShow: String
            ) : Parcelable

            @Parcelize
            data class Stat(
                @SerializedName("danmaku")
                val danmaku: Int,
                @SerializedName("follow")
                val follow: Int,
                @SerializedName("series_follow")
                val seriesFollow: Int,
                @SerializedName("view")
                val view: Int
            ) : Parcelable
        }
    }
}