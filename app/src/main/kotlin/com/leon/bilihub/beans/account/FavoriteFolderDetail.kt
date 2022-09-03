package com.leon.bilihub.beans.account

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteFolderDetail(
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
        @SerializedName("has_more")
        val hasMore: Boolean,
        @SerializedName("info")
        val info: Info,
        @SerializedName("medias")
        val medias: List<Media>?
    ) : Parcelable {
        @Parcelize
        data class Info(
            @SerializedName("attr")
            val attr: Int,
            @SerializedName("cnt_info")
            val cntInfo: CntInfo,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("cover_type")
            val coverType: Int,
            @SerializedName("ctime")
            val ctime: Int,
            @SerializedName("fav_state")
            val favState: Int,
            @SerializedName("fid")
            val fid: Int,
            @SerializedName("id")
            val id: Int,
            @SerializedName("intro")
            val intro: String,
            @SerializedName("like_state")
            val likeState: Int,
            @SerializedName("media_count")
            val mediaCount: Int,
            @SerializedName("mid")
            val mid: String,
            @SerializedName("mtime")
            val mtime: Int,
            @SerializedName("state")
            val state: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("type")
            val type: Int,
            @SerializedName("upper")
            val upper: Upper
        ) : Parcelable {
            @Parcelize
            data class CntInfo(
                @SerializedName("collect")
                val collect: Int,
                @SerializedName("play")
                val play: Int,
                @SerializedName("share")
                val share: Int,
                @SerializedName("thumb_up")
                val thumbUp: Int
            ) : Parcelable

            @Parcelize
            data class Upper(
                @SerializedName("face")
                val face: String,
                @SerializedName("followed")
                val followed: Boolean,
                @SerializedName("mid")
                val mid: String,
                @SerializedName("name")
                val name: String,
                @SerializedName("vip_statue")
                val vipStatue: Int,
                @SerializedName("vip_type")
                val vipType: Int
            ) : Parcelable
        }

        @Parcelize
        data class Media(
            @SerializedName("attr")
            val attr: Int,
            @SerializedName("bv_id")
            val bvId: String,
            @SerializedName("bvid")
            val bvid: String,
            @SerializedName("cnt_info")
            val cntInfo: CntInfo,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("ctime")
            val ctime: Int,
            @SerializedName("duration")
            val duration: Int,
            @SerializedName("fav_time")
            val favTime: Int,
            @SerializedName("id")
            val id: Int,
            @SerializedName("intro")
            val intro: String,
            @SerializedName("link")
            val link: String,
            @SerializedName("page")
            val page: Int,
            @SerializedName("pubtime")
            val pubtime: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("type")
            val type: Int,
            @SerializedName("ugc")
            val ugc: Ugc,
            @SerializedName("upper")
            val upper: Upper
        ) : Parcelable {
            @Parcelize
            data class CntInfo(
                @SerializedName("collect")
                val collect: Int,
                @SerializedName("danmaku")
                val danmaku: Int,
                @SerializedName("play")
                val play: Int
            ) : Parcelable

            @Parcelize
            data class Ugc(
                @SerializedName("first_cid")
                val firstCid: Int
            ) : Parcelable

            @Parcelize
            data class Upper(
                @SerializedName("face")
                val face: String,
                @SerializedName("mid")
                val mid: String,
                @SerializedName("name")
                val name: String
            ) : Parcelable
        }
    }
}