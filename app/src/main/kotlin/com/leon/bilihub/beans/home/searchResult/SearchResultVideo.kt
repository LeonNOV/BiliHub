package com.leon.bilihub.beans.home.searchResult

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResultVideo(
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
            @SerializedName("aid")
            val aid: Int,
            @SerializedName("arcrank")
            val arcrank: String,
            @SerializedName("arcurl")
            val arcurl: String,
            @SerializedName("author")
            val author: String,
            @SerializedName("badgepay")
            val badgepay: Boolean,
            @SerializedName("bvid")
            val bvid: String,
            @SerializedName("corner")
            val corner: String,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("danmaku")
            val danmaku: Int,
            @SerializedName("desc")
            val desc: String,
            @SerializedName("description")
            val description: String,
            @SerializedName("duration")
            val duration: String,
            @SerializedName("favorites")
            val favorites: Int,
            @SerializedName("hit_columns")
            val hitColumns: List<String>,
            @SerializedName("id")
            val id: Int,
            @SerializedName("is_pay")
            val isPay: Int,
            @SerializedName("is_union_video")
            val isUnionVideo: Int,
            @SerializedName("like")
            val like: Int,
            @SerializedName("mid")
            val mid: String,
            @SerializedName("pic")
            val pic: String,
            @SerializedName("play")
            val play: Int,
            @SerializedName("pubdate")
            val pubdate: Int,
            @SerializedName("rank_score")
            val rankScore: Int,
            @SerializedName("rec_reason")
            val recReason: String,
            @SerializedName("review")
            val review: Int,
            @SerializedName("senddate")
            val senddate: Int,
            @SerializedName("tag")
            val tag: String,
            @SerializedName("title")
            val title: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("typeid")
            val typeid: String,
            @SerializedName("typename")
            val typename: String,
            @SerializedName("upic")
            val upic: String,
            @SerializedName("url")
            val url: String,
            @SerializedName("video_review")
            val videoReview: Int,
            @SerializedName("view_type")
            val viewType: String
        ) : Parcelable
    }
}