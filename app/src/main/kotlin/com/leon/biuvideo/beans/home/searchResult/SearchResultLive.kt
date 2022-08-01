package com.leon.biuvideo.beans.home.searchResult

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResultLive(
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
        @SerializedName("pageinfo")
        val pageinfo: Pageinfo,
        @SerializedName("pagesize")
        val pagesize: Int,
        @SerializedName("result")
        val result: Result,
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
            @SerializedName("as_doc_request")
            val asDocRequest: String,
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
        data class Pageinfo(
            @SerializedName("live_room")
            val liveRoom: LiveRoom,
            @SerializedName("live_user")
            val liveUser: LiveUser
        ) : Parcelable {
            @Parcelize
            data class LiveRoom(
                @SerializedName("numPages")
                val numPages: Int,
                @SerializedName("numResults")
                val numResults: Int,
                @SerializedName("pages")
                val pages: Int,
                @SerializedName("total")
                val total: Int
            ) : Parcelable

            @Parcelize
            data class LiveUser(
                @SerializedName("numPages")
                val numPages: Int,
                @SerializedName("numResults")
                val numResults: Int,
                @SerializedName("pages")
                val pages: Int,
                @SerializedName("total")
                val total: Int
            ) : Parcelable
        }

        @Parcelize
        data class Result(
            @SerializedName("live_room")
            val liveRoom: List<LiveRoom>,
            @SerializedName("live_user")
            val liveUser: List<LiveUser>
        ) : Parcelable {
            @Parcelize
            data class LiveRoom(
                @SerializedName("area")
                val area: Int,
                @SerializedName("attentions")
                val attentions: Int,
                @SerializedName("cate_name")
                val cateName: String,
                @SerializedName("cover")
                val cover: String,
                @SerializedName("hit_columns")
                val hitColumns: List<String>,
                @SerializedName("is_live_room_inline")
                val isLiveRoomInline: Int,
                @SerializedName("live_status")
                val liveStatus: Int,
                @SerializedName("live_time")
                val liveTime: String,
                @SerializedName("online")
                val online: Int,
                @SerializedName("rank_index")
                val rankIndex: Int,
                @SerializedName("rank_offset")
                val rankOffset: Int,
                @SerializedName("rank_score")
                val rankScore: Int,
                @SerializedName("roomid")
                val roomid: Int,
                @SerializedName("short_id")
                val shortId: Int,
                @SerializedName("tags")
                val tags: String,
                @SerializedName("title")
                val title: String,
                @SerializedName("type")
                val type: String,
                @SerializedName("uface")
                val uface: String,
                @SerializedName("uid")
                val uid: Int,
                @SerializedName("uname")
                val uname: String,
                @SerializedName("user_cover")
                val userCover: String,
                @SerializedName("watched_show")
                val watchedShow: WatchedShow
            ) : Parcelable {
                @Parcelize
                data class WatchedShow(
                    @SerializedName("icon")
                    val icon: String,
                    @SerializedName("icon_location")
                    val iconLocation: String,
                    @SerializedName("icon_web")
                    val iconWeb: String,
                    @SerializedName("num")
                    val num: Int,
                    @SerializedName("switch")
                    val switch: Boolean,
                    @SerializedName("text_large")
                    val textLarge: String,
                    @SerializedName("text_small")
                    val textSmall: String
                ) : Parcelable
            }

            @Parcelize
            data class LiveUser(
                @SerializedName("area")
                val area: Int,
                @SerializedName("area_v2_id")
                val areaV2Id: Int,
                @SerializedName("attentions")
                val attentions: Int,
                @SerializedName("cate_name")
                val cateName: String,
                @SerializedName("hit_columns")
                val hitColumns: List<String>,
                @SerializedName("is_live")
                val isLive: Boolean,
                @SerializedName("live_status")
                val liveStatus: Int,
                @SerializedName("live_time")
                val liveTime: String,
                @SerializedName("rank_index")
                val rankIndex: Int,
                @SerializedName("rank_offset")
                val rankOffset: Int,
                @SerializedName("rank_score")
                val rankScore: Int,
                @SerializedName("roomid")
                val roomid: Int,
                @SerializedName("tags")
                val tags: String,
                @SerializedName("type")
                val type: String,
                @SerializedName("uface")
                val uface: String,
                @SerializedName("uid")
                val uid: Int,
                @SerializedName("uname")
                val uname: String
            ) : Parcelable
        }
    }
}