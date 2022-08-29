package com.leon.bilihub.beans.partition

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PartitionData(
    @SerializedName("cache")
    val cache: Int,
    @SerializedName("code")
    val code: Int,
    @SerializedName("cost_time")
    val costTime: CostTime,
    @SerializedName("crr_query")
    val crrQuery: String,
    @SerializedName("egg_hit")
    val eggHit: Int,
    @SerializedName("exp_bits")
    val expBits: Int,
    @SerializedName("exp_str")
    val expStr: String,
    @SerializedName("msg")
    val msg: String,
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
    @SerializedName("show_module_list")
    val showModuleList: List<String>,
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
        @SerializedName("total")
        val total: String
    ) : Parcelable

    @Parcelize
    data class Result(
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
        @SerializedName("description")
        val description: String,
        @SerializedName("duration")
        val duration: Int,
        @SerializedName("favorites")
        val favorites: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("is_pay")
        val isPay: Int,
        @SerializedName("is_union_video")
        val isUnionVideo: Int,
        @SerializedName("mid")
        val mid: String,
        @SerializedName("pic")
        val pic: String,
        @SerializedName("play")
        val play: String,
        @SerializedName("pubdate")
        val pubdate: String,
        @SerializedName("rank_index")
        val rankIndex: Int,
        @SerializedName("rank_offset")
        val rankOffset: Int,
        @SerializedName("rank_score")
        val rankScore: Int,
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
        @SerializedName("video_review")
        val videoReview: Int
    ) : Parcelable
}