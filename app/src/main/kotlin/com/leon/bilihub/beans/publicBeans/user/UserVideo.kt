package com.leon.bilihub.beans.publicBeans.user

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserVideo(
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
        val list: DataList,
        @SerializedName("page")
        val page: Page
    ) : Parcelable {

        @Parcelize
        data class DataList(
            /*@SerializedName("tlist")
            val tlist: Tlist,*/
            @SerializedName("vlist")
            val videoList: List<Video>
        ) : Parcelable {
            /*@Parcelize
            data class Tlist(
                @SerializedName("129")
                val x129: X129,
                @SerializedName("155")
                val x155: X155,
                @SerializedName("160")
                val x160: X160,
                @SerializedName("211")
                val x211: X211
            ) : Parcelable {
                @Parcelize
                data class X129(
                    @SerializedName("count")
                    val count: Int,
                    @SerializedName("name")
                    val name: String,
                    @SerializedName("tid")
                    val tid: Int
                ) : Parcelable

                @Parcelize
                data class X155(
                    @SerializedName("count")
                    val count: Int,
                    @SerializedName("name")
                    val name: String,
                    @SerializedName("tid")
                    val tid: Int
                ) : Parcelable

                @Parcelize
                data class X160(
                    @SerializedName("count")
                    val count: Int,
                    @SerializedName("name")
                    val name: String,
                    @SerializedName("tid")
                    val tid: Int
                ) : Parcelable

                @Parcelize
                data class X211(
                    @SerializedName("count")
                    val count: Int,
                    @SerializedName("name")
                    val name: String,
                    @SerializedName("tid")
                    val tid: Int
                ) : Parcelable
            }*/

            @Parcelize
            data class Video(
                @SerializedName("aid")
                val aid: Int,
                @SerializedName("author")
                val author: String,
                @SerializedName("bvid")
                val bvid: String,
                @SerializedName("comment")
                val comment: Int,
                @SerializedName("copyright")
                val copyright: String,
                @SerializedName("created")
                val created: Int,
                @SerializedName("description")
                val description: String,
                @SerializedName("hide_click")
                val hideClick: Boolean,
                @SerializedName("is_live_playback")
                val isLivePlayback: Int,
                @SerializedName("is_pay")
                val isPay: Int,
                @SerializedName("is_steins_gate")
                val isSteinsGate: Int,
                @SerializedName("is_union_video")
                val isUnionVideo: Int,
                @SerializedName("length")
                val length: String,
                @SerializedName("mid")
                val mid: String,
                @SerializedName("pic")
                val pic: String,
                @SerializedName("play")
                val play: Int,
                @SerializedName("review")
                val review: Int,
                @SerializedName("subtitle")
                val subtitle: String,
                @SerializedName("title")
                val title: String,
                @SerializedName("typeid")
                val typeid: Int,
                @SerializedName("video_review")
                val videoReview: Int
            ) : Parcelable
        }

        @Parcelize
        data class Page(
            @SerializedName("count")
            val count: Int,
            @SerializedName("pn")
            val pn: Int,
            @SerializedName("ps")
            val ps: Int
        ) : Parcelable
    }
}