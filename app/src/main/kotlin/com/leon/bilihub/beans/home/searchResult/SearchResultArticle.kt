package com.leon.bilihub.beans.home.searchResult

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResultArticle(
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
            @SerializedName("category_id")
            val categoryId: Int,
            @SerializedName("category_name")
            val categoryName: String,
            @SerializedName("desc")
            val desc: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("image_urls")
            val imageUrls: List<String>,
            @SerializedName("like")
            val like: Int,
            @SerializedName("mid")
            val mid: String,
            @SerializedName("pub_time")
            val pubTime: Int,
            @SerializedName("rank_index")
            val rankIndex: Int,
            @SerializedName("rank_offset")
            val rankOffset: Int,
            @SerializedName("rank_score")
            val rankScore: Int,
            @SerializedName("reply")
            val reply: Int,
            @SerializedName("template_id")
            val templateId: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("view")
            val view: Int
        ) : Parcelable
    }
}