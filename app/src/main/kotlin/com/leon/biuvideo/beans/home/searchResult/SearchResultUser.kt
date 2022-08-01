package com.leon.biuvideo.beans.home.searchResult

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResultUser(
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
            @SerializedName("get upuser live status")
            val getUpuserLiveStatus: String,
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
            @SerializedName("face_nft")
            val faceNft: Int,
            @SerializedName("face_nft_type")
            val faceNftType: Int,
            @SerializedName("fans")
            val fans: Int,
            @SerializedName("gender")
            val gender: Int,
            @SerializedName("hit_columns")
            val hitColumns: List<String>,
            @SerializedName("is_live")
            val isLive: Int,
            @SerializedName("is_upuser")
            val isUpuser: Int,
            @SerializedName("level")
            val level: Int,
            @SerializedName("mid")
            val mid: Int,
            @SerializedName("official_verify")
            val officialVerify: OfficialVerify,
            @SerializedName("res")
            val res: List<Re>,
            @SerializedName("room_id")
            val roomId: Int,
            @SerializedName("type")
            val type: String,
            @SerializedName("uname")
            val uname: String,
            @SerializedName("upic")
            val upic: String,
            @SerializedName("usign")
            val usign: String,
            @SerializedName("verify_info")
            val verifyInfo: String,
            @SerializedName("videos")
            val videos: Int
        ) : Parcelable {
            @Parcelize
            data class OfficialVerify(
                @SerializedName("desc")
                val desc: String,
                @SerializedName("type")
                val type: Int
            ) : Parcelable

            @Parcelize
            data class Re(
                @SerializedName("aid")
                val aid: Int,
                @SerializedName("arcurl")
                val arcurl: String,
                @SerializedName("bvid")
                val bvid: String,
                @SerializedName("coin")
                val coin: Int,
                @SerializedName("desc")
                val desc: String,
                @SerializedName("dm")
                val dm: Int,
                @SerializedName("duration")
                val duration: String,
                @SerializedName("fav")
                val fav: Int,
                @SerializedName("is_pay")
                val isPay: Int,
                @SerializedName("is_union_video")
                val isUnionVideo: Int,
                @SerializedName("pic")
                val pic: String,
                @SerializedName("play")
                val play: String,
                @SerializedName("pubdate")
                val pubdate: Int,
                @SerializedName("title")
                val title: String
            ) : Parcelable
        }
    }
}