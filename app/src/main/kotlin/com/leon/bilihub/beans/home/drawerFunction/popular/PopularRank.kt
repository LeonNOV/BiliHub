package com.leon.bilihub.beans.home.drawerFunction.popular

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PopularRank(
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
        @SerializedName("list")
        val list: List<Media>,
        @SerializedName("note")
        val note: String
    ) : Parcelable {
        @Parcelize
        data class Media(
            @SerializedName("aid")
            val aid: Int,
            @SerializedName("bvid")
            val bvid: String,
            @SerializedName("cid")
            val cid: String,
            @SerializedName("copyright")
            val copyright: Int,
            @SerializedName("ctime")
            val ctime: Int,
            @SerializedName("desc")
            val desc: String,
            @SerializedName("dimension")
            val dimension: Dimension,
            @SerializedName("duration")
            val duration: Int,
            @SerializedName("dynamic")
            val `dynamic`: String,
            @SerializedName("first_frame")
            val firstFrame: String,
            @SerializedName("mission_id")
            val missionId: Int?,
            @SerializedName("others")
            val others: List<Other>?,
            @SerializedName("owner")
            val owner: Owner,
            @SerializedName("pic")
            val pic: String,
            @SerializedName("pub_location")
            val pubLocation: String,
            @SerializedName("pubdate")
            val pubdate: Int,
            @SerializedName("rights")
            val rights: Rights,
            @SerializedName("score")
            val score: Int,
            @SerializedName("season_id")
            val seasonId: Int?,
            @SerializedName("short_link")
            val shortLink: String,
            @SerializedName("short_link_v2")
            val shortLinkV2: String,
            @SerializedName("stat")
            val stat: Stat,
            @SerializedName("state")
            val state: Int,
            @SerializedName("tid")
            val tid: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("tname")
            val tname: String,
            @SerializedName("up_from_v2")
            val upFromV2: Int?,
            @SerializedName("videos")
            val videos: Int
        ) : Parcelable {
            @Parcelize
            data class Dimension(
                @SerializedName("height")
                val height: Int,
                @SerializedName("rotate")
                val rotate: Int,
                @SerializedName("width")
                val width: Int
            ) : Parcelable

            @Parcelize
            data class Other(
                @SerializedName("aid")
                val aid: Int,
                @SerializedName("attribute")
                val attribute: Int,
                @SerializedName("attribute_v2")
                val attributeV2: Int,
                @SerializedName("bvid")
                val bvid: String,
                @SerializedName("cid")
                val cid: String,
                @SerializedName("copyright")
                val copyright: Int,
                @SerializedName("ctime")
                val ctime: Int,
                @SerializedName("desc")
                val desc: String,
                @SerializedName("dimension")
                val dimension: Dimension,
                @SerializedName("duration")
                val duration: Int,
                @SerializedName("dynamic")
                val `dynamic`: String,
                @SerializedName("first_frame")
                val firstFrame: String,
                @SerializedName("mission_id")
                val missionId: Int?,
                @SerializedName("owner")
                val owner: Owner,
                @SerializedName("pic")
                val pic: String,
                @SerializedName("pub_location")
                val pubLocation: String,
                @SerializedName("pubdate")
                val pubdate: Int,
                @SerializedName("rights")
                val rights: Rights,
                @SerializedName("score")
                val score: Int,
                @SerializedName("short_link")
                val shortLink: String,
                @SerializedName("short_link_v2")
                val shortLinkV2: String,
                @SerializedName("stat")
                val stat: Stat,
                @SerializedName("state")
                val state: Int,
                @SerializedName("tid")
                val tid: Int,
                @SerializedName("title")
                val title: String,
                @SerializedName("tname")
                val tname: String,
                @SerializedName("up_from_v2")
                val upFromV2: Int?,
                @SerializedName("videos")
                val videos: Int
            ) : Parcelable {
                @Parcelize
                data class Dimension(
                    @SerializedName("height")
                    val height: Int,
                    @SerializedName("rotate")
                    val rotate: Int,
                    @SerializedName("width")
                    val width: Int
                ) : Parcelable

                @Parcelize
                data class Owner(
                    @SerializedName("face")
                    val face: String,
                    @SerializedName("mid")
                    val mid: String,
                    @SerializedName("name")
                    val name: String
                ) : Parcelable

                @Parcelize
                data class Rights(
                    @SerializedName("arc_pay")
                    val arcPay: Int,
                    @SerializedName("autoplay")
                    val autoplay: Int,
                    @SerializedName("bp")
                    val bp: Int,
                    @SerializedName("download")
                    val download: Int,
                    @SerializedName("elec")
                    val elec: Int,
                    @SerializedName("hd5")
                    val hd5: Int,
                    @SerializedName("is_cooperation")
                    val isCooperation: Int,
                    @SerializedName("movie")
                    val movie: Int,
                    @SerializedName("no_background")
                    val noBackground: Int,
                    @SerializedName("no_reprint")
                    val noReprint: Int,
                    @SerializedName("pay")
                    val pay: Int,
                    @SerializedName("pay_free_watch")
                    val payFreeWatch: Int,
                    @SerializedName("ugc_pay")
                    val ugcPay: Int,
                    @SerializedName("ugc_pay_preview")
                    val ugcPayPreview: Int
                ) : Parcelable

                @Parcelize
                data class Stat(
                    @SerializedName("aid")
                    val aid: Int,
                    @SerializedName("coin")
                    val coin: Int,
                    @SerializedName("danmaku")
                    val danmaku: Int,
                    @SerializedName("dislike")
                    val dislike: Int,
                    @SerializedName("favorite")
                    val favorite: Int,
                    @SerializedName("his_rank")
                    val hisRank: Int,
                    @SerializedName("like")
                    val like: Int,
                    @SerializedName("now_rank")
                    val nowRank: Int,
                    @SerializedName("reply")
                    val reply: Int,
                    @SerializedName("share")
                    val share: Int,
                    @SerializedName("view")
                    val view: Int
                ) : Parcelable
            }

            @Parcelize
            data class Owner(
                @SerializedName("face")
                val face: String,
                @SerializedName("mid")
                val mid: String,
                @SerializedName("name")
                val name: String
            ) : Parcelable

            @Parcelize
            data class Rights(
                @SerializedName("arc_pay")
                val arcPay: Int,
                @SerializedName("autoplay")
                val autoplay: Int,
                @SerializedName("bp")
                val bp: Int,
                @SerializedName("download")
                val download: Int,
                @SerializedName("elec")
                val elec: Int,
                @SerializedName("hd5")
                val hd5: Int,
                @SerializedName("is_cooperation")
                val isCooperation: Int,
                @SerializedName("movie")
                val movie: Int,
                @SerializedName("no_background")
                val noBackground: Int,
                @SerializedName("no_reprint")
                val noReprint: Int,
                @SerializedName("pay")
                val pay: Int,
                @SerializedName("pay_free_watch")
                val payFreeWatch: Int,
                @SerializedName("ugc_pay")
                val ugcPay: Int,
                @SerializedName("ugc_pay_preview")
                val ugcPayPreview: Int
            ) : Parcelable

            @Parcelize
            data class Stat(
                @SerializedName("aid")
                val aid: Int,
                @SerializedName("coin")
                val coin: Int,
                @SerializedName("danmaku")
                val danmaku: Int,
                @SerializedName("dislike")
                val dislike: Int,
                @SerializedName("favorite")
                val favorite: Int,
                @SerializedName("his_rank")
                val hisRank: Int,
                @SerializedName("like")
                val like: Int,
                @SerializedName("now_rank")
                val nowRank: Int,
                @SerializedName("reply")
                val reply: Int,
                @SerializedName("share")
                val share: Int,
                @SerializedName("view")
                val view: Int
            ) : Parcelable
        }
    }
}