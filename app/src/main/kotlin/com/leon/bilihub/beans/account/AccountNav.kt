package com.leon.bilihub.beans.account

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccountNav(
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
        @SerializedName("allowance_count")
        val allowanceCount: String,
        @SerializedName("answer_status")
        val answerStatus: Int,
        @SerializedName("email_verified")
        val emailVerified: Int,
        @SerializedName("face")
        val face: String,
        @SerializedName("has_shop")
        val hasShop: Boolean,
        @SerializedName("isLogin")
        val isLogin: Boolean,
        @SerializedName("is_senior_member")
        val isSeniorMember: Int,
        @SerializedName("level_info")
        val levelInfo: LevelInfo,
        @SerializedName("mid")
        val mid: String,
        @SerializedName("mobile_verified")
        val mobileVerified: Int,
        @SerializedName("money")
        val money: String,
        @SerializedName("moral")
        val moral: String,
        @SerializedName("official")
        val official: Official,
        @SerializedName("officialVerify")
        val officialVerify: OfficialVerify,
        @SerializedName("pendant")
        val pendant: Pendant,
        @SerializedName("scores")
        val scores: String,
        @SerializedName("shop_url")
        val shopUrl: String,
        @SerializedName("uname")
        val uname: String,
        @SerializedName("vip")
        val vip: Vip,
        @SerializedName("vip_avatar_subscript")
        val vipAvatarSubscript: Int,
        @SerializedName("vipDueDate")
        val vipDueDate: Long,
        @SerializedName("vip_label")
        val vipLabel: VipLabel,
        @SerializedName("vip_nickname_color")
        val vipNicknameColor: String,
        @SerializedName("vip_pay_type")
        val vipPayType: Int,
        @SerializedName("vipStatus")
        val vipStatus: Int,
        @SerializedName("vip_theme_type")
        val vipThemeType: Int,
        @SerializedName("vipType")
        val vipType: Int,
        @SerializedName("wallet")
        val wallet: Wallet
    ) : Parcelable {
        @Parcelize
        data class LevelInfo(
            @SerializedName("current_exp")
            val currentExp: Int,
            @SerializedName("current_level")
            val currentLevel: Int,
            @SerializedName("current_min")
            val currentMin: Int,
            @SerializedName("next_exp")
            val nextExp: Int
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
        data class OfficialVerify(
            @SerializedName("desc")
            val desc: String,
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

        @Parcelize
        data class VipLabel(
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

        @Parcelize
        data class Wallet(
            @SerializedName("bcoin_balance")
            val bcoinBalance: String,
            @SerializedName("coupon_balance")
            val couponBalance: String,
            @SerializedName("coupon_due_time")
            val couponDueTime: Int,
            @SerializedName("mid")
            val mid: String
        ) : Parcelable
    }
}