package com.leon.bilihub.beans.home.searchResult

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResultMedia(
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
        @SerializedName("cost_time")
        val costTime: CostTime,
        @SerializedName("egg_hit")
        val eggHit: Int,
        @SerializedName("in_black_key")
        val inBlackKey: Int,
        @SerializedName("in_white_key")
        val inWhiteKey: Int,
        @SerializedName("numPages")
        val numPages: Int,
        @SerializedName("numResults")
        val numResults: Int,
        @SerializedName("page")
        val page: Int,
        @SerializedName("pagesize")
        val pagesize: Int,
        @SerializedName("result")
        val result: List<Result>,
        @SerializedName("rqt_type")
        val rqtType: String,
        @SerializedName("seid")
        val seid: String,
        @SerializedName("show_column")
        val showColumn: Int,
        @SerializedName("suggest_keyword")
        val suggestKeyword: String
    ) : Parcelable {
        @Parcelize
        data class CostTime(
            @SerializedName("as_request")
            val asRequest: String,
            @SerializedName("as_request_format")
            val asRequestFormat: String,
            @SerializedName("as_response_format")
            val asResponseFormat: String,
            @SerializedName("deserialize_response")
            val deserializeResponse: String,
            @SerializedName("illegal_handler")
            val illegalHandler: String,
            @SerializedName("main_handler")
            val mainHandler: String,
            @SerializedName("params_check")
            val paramsCheck: String,
            @SerializedName("save_cache")
            val saveCache: String,
            @SerializedName("total")
            val total: String
        ) : Parcelable

        @Parcelize
        data class Result(
            @SerializedName("areas")
            val areas: String,
            @SerializedName("badges")
            val badges: List<Badge>?,
            @SerializedName("button_text")
            val buttonText: String,
            @SerializedName("corner")
            val corner: Int,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("cv")
            val cv: String,
            @SerializedName("desc")
            val desc: String,
            @SerializedName("display_info")
            val displayInfo: List<DisplayInfo>?,
            @SerializedName("ep_size")
            val epSize: Int,
            @SerializedName("eps")
            val eps: List<Ep>,
            @SerializedName("fix_pubtime_str")
            val fixPubtimeStr: String,
            @SerializedName("goto_url")
            val gotoUrl: String,
            @SerializedName("hit_epids")
            val hitEpids: String,
            @SerializedName("index_show")
            val indexShow: String,
            @SerializedName("is_avid")
            val isAvid: Boolean,
            @SerializedName("is_follow")
            val isFollow: Int,
            @SerializedName("is_selection")
            val isSelection: Int,
            @SerializedName("media_id")
            val mediaId: Int,
            @SerializedName("media_mode")
            val mediaMode: Int,
            @SerializedName("media_score")
            val mediaScore: MediaScore,
            @SerializedName("media_type")
            val mediaType: Int,
            @SerializedName("org_title")
            val orgTitle: String,
            @SerializedName("pgc_season_id")
            val pgcSeasonId: Int,
            @SerializedName("pubtime")
            val pubtime: Int,
            @SerializedName("season_id")
            val seasonId: Int,
            @SerializedName("season_type")
            val seasonType: Int,
            @SerializedName("season_type_name")
            val seasonTypeName: String,
            @SerializedName("selection_style")
            val selectionStyle: String,
            @SerializedName("staff")
            val staff: String,
            @SerializedName("styles")
            val styles: String,
            @SerializedName("title")
            val title: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("url")
            val url: String
        ) : Parcelable {
            @Parcelize
            data class Badge(
//                @SerializedName("bg_color")
//                val bgColor: String,
//                @SerializedName("bg_color_night")
//                val bgColorNight: String,
//                @SerializedName("bg_style")
//                val bgStyle: Int,
//                @SerializedName("border_color")
//                val borderColor: String,
//                @SerializedName("border_color_night")
//                val borderColorNight: String,
                @SerializedName("text")
                val text: String
//                @SerializedName("text_color")
//                val textColor: String,
//                @SerializedName("text_color_night")
//                val textColorNight: String
            ) : Parcelable

            @Parcelize
            data class DisplayInfo(
                @SerializedName("bg_color")
                val bgColor: String,
                @SerializedName("bg_color_night")
                val bgColorNight: String,
                @SerializedName("bg_style")
                val bgStyle: Int,
                @SerializedName("border_color")
                val borderColor: String,
                @SerializedName("border_color_night")
                val borderColorNight: String,
                @SerializedName("text")
                val text: String,
                @SerializedName("text_color")
                val textColor: String,
                @SerializedName("text_color_night")
                val textColorNight: String
            ) : Parcelable

            @Parcelize
            data class Ep(
                @SerializedName("badges")
                val badges: List<Badge>?,
                @SerializedName("cover")
                val cover: String,
                @SerializedName("id")
                val id: Int,
                @SerializedName("index_title")
                val indexTitle: String,
                @SerializedName("long_title")
                val longTitle: String,
                @SerializedName("release_date")
                val releaseDate: String,
                @SerializedName("title")
                val title: String,
                @SerializedName("url")
                val url: String
            ) : Parcelable {
                @Parcelize
                data class Badge(
                    @SerializedName("bg_color")
                    val bgColor: String,
                    @SerializedName("bg_color_night")
                    val bgColorNight: String,
                    @SerializedName("bg_style")
                    val bgStyle: Int,
                    @SerializedName("border_color")
                    val borderColor: String,
                    @SerializedName("border_color_night")
                    val borderColorNight: String,
                    @SerializedName("text")
                    val text: String,
                    @SerializedName("text_color")
                    val textColor: String,
                    @SerializedName("text_color_night")
                    val textColorNight: String
                ) : Parcelable
            }

            @Parcelize
            data class MediaScore(
                @SerializedName("score")
                val score: Double,
                @SerializedName("user_count")
                val userCount: Int
            ) : Parcelable
        }
    }
}