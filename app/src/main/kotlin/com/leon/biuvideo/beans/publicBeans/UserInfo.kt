package com.leon.biuvideo.beans.publicBeans

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserInfo(
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
        @SerializedName("birthday")
        val birthday: String,
        @SerializedName("coins")
        val coins: Int,
        @SerializedName("face")
        val face: String,
        @SerializedName("face_nft")
        val faceNft: Int,
        @SerializedName("face_nft_type")
        val faceNftType: Int,
        @SerializedName("fans_badge")
        val fansBadge: Boolean,
        @SerializedName("is_followed")
        val isFollowed: Boolean,
        @SerializedName("is_senior_member")
        val isSeniorMember: Int,
        @SerializedName("jointime")
        val jointime: Int,
        @SerializedName("level")
        val level: Int,
        @SerializedName("live_room")
        val liveRoom: LiveRoom,
        @SerializedName("mid")
        val mid: Int,
        @SerializedName("moral")
        val moral: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("nameplate")
        val nameplate: Nameplate,
        @SerializedName("official")
        val official: Official,
        @SerializedName("pendant")
        val pendant: Pendant,
        @SerializedName("profession")
        val profession: Profession,
        @SerializedName("rank")
        val rank: Int,
        @SerializedName("school")
        val school: School,
        @SerializedName("series")
        val series: Series,
        @SerializedName("sex")
        val sex: String,
        @SerializedName("sign")
        val sign: String,
        @SerializedName("silence")
        val silence: Int,
        @SerializedName("sys_notice")
        val sysNotice: SysNotice,
        @SerializedName("tags")
        val tags: List<String>,
        @SerializedName("theme")
        val theme: Theme,
        @SerializedName("top_photo")
        val topPhoto: String,
        @SerializedName("vip")
        val vip: Vip
    ) : Parcelable {

        @Parcelize
        data class LiveRoom(
            @SerializedName("broadcast_type")
            val broadcastType: Int,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("liveStatus")
            val liveStatus: Int,
            @SerializedName("roomStatus")
            val roomStatus: Int,
            @SerializedName("roomid")
            val roomid: Int,
            @SerializedName("roundStatus")
            val roundStatus: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("url")
            val url: String,
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
        data class Nameplate(
            @SerializedName("condition")
            val condition: String,
            @SerializedName("image")
            val image: String,
            @SerializedName("image_small")
            val imageSmall: String,
            @SerializedName("level")
            val level: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("nid")
            val nid: Int
        ) : Parcelable

        @Parcelize
        data class Official(
            @SerializedName("desc")
            val desc: String,
            @SerializedName("role")
            val role: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("type")
            val type: Int
        ) : Parcelable

        @Parcelize
        data class Pendant(
            @SerializedName("expire")
            val expire: Int,
            @SerializedName("image")
            val image: String,
            @SerializedName("image_enhance")
            val imageEnhance: String,
            @SerializedName("image_enhance_frame")
            val imageEnhanceFrame: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("pid")
            val pid: Int
        ) : Parcelable

        @Parcelize
        data class Profession(
            @SerializedName("department")
            val department: String,
            @SerializedName("is_show")
            val isShow: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("title")
            val title: String
        ) : Parcelable

        @Parcelize
        data class School(
            @SerializedName("name")
            val name: String
        ) : Parcelable

        @Parcelize
        data class Series(
            @SerializedName("show_upgrade_window")
            val showUpgradeWindow: Boolean,
            @SerializedName("user_upgrade_status")
            val userUpgradeStatus: Int
        ) : Parcelable

        @Parcelize
        data class SysNotice(
            @SerializedName("id")
            val id: Int,
            @SerializedName("text_color")
            val textColor: String
        ) : Parcelable

        @Parcelize
        class Theme : Parcelable

        @Parcelize
        data class Vip(
            @SerializedName("avatar_subscript")
            val avatarSubscript: Int,
            @SerializedName("avatar_subscript_url")
            val avatarSubscriptUrl: String,
            @SerializedName("due_date")
            val dueDate: Long,
            @SerializedName("label")
            val label: Label,
            @SerializedName("nickname_color")
            val nicknameColor: String,
            @SerializedName("role")
            val role: Int,
            @SerializedName("status")
            val status: Int,
            @SerializedName("theme_type")
            val themeType: Int,
            @SerializedName("tv_vip_pay_type")
            val tvVipPayType: Int,
            @SerializedName("tv_vip_status")
            val tvVipStatus: Int,
            @SerializedName("type")
            val type: Int,
            @SerializedName("vip_pay_type")
            val vipPayType: Int
        ) : Parcelable {
            @Parcelize
            data class Label(
                @SerializedName("bg_color")
                val bgColor: String,
                @SerializedName("bg_style")
                val bgStyle: Int,
                @SerializedName("border_color")
                val borderColor: String,
                @SerializedName("img_label_uri_hans")
                val imgLabelUriHans: String,
                @SerializedName("img_label_uri_hans_static")
                val imgLabelUriHansStatic: String,
                @SerializedName("img_label_uri_hant")
                val imgLabelUriHant: String,
                @SerializedName("img_label_uri_hant_static")
                val imgLabelUriHantStatic: String,
                @SerializedName("label_theme")
                val labelTheme: String,
                @SerializedName("path")
                val path: String,
                @SerializedName("text")
                val text: String,
                @SerializedName("text_color")
                val textColor: String,
                @SerializedName("use_img_label")
                val useImgLabel: Boolean
            ) : Parcelable
        }
    }
}