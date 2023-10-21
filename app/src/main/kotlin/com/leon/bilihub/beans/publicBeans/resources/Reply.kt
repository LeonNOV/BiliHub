package com.leon.bilihub.beans.publicBeans.resources


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Reply(
    @SerializedName("code")
    val code: String,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("ttl")
    val ttl: String
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("assist")
        val assist: String,
        @SerializedName("blacklist")
        val blacklist: String,
        @SerializedName("cursor")
        val cursor: Cursor,
        @SerializedName("note")
        val note: String,
        @SerializedName("replies")
        val replies: List<Reply>,
        @SerializedName("top_replies")
        val topReplies: List<Reply>,
        @SerializedName("upper")
        val upper: Upper,
        @SerializedName("vote")
        val vote: String
    ) : Parcelable {

        @Parcelize
        data class Cursor(
            @SerializedName("all_count")
            val allCount: String,
            @SerializedName("is_begin")
            val isBegin: Boolean,
            @SerializedName("is_end")
            val isEnd: Boolean,
            @SerializedName("mode")
            val mode: String,
            @SerializedName("mode_text")
            val modeText: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("next")
            val next: String,
            @SerializedName("pagination_reply")
            val paginationReply: PaginationReply,
            @SerializedName("prev")
            val prev: String,
            @SerializedName("session_id")
            val sessionId: String,
            @SerializedName("support_mode")
            val supportMode: List<Int>
        ) : Parcelable {
            @Parcelize
            data class PaginationReply(
                @SerializedName("next_offset")
                val nextOffset: String
            ) : Parcelable
        }

        @Parcelize
        data class Reply(
            @SerializedName("action")
            val action: String,
            @SerializedName("assist")
            val assist: String,
            @SerializedName("attr")
            val attr: String,
            @SerializedName("card_label")
            val cardLabel: List<CardLabel>,
            @SerializedName("content")
            val content: Content,
            @SerializedName("count")
            val count: String,
            @SerializedName("ctime")
            val ctime: Long,
            @SerializedName("dialog")
            val dialog: String,
            @SerializedName("dynamic_id_str")
            val dynamicIdStr: String,
            @SerializedName("fansgrade")
            val fansgrade: String,
            @SerializedName("folder")
            val folder: Folder,
            @SerializedName("invisible")
            val invisible: Boolean,
            @SerializedName("like")
            val like: Int,
            @SerializedName("member")
            val member: Member,
            @SerializedName("mid")
            val mid: String,
            @SerializedName("oid")
            val oid: String,
            @SerializedName("parent")
            val parent: String,
            @SerializedName("parent_str")
            val parentStr: String,
            @SerializedName("rcount")
            val rcount: Long,
            @SerializedName("replies")
            val replies: List<Reply>,
            @SerializedName("reply_control")
            val replyControl: ReplyControl,
            @SerializedName("root")
            val root: String,
            @SerializedName("root_str")
            val rootStr: String,
            @SerializedName("rpid")
            val rpid: Long,
            @SerializedName("rpid_str")
            val rpidStr: String,
            @SerializedName("state")
            val state: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("up_action")
            val upAction: UpAction
        ) : Parcelable {
            @Parcelize
            data class CardLabel(
                @SerializedName("background")
                val background: String,
                @SerializedName("background_height")
                val backgroundHeight: String,
                @SerializedName("background_width")
                val backgroundWidth: String,
                @SerializedName("effect")
                val effect: String,
                @SerializedName("effect_start_time")
                val effectStartTime: String,
                @SerializedName("image")
                val image: String,
                @SerializedName("jump_url")
                val jumpUrl: String,
                @SerializedName("label_color_day")
                val labelColorDay: String,
                @SerializedName("label_color_night")
                val labelColorNight: String,
                @SerializedName("rpid")
                val rpid: Long,
                @SerializedName("text_color_day")
                val textColorDay: String,
                @SerializedName("text_color_night")
                val textColorNight: String,
                @SerializedName("text_content")
                val textContent: String,
                @SerializedName("type")
                val type: String
            ) : Parcelable

            @Parcelize
            data class Content(
//                @SerializedName("emote")
//                val emote: Emote,
//                @SerializedName("jump_url")
//                val jumpUrl: JumpUrl,
                @SerializedName("max_line")
                val maxLine: String,
                @SerializedName("message")
                val message: String
            ) : Parcelable

            @Parcelize
            data class Folder(
                @SerializedName("has_folded")
                val hasFolded: Boolean,
                @SerializedName("is_folded")
                val isFolded: Boolean,
                @SerializedName("rule")
                val rule: String
            ) : Parcelable

            @Parcelize
            data class Member(
                @SerializedName("avatar")
                val avatar: String,
                @SerializedName("contract_desc")
                val contractDesc: String,
                @SerializedName("face_nft_new")
                val faceNftNew: String,
                @SerializedName("is_contractor")
                val isContractor: Boolean,
                @SerializedName("is_senior_member")
                val isSeniorMember: String,
                @SerializedName("level_info")
                val levelInfo: LevelInfo,
                @SerializedName("mid")
                val mid: String,
                @SerializedName("nameplate")
                val nameplate: Nameplate,
                @SerializedName("official_verify")
                val officialVerify: OfficialVerify,
                @SerializedName("pendant")
                val pendant: Pendant,
                @SerializedName("rank")
                val rank: String,
                @SerializedName("senior")
                val senior: Senior,
                @SerializedName("sex")
                val sex: String,
                @SerializedName("sign")
                val sign: String,
                @SerializedName("uname")
                val uname: String,
                @SerializedName("vip")
                val vip: Vip
            ) : Parcelable {

                @Parcelize
                data class LevelInfo(
                    @SerializedName("current_exp")
                    val currentExp: String,
                    @SerializedName("current_level")
                    val currentLevel: String,
                    @SerializedName("current_min")
                    val currentMin: String,
                    @SerializedName("next_exp")
                    val nextExp: String
                ) : Parcelable

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
                    val nid: String
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
                    val expire: String,
                    @SerializedName("image")
                    val image: String,
                    @SerializedName("image_enhance")
                    val imageEnhance: String,
                    @SerializedName("image_enhance_frame")
                    val imageEnhanceFrame: String,
                    @SerializedName("name")
                    val name: String,
                    @SerializedName("pid")
                    val pid: String
                ) : Parcelable

                @Parcelize
                data class Senior(
                    @SerializedName("status")
                    val status: String
                ) : Parcelable

                @Parcelize
                data class Vip(
                    @SerializedName("accessStatus")
                    val accessStatus: String,
                    @SerializedName("avatar_subscript")
                    val avatarSubscript: String,
                    @SerializedName("dueRemark")
                    val dueRemark: String,
                    @SerializedName("label")
                    val label: Label,
                    @SerializedName("nickname_color")
                    val nicknameColor: String,
                    @SerializedName("themeType")
                    val themeType: String,
                    @SerializedName("vipDueDate")
                    val vipDueDate: Long,
                    @SerializedName("vipStatus")
                    val vipStatus: Int,
                    @SerializedName("vipStatusWarn")
                    val vipStatusWarn: String,
                    @SerializedName("vipType")
                    val vipType: String
                ) : Parcelable {
                    @Parcelize
                    data class Label(
                        @SerializedName("bg_color")
                        val bgColor: String,
                        @SerializedName("bg_style")
                        val bgStyle: String,
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

            @Parcelize
            data class ReplyControl(
                @SerializedName("location")
                val location: String,
                @SerializedName("max_line")
                val maxLine: String,
                @SerializedName("sub_reply_entry_text")
                val subReplyEntryText: String,
                @SerializedName("sub_reply_title_text")
                val subReplyTitleText: String,
                @SerializedName("time_desc")
                val timeDesc: String,
                @SerializedName("up_like")
                val upLike: Boolean
            ) : Parcelable

            @Parcelize
            data class UpAction(
                @SerializedName("like")
                val like: Boolean,
                @SerializedName("reply")
                val reply: Boolean
            ) : Parcelable
        }

        @Parcelize
        data class Upper(
            @SerializedName("mid")
            val mid: String
        ) : Parcelable
    }
}