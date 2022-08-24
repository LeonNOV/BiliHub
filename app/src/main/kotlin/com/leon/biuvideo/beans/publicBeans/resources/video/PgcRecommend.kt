package com.leon.biuvideo.beans.publicBeans.resources.video

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PgcRecommend(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("relates")
        val relates: List<Relate>,
        @SerializedName("season")
        val season: List<Season>
    ) : Parcelable {
        @Parcelize
        data class Relate(
            @SerializedName("desc1")
            val desc1: String,
            @SerializedName("desc2")
            val desc2: String,
            @SerializedName("item_id")
            val itemId: Int,
            @SerializedName("pic")
            val pic: String,
            @SerializedName("title")
            val title: String,
            @SerializedName("type")
            val type: Int,
            @SerializedName("type_name")
            val typeName: String,
            @SerializedName("url")
            val url: String
        ) : Parcelable

        @Parcelize
        data class Season(
            @SerializedName("actor")
            val actor: String,
            @SerializedName("badge")
            val badge: String,
            @SerializedName("badge_info")
            val badgeInfo: BadgeInfo,
            @SerializedName("badge_type")
            val badgeType: Int,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("from")
            val from: Int,
            @SerializedName("new_ep")
            val newEp: NewEp,
            @SerializedName("rating")
            val rating: Rating?,
            @SerializedName("report")
            val report: Report,
            @SerializedName("rights")
            val rights: Rights,
            @SerializedName("season_id")
            val seasonId: String,
            @SerializedName("season_type")
            val seasonType: Int,
            @SerializedName("stat")
            val stat: Stat,
            @SerializedName("styles")
            val styles: List<Style>,
            @SerializedName("subtitle")
            val subtitle: String,
            @SerializedName("title")
            val title: String,
            @SerializedName("url")
            val url: String,
            @SerializedName("user_status")
            val userStatus: UserStatus
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
            data class Rating(
                @SerializedName("count")
                val count: Int,
                @SerializedName("score")
                val score: Double
            ) : Parcelable

            @Parcelize
            data class Report(
                @SerializedName("is_wtgt")
                val isWtgt: Int,
                @SerializedName("seriesId")
                val seriesId: Int
            ) : Parcelable

            @Parcelize
            data class Rights(
                @SerializedName("can_watch")
                val canWatch: Int,
                @SerializedName("resource")
                val resource: String
            ) : Parcelable

            @Parcelize
            data class Stat(
                @SerializedName("danmaku")
                val danmaku: Int,
                @SerializedName("follow")
                val follow: Int,
                @SerializedName("view")
                val view: Int
            ) : Parcelable

            @Parcelize
            data class Style(
                @SerializedName("id")
                val id: Int,
                @SerializedName("name")
                val name: String
            ) : Parcelable

            @Parcelize
            data class UserStatus(
                @SerializedName("follow")
                val follow: Int
            ) : Parcelable
        }
    }
}