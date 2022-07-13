package com.leon.biuvideo.beans.account

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RelationDetail(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("ttl")
    val ttl: Int
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("attribute")
        val attribute: Int,
        @SerializedName("contract_info")
        val contractInfo: ContractInfo,
        @SerializedName("face")
        val face: String,
        @SerializedName("face_nft")
        val faceNft: Int,
        @SerializedName("live")
        val live: Live,
        @SerializedName("mid")
        val mid: Int,
        @SerializedName("official_verify")
        val officialVerify: OfficialVerify,
        @SerializedName("sign")
        val sign: String,
        @SerializedName("special")
        val special: Int,
        @SerializedName("uname")
        val uname: String,
        @SerializedName("vip")
        val vip: Vip
    ) : Parcelable {
        @Parcelize
        data class ContractInfo(
            @SerializedName("is_contract")
            val isContract: Boolean,
            @SerializedName("is_contractor")
            val isContractor: Boolean,
            @SerializedName("ts")
            val ts: Int,
            @SerializedName("user_attr")
            val userAttr: Int
        ) : Parcelable

        @Parcelize
        data class Live(
            @SerializedName("jump_url")
            val jumpUrl: String,
            @SerializedName("live_status")
            val liveStatus: Int
        ) : Parcelable

        @Parcelize
        data class OfficialVerify(
            @SerializedName("desc")
            val desc: String,
            @SerializedName("type")
            val type: Int
        ) : Parcelable

        @Parcelize
        data class Vip(
            @SerializedName("accessStatus")
            val accessStatus: Int,
            @SerializedName("avatar_subscript")
            val avatarSubscript: Int,
            @SerializedName("avatar_subscript_url")
            val avatarSubscriptUrl: String,
            @SerializedName("dueRemark")
            val dueRemark: String,
            @SerializedName("label")
            val label: Label,
            @SerializedName("nickname_color")
            val nicknameColor: String,
            @SerializedName("themeType")
            val themeType: Int,
            @SerializedName("vipDueDate")
            val vipDueDate: Long,
            @SerializedName("vipStatus")
            val vipStatus: Int,
            @SerializedName("vipStatusWarn")
            val vipStatusWarn: String,
            @SerializedName("vipType")
            val vipType: Int
        ) : Parcelable {
            @Parcelize
            data class Label(
                @SerializedName("bg_color")
                val bgColor: String,
                @SerializedName("bg_style")
                val bgStyle: Int,
                @SerializedName("border_color")
                val borderColor: String,
                @SerializedName("label_theme")
                val labelTheme: String,
                @SerializedName("path")
                val path: String,
                @SerializedName("text")
                val text: String,
                @SerializedName("text_color")
                val textColor: String
            ) : Parcelable
        }
    }
}