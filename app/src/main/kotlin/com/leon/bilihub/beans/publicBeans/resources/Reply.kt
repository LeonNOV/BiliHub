package com.leon.bilihub.beans.publicBeans.resources

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Reply(
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
        @SerializedName("assist")
        val assist: Int,
        @SerializedName("blacklist")
        val blacklist: Int,
        @SerializedName("config")
        val config: Config,
        @SerializedName("control")
        val control: Control,
        @SerializedName("cursor")
        val cursor: Cursor,
        @SerializedName("effects")
        val effects: Effects,
        @SerializedName("note")
        val note: Int,
        @SerializedName("replies")
        val replies: List<Reply>,
        @SerializedName("up_selection")
        val upSelection: UpSelection,
        @SerializedName("upper")
        val upper: Upper,
        @SerializedName("vote")
        val vote: Int
    ) : Parcelable {

        @Parcelize
        data class Config(
            @SerializedName("read_only")
            val readOnly: Boolean,
            @SerializedName("show_up_flag")
            val showUpFlag: Boolean,
            @SerializedName("showtopic")
            val showtopic: Int
        ) : Parcelable

        @Parcelize
        data class Control(
            @SerializedName("answer_guide_android_url")
            val answerGuideAndroidUrl: String,
            @SerializedName("answer_guide_icon_url")
            val answerGuideIconUrl: String,
            @SerializedName("answer_guide_ios_url")
            val answerGuideIosUrl: String,
            @SerializedName("answer_guide_text")
            val answerGuideText: String,
            @SerializedName("bg_text")
            val bgText: String,
            @SerializedName("child_input_text")
            val childInputText: String,
            @SerializedName("disable_jump_emote")
            val disableJumpEmote: Boolean,
            @SerializedName("giveup_input_text")
            val giveupInputText: String,
            @SerializedName("input_disable")
            val inputDisable: Boolean,
            @SerializedName("root_input_text")
            val rootInputText: String,
            @SerializedName("show_text")
            val showText: String,
            @SerializedName("show_type")
            val showType: Int,
            @SerializedName("web_selection")
            val webSelection: Boolean
        ) : Parcelable

        @Parcelize
        data class Cursor(
            @SerializedName("all_count")
            val allCount: Int,
            @SerializedName("is_begin")
            val isBegin: Boolean,
            @SerializedName("is_end")
            val isEnd: Boolean,
            @SerializedName("mode")
            val mode: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("next")
            val next: Int,
            @SerializedName("prev")
            val prev: Int,
            @SerializedName("show_type")
            val showType: Int,
            @SerializedName("support_mode")
            val supportMode: List<Int>
        ) : Parcelable

        @Parcelize
        data class Effects(
            @SerializedName("preloading")
            val preloading: String
        ) : Parcelable

        @Parcelize
        data class Reply(
            @SerializedName("action")
            val action: Int,
            @SerializedName("assist")
            val assist: Int,
            @SerializedName("attr")
            val attr: Int,
            @SerializedName("card_label")
            val cardLabel: List<CardLabel>?,
            @SerializedName("content")
            val content: Content,
            @SerializedName("count")
            val count: Int,
            @SerializedName("ctime")
            val ctime: Int,
            @SerializedName("dialog")
            val dialog: Int,
            @SerializedName("fansgrade")
            val fansgrade: Int,
            @SerializedName("invisible")
            val invisible: Boolean,
            @SerializedName("like")
            val like: Int,
            @SerializedName("member")
            val member: Member,
            @SerializedName("mid")
            val mid: String,
            @SerializedName("oid")
            val oid: Int,
            @SerializedName("parent")
            val parent: Int,
            @SerializedName("parent_str")
            val parentStr: String,
            @SerializedName("rcount")
            val rcount: Int,
            @SerializedName("replies")
            val replies: List<Reply>?,
            @SerializedName("reply_control")
            val replyControl: ReplyControl,
            @SerializedName("root")
            val root: Int,
            @SerializedName("root_str")
            val rootStr: String,
            @SerializedName("rpid")
            val rpid: Long,
            @SerializedName("rpid_str")
            val rpidStr: String,
            @SerializedName("show_follow")
            val showFollow: Boolean,
            @SerializedName("state")
            val state: Int,
            @SerializedName("type")
            val type: Int,
            @SerializedName("up_action")
            val upAction: UpAction
        ) : Parcelable {
            @Parcelize
            data class CardLabel(
                @SerializedName("background")
                val background: String,
                @SerializedName("background_height")
                val backgroundHeight: Int,
                @SerializedName("background_width")
                val backgroundWidth: Int,
                @SerializedName("effect")
                val effect: Int,
                @SerializedName("effect_start_time")
                val effectStartTime: Int,
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
                val type: Int
            ) : Parcelable

            @Parcelize
            data class Content(
                @SerializedName("device")
                val device: String,
                @SerializedName("max_line")
                val maxLine: Int,
                @SerializedName("message")
                val message: String,
                @SerializedName("plat")
                val plat: Int
            ) : Parcelable

            @Parcelize
            data class Member(
                @SerializedName("avatar")
                val avatar: String,
                @SerializedName("contract_desc")
                val contractDesc: String,
                @SerializedName("DisplayRank")
                val displayRank: String,
                @SerializedName("face_nft_new")
                val faceNftNew: Int,
                @SerializedName("fans_detail")
                val fansDetail: FansDetail?,
                @SerializedName("following")
                val following: Int,
                @SerializedName("is_contractor")
                val isContractor: Boolean,
                @SerializedName("is_followed")
                val isFollowed: Int,
                @SerializedName("is_senior_member")
                val isSeniorMember: Int,
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
                @SerializedName("sex")
                val sex: String,
                @SerializedName("sign")
                val sign: String,
                @SerializedName("uname")
                val uname: String,
                @SerializedName("user_sailing")
                val userSailing: UserSailing,
                @SerializedName("vip")
                val vip: Vip
            ) : Parcelable {
                @Parcelize
                data class FansDetail(
                    @SerializedName("guard_icon")
                    val guardIcon: String,
                    @SerializedName("guard_level")
                    val guardLevel: Int,
                    @SerializedName("honor_icon")
                    val honorIcon: String,
                    @SerializedName("intimacy")
                    val intimacy: Int,
                    @SerializedName("is_receive")
                    val isReceive: Int,
                    @SerializedName("level")
                    val level: Int,
                    @SerializedName("master_status")
                    val masterStatus: Int,
                    @SerializedName("medal_color")
                    val medalColor: Int,
                    @SerializedName("medal_color_border")
                    val medalColorBorder: Long,
                    @SerializedName("medal_color_end")
                    val medalColorEnd: Int,
                    @SerializedName("medal_color_level")
                    val medalColorLevel: Long,
                    @SerializedName("medal_color_name")
                    val medalColorName: Long,
                    @SerializedName("medal_id")
                    val medalId: Int,
                    @SerializedName("medal_name")
                    val medalName: String,
                    @SerializedName("score")
                    val score: Int,
                    @SerializedName("uid")
                    val uid: String
                ) : Parcelable

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
                data class UserSailing(
                    @SerializedName("cardbg")
                    val cardbg: Cardbg?,
                    @SerializedName("pendant")
                    val pendant: Pendant?
                ) : Parcelable {
                    @Parcelize
                    data class Cardbg(
                        @SerializedName("fan")
                        val fan: Fan,
                        @SerializedName("id")
                        val id: String,
                        @SerializedName("image")
                        val image: String,
                        @SerializedName("jump_url")
                        val jumpUrl: String,
                        @SerializedName("name")
                        val name: String,
                        @SerializedName("type")
                        val type: String
                    ) : Parcelable {
                        @Parcelize
                        data class Fan(
                            @SerializedName("color")
                            val color: String,
                            @SerializedName("is_fan")
                            val isFan: Int,
                            @SerializedName("name")
                            val name: String,
                            @SerializedName("num_desc")
                            val numDesc: String,
                            @SerializedName("number")
                            val number: Int
                        ) : Parcelable
                    }

                    @Parcelize
                    data class Pendant(
                        @SerializedName("id")
                        val id: Int,
                        @SerializedName("image")
                        val image: String,
                        @SerializedName("image_enhance")
                        val imageEnhance: String,
                        @SerializedName("image_enhance_frame")
                        val imageEnhanceFrame: String,
                        @SerializedName("jump_url")
                        val jumpUrl: String,
                        @SerializedName("name")
                        val name: String,
                        @SerializedName("type")
                        val type: String
                    ) : Parcelable
                }

                @Parcelize
                data class Vip(
                    @SerializedName("accessStatus")
                    val accessStatus: Int,
                    @SerializedName("avatar_subscript")
                    val avatarSubscript: Int,
                    @SerializedName("dueRemark")
                    val dueRemark: String,
                    @SerializedName("label")
                    val label: Label,
                    @SerializedName("nickname_color")
                    val nicknameColor: String,
                    @SerializedName("themeType")
                    val themeType: Int,
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
            data class Reply(
                @SerializedName("action")
                val action: Int,
                @SerializedName("assist")
                val assist: Int,
                @SerializedName("attr")
                val attr: Int,
                @SerializedName("content")
                val content: Content,
                @SerializedName("count")
                val count: Int,
                @SerializedName("ctime")
                val ctime: Int,
                @SerializedName("dialog")
                val dialog: Long,
                @SerializedName("fansgrade")
                val fansgrade: Int,
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
                val oid: Int,
                @SerializedName("parent")
                val parent: Long,
                @SerializedName("parent_str")
                val parentStr: String,
                @SerializedName("rcount")
                val rcount: Int,
                @SerializedName("reply_control")
                val replyControl: ReplyControl,
                @SerializedName("root")
                val root: Long,
                @SerializedName("root_str")
                val rootStr: String,
                @SerializedName("rpid")
                val rpid: Long,
                @SerializedName("rpid_str")
                val rpidStr: String,
                @SerializedName("show_follow")
                val showFollow: Boolean,
                @SerializedName("state")
                val state: Int,
                @SerializedName("type")
                val type: Int,
                @SerializedName("up_action")
                val upAction: UpAction
            ) : Parcelable {
                @Parcelize
                data class Content(
                    @SerializedName("device")
                    val device: String,
                    @SerializedName("max_line")
                    val maxLine: Int,
                    @SerializedName("members")
                    val members: List<Member>,
                    @SerializedName("message")
                    val message: String,
                    @SerializedName("plat")
                    val plat: Int
                ) : Parcelable {

                    @Parcelize
                    data class Member(
                        @SerializedName("avatar")
                        val avatar: String,
                        @SerializedName("DisplayRank")
                        val displayRank: String,
                        @SerializedName("face_nft_new")
                        val faceNftNew: Int,
                        @SerializedName("is_senior_member")
                        val isSeniorMember: Int,
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
                            val currentExp: Int,
                            @SerializedName("current_level")
                            val currentLevel: Int,
                            @SerializedName("current_min")
                            val currentMin: Int,
                            @SerializedName("next_exp")
                            val nextExp: Int
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
                            val nid: Int
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
                            @SerializedName("accessStatus")
                            val accessStatus: Int,
                            @SerializedName("avatar_subscript")
                            val avatarSubscript: Int,
                            @SerializedName("dueRemark")
                            val dueRemark: String,
                            @SerializedName("label")
                            val label: Label,
                            @SerializedName("nickname_color")
                            val nicknameColor: String,
                            @SerializedName("themeType")
                            val themeType: Int,
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
                    @SerializedName("DisplayRank")
                    val displayRank: String,
                    @SerializedName("face_nft_new")
                    val faceNftNew: Int,
                    @SerializedName("fans_detail")
                    val fansDetail: FansDetail?,
                    @SerializedName("following")
                    val following: Int,
                    @SerializedName("is_contractor")
                    val isContractor: Boolean,
                    @SerializedName("is_followed")
                    val isFollowed: Int,
                    @SerializedName("is_senior_member")
                    val isSeniorMember: Int,
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
                    @SerializedName("sex")
                    val sex: String,
                    @SerializedName("sign")
                    val sign: String,
                    @SerializedName("uname")
                    val uname: String,
                    @SerializedName("user_sailing")
                    val userSailing: UserSailing,
                    @SerializedName("vip")
                    val vip: Vip
                ) : Parcelable {
                    @Parcelize
                    data class FansDetail(
                        @SerializedName("guard_icon")
                        val guardIcon: String,
                        @SerializedName("guard_level")
                        val guardLevel: Int,
                        @SerializedName("honor_icon")
                        val honorIcon: String,
                        @SerializedName("intimacy")
                        val intimacy: Int,
                        @SerializedName("is_receive")
                        val isReceive: Int,
                        @SerializedName("level")
                        val level: Int,
                        @SerializedName("master_status")
                        val masterStatus: Int,
                        @SerializedName("medal_color")
                        val medalColor: Int,
                        @SerializedName("medal_color_border")
                        val medalColorBorder: Long,
                        @SerializedName("medal_color_end")
                        val medalColorEnd: Int,
                        @SerializedName("medal_color_level")
                        val medalColorLevel: Long,
                        @SerializedName("medal_color_name")
                        val medalColorName: Long,
                        @SerializedName("medal_id")
                        val medalId: Int,
                        @SerializedName("medal_name")
                        val medalName: String,
                        @SerializedName("score")
                        val score: Int,
                        @SerializedName("uid")
                        val uid: String
                    ) : Parcelable

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
                    data class UserSailing(
                        @SerializedName("cardbg")
                        val cardbg: Cardbg?,
                        @SerializedName("pendant")
                        val pendant: Pendant?
                    ) : Parcelable {
                        @Parcelize
                        data class Cardbg(
                            @SerializedName("fan")
                            val fan: Fan,
                            @SerializedName("id")
                            val id: Int,
                            @SerializedName("image")
                            val image: String,
                            @SerializedName("jump_url")
                            val jumpUrl: String,
                            @SerializedName("name")
                            val name: String,
                            @SerializedName("type")
                            val type: String
                        ) : Parcelable {
                            @Parcelize
                            data class Fan(
                                @SerializedName("color")
                                val color: String,
                                @SerializedName("is_fan")
                                val isFan: Int,
                                @SerializedName("name")
                                val name: String,
                                @SerializedName("num_desc")
                                val numDesc: String,
                                @SerializedName("number")
                                val number: Int
                            ) : Parcelable
                        }

                        @Parcelize
                        data class Pendant(
                            @SerializedName("id")
                            val id: Int,
                            @SerializedName("image")
                            val image: String,
                            @SerializedName("image_enhance")
                            val imageEnhance: String,
                            @SerializedName("image_enhance_frame")
                            val imageEnhanceFrame: String,
                            @SerializedName("jump_url")
                            val jumpUrl: String,
                            @SerializedName("name")
                            val name: String,
                            @SerializedName("type")
                            val type: String
                        ) : Parcelable
                    }

                    @Parcelize
                    data class Vip(
                        @SerializedName("accessStatus")
                        val accessStatus: Int,
                        @SerializedName("avatar_subscript")
                        val avatarSubscript: Int,
                        @SerializedName("dueRemark")
                        val dueRemark: String,
                        @SerializedName("label")
                        val label: Label,
                        @SerializedName("nickname_color")
                        val nicknameColor: String,
                        @SerializedName("themeType")
                        val themeType: Int,
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
                    @SerializedName("time_desc")
                    val timeDesc: String
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
            data class ReplyControl(
                @SerializedName("location")
                val location: String,
                @SerializedName("sub_reply_entry_text")
                val subReplyEntryText: String?,
                @SerializedName("sub_reply_title_text")
                val subReplyTitleText: String?,
                @SerializedName("time_desc")
                val timeDesc: String,
                @SerializedName("up_like")
                val upLike: Boolean?,
                @SerializedName("up_reply")
                val upReply: Boolean?
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
        data class UpSelection(
            @SerializedName("ignore_count")
            val ignoreCount: Int,
            @SerializedName("pending_count")
            val pendingCount: Int
        ) : Parcelable

        @Parcelize
        data class Upper(
            @SerializedName("mid")
            val mid: String
        ) : Parcelable
    }
}