package com.leon.bilihub.beans.publicBeans.resources.video

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoDetail(
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
        @SerializedName("Card")
        val card: Card,
        @SerializedName("Related")
        val related: List<Related>,
        @SerializedName("Reply")
        val reply: Reply,
        @SerializedName("Tags")
        val tags: List<Tag>,
        @SerializedName("View")
        val view: View,
        @SerializedName("view_addit")
        val viewAddit: ViewAddit
    ) : Parcelable {
        @Parcelize
        data class Card(
            @SerializedName("archive_count")
            val archiveCount: Int,
            @SerializedName("article_count")
            val articleCount: Int,
            @SerializedName("card")
            val card: Card,
            @SerializedName("follower")
            val follower: Int,
            @SerializedName("following")
            val following: Boolean,
            @SerializedName("like_num")
            val likeNum: Int
        ) : Parcelable {
            @Parcelize
            data class Card(
                @SerializedName("approve")
                val approve: Boolean,
                @SerializedName("article")
                val article: Int,
                @SerializedName("attention")
                val attention: Int,
                @SerializedName("birthday")
                val birthday: String,
                @SerializedName("description")
                val description: String,
                @SerializedName("DisplayRank")
                val displayRank: String,
                @SerializedName("face")
                val face: String,
                @SerializedName("face_nft")
                val faceNft: Int,
                @SerializedName("face_nft_type")
                val faceNftType: Int,
                @SerializedName("fans")
                val fans: Int,
                @SerializedName("friend")
                val friend: Int,
                @SerializedName("is_senior_member")
                val isSeniorMember: Int,
                @SerializedName("level_info")
                val levelInfo: LevelInfo,
                @SerializedName("mid")
                val mid: String,
                @SerializedName("name")
                val name: String,
                @SerializedName("nameplate")
                val nameplate: Nameplate,
                @SerializedName("Official")
                val official: Official,
                @SerializedName("official_verify")
                val officialVerify: OfficialVerify,
                @SerializedName("pendant")
                val pendant: Pendant,
                @SerializedName("place")
                val place: String,
                @SerializedName("rank")
                val rank: String,
                @SerializedName("regtime")
                val regtime: Int,
                @SerializedName("sex")
                val sex: String,
                @SerializedName("sign")
                val sign: String,
                @SerializedName("spacesta")
                val spacesta: Int,
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
                    val vipPayType: Int,
                    @SerializedName("vipStatus")
                    val vipStatus: Int,
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
        data class Related(
            @SerializedName("aid")
            val aid: String,
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
            val firstFrame: String?,
            @SerializedName("is_ogv")
            val isOgv: Boolean,
            @SerializedName("mission_id")
            val missionId: Int?,
            @SerializedName("owner")
            val owner: Owner,
            @SerializedName("pic")
            val pic: String,
            @SerializedName("pub_location")
            val pubLocation: String?,
            @SerializedName("pubdate")
            val pubdate: Int,
            @SerializedName("rcmd_reason")
            val rcmdReason: String,
            @SerializedName("rights")
            val rights: Rights,
            @SerializedName("season_id")
            val seasonId: Int?,
            @SerializedName("season_type")
            val seasonType: Int,
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
                val aid: String,
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
        data class Reply(
            @SerializedName("page")
            val page: Page,
            @SerializedName("replies")
            val replies: List<Reply>
        ) : Parcelable {
            @Parcelize
            data class Page(
                @SerializedName("acount")
                val acount: Int,
                @SerializedName("count")
                val count: Int,
                @SerializedName("num")
                val num: Int,
                @SerializedName("size")
                val size: Int
            ) : Parcelable

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
                val dialog: Int,
                @SerializedName("fansgrade")
                val fansgrade: Int,
                @SerializedName("floor")
                val floor: Int,
                @SerializedName("like")
                val like: Int,
                @SerializedName("mid")
                val mid: String,
                @SerializedName("oid")
                val oid: Int,
                @SerializedName("parent")
                val parent: Int,
                @SerializedName("rcount")
                val rcount: Int,
                @SerializedName("root")
                val root: Int,
                @SerializedName("rpid")
                val rpid: Long,
                @SerializedName("show_follow")
                val showFollow: Boolean,
                @SerializedName("state")
                val state: Int,
                @SerializedName("type")
                val type: Int
            ) : Parcelable {
                @Parcelize
                data class Content(
                    @SerializedName("device")
                    val device: String,
                    @SerializedName("message")
                    val message: String,
                    @SerializedName("plat")
                    val plat: Int
                ) : Parcelable
            }
        }

        @Parcelize
        data class Tag(
            @SerializedName("alpha")
            val alpha: Int,
            @SerializedName("archive_count")
            val archiveCount: String,
            @SerializedName("attribute")
            val attribute: Int,
            @SerializedName("color")
            val color: String,
            @SerializedName("content")
            val content: String,
            @SerializedName("count")
            val count: Count,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("ctime")
            val ctime: Int,
            @SerializedName("extra_attr")
            val extraAttr: Int,
            @SerializedName("featured_count")
            val featuredCount: Int,
            @SerializedName("hated")
            val hated: Int,
            @SerializedName("hates")
            val hates: Int,
            @SerializedName("head_cover")
            val headCover: String,
            @SerializedName("is_activity")
            val isActivity: Boolean,
            @SerializedName("is_atten")
            val isAtten: Int,
            @SerializedName("is_season")
            val isSeason: Boolean,
            @SerializedName("jump_url")
            val jumpUrl: String,
            @SerializedName("liked")
            val liked: Int,
            @SerializedName("likes")
            val likes: Int,
            @SerializedName("music_id")
            val musicId: String,
            @SerializedName("short_content")
            val shortContent: String,
            @SerializedName("state")
            val state: Int,
            @SerializedName("subscribed_count")
            val subscribedCount: Int,
            @SerializedName("tag_id")
            val tagId: Int,
            @SerializedName("tag_name")
            val tagName: String,
            @SerializedName("tag_type")
            val tagType: String,
            @SerializedName("type")
            val type: Int
        ) : Parcelable {
            @Parcelize
            data class Count(
                @SerializedName("atten")
                val atten: Int,
                @SerializedName("use")
                val use: Int,
                @SerializedName("view")
                val view: Int
            ) : Parcelable
        }

        @Parcelize
        data class View(
            @SerializedName("aid")
            val aid: String,
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
            @SerializedName("honor_reply")
            val honorReply: HonorReply,
            @SerializedName("is_chargeable_season")
            val isChargeableSeason: Boolean,
            @SerializedName("is_season_display")
            val isSeasonDisplay: Boolean,
            @SerializedName("is_story")
            val isStory: Boolean,
            @SerializedName("mission_id")
            val missionId: Int,
            @SerializedName("no_cache")
            val noCache: Boolean,
            @SerializedName("owner")
            val owner: Owner,
            @SerializedName("pages")
            val pages: List<Page>,
            @SerializedName("pic")
            val pic: String,
            @SerializedName("pubdate")
            val pubdate: Int,
            @SerializedName("rights")
            val rights: Rights,
            @SerializedName("season_id")
            val seasonId: Int,
            @SerializedName("staff")
            val staff: List<Staff>,
            @SerializedName("stat")
            val stat: Stat,
            @SerializedName("state")
            val state: Int,
            @SerializedName("subtitle")
            val subtitle: Subtitle,
            @SerializedName("teenage_mode")
            val teenageMode: Int,
            @SerializedName("tid")
            val tid: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("tname")
            val tname: String,
            @SerializedName("ugc_season")
            val ugcSeason: UgcSeason,
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
            data class HonorReply(
                @SerializedName("honor")
                val honor: List<Honor>
            ) : Parcelable {
                @Parcelize
                data class Honor(
                    @SerializedName("aid")
                    val aid: String,
                    @SerializedName("desc")
                    val desc: String,
                    @SerializedName("type")
                    val type: Int,
                    @SerializedName("weekly_recommend_num")
                    val weeklyRecommendNum: Int
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
            data class Page(
                @SerializedName("cid")
                val cid: String,
                @SerializedName("dimension")
                val dimension: Dimension,
                @SerializedName("duration")
                val duration: Int,
                @SerializedName("first_frame")
                val firstFrame: String,
                @SerializedName("from")
                val from: String,
                @SerializedName("page")
                val page: Int,
                @SerializedName("part")
                val part: String,
                @SerializedName("vid")
                val vid: String,
                @SerializedName("weblink")
                val weblink: String,
                var itemState: ItemState
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
                data class ItemState(var itemColor: Int, var selected: Boolean) : Parcelable
            }

            @Parcelize
            data class Rights(
                @SerializedName("arc_pay")
                val arcPay: Int,
                @SerializedName("autoplay")
                val autoplay: Int,
                @SerializedName("bp")
                val bp: Int,
                @SerializedName("clean_mode")
                val cleanMode: Int,
                @SerializedName("download")
                val download: Int,
                @SerializedName("elec")
                val elec: Int,
                @SerializedName("free_watch")
                val freeWatch: Int,
                @SerializedName("hd5")
                val hd5: Int,
                @SerializedName("is_360")
                val is360: Int,
                @SerializedName("is_cooperation")
                val isCooperation: Int,
                @SerializedName("is_stein_gate")
                val isSteinGate: Int,
                @SerializedName("movie")
                val movie: Int,
                @SerializedName("no_background")
                val noBackground: Int,
                @SerializedName("no_reprint")
                val noReprint: Int,
                @SerializedName("no_share")
                val noShare: Int,
                @SerializedName("pay")
                val pay: Int,
                @SerializedName("ugc_pay")
                val ugcPay: Int,
                @SerializedName("ugc_pay_preview")
                val ugcPayPreview: Int
            ) : Parcelable

            @Parcelize
            data class Staff(
                @SerializedName("face")
                val face: String,
                @SerializedName("follower")
                val follower: Int,
                @SerializedName("label_style")
                val labelStyle: Int,
                @SerializedName("mid")
                val mid: String,
                @SerializedName("name")
                val name: String,
                @SerializedName("official")
                val official: Official,
                @SerializedName("title")
                val title: String,
                @SerializedName("vip")
                val vip: Vip
            ) : Parcelable {
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

            @Parcelize
            data class Stat(
                @SerializedName("aid")
                val aid: String,
                @SerializedName("argue_msg")
                val argueMsg: String,
                @SerializedName("coin")
                val coin: Int,
                @SerializedName("danmaku")
                val danmaku: Int,
                @SerializedName("dislike")
                val dislike: Int,
                @SerializedName("evaluation")
                val evaluation: String,
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

            @Parcelize
            data class Subtitle(
                @SerializedName("allow_submit")
                val allowSubmit: Boolean,
                @SerializedName("list")
                val list: List<Data>
            ) : Parcelable {
                @Parcelize
                data class Data(
                    @SerializedName("ai_status")
                    val aiStatus: Int,
                    @SerializedName("ai_type")
                    val aiType: Int,
                    @SerializedName("author")
                    val author: Author,
                    @SerializedName("id")
                    val id: String,
                    @SerializedName("id_str")
                    val idStr: String,
                    @SerializedName("is_lock")
                    val isLock: Boolean,
                    @SerializedName("lan")
                    val lan: String,
                    @SerializedName("lan_doc")
                    val lanDoc: String,
                    @SerializedName("subtitle_url")
                    val subtitleUrl: String,
                    @SerializedName("type")
                    val type: Int,
                    var selected: Boolean
                ) : Parcelable {
                    @Parcelize
                    data class Author(
                        @SerializedName("birthday")
                        val birthday: Int,
                        @SerializedName("face")
                        val face: String,
                        @SerializedName("in_reg_audit")
                        val inRegAudit: Int,
                        @SerializedName("is_deleted")
                        val isDeleted: Int,
                        @SerializedName("is_fake_account")
                        val isFakeAccount: Int,
                        @SerializedName("is_senior_member")
                        val isSeniorMember: Int,
                        @SerializedName("mid")
                        val mid: Int,
                        @SerializedName("name")
                        val name: String,
                        @SerializedName("rank")
                        val rank: Int,
                        @SerializedName("sex")
                        val sex: String,
                        @SerializedName("sign")
                        val sign: String
                    ) : Parcelable
                }
            }

            @Parcelize
            data class UgcSeason(
                @SerializedName("attribute")
                val attribute: Int,
                @SerializedName("cover")
                val cover: String,
                @SerializedName("ep_count")
                val epCount: Int,
                @SerializedName("id")
                val id: Int,
                @SerializedName("intro")
                val intro: String,
                @SerializedName("is_pay_season")
                val isPaySeason: Boolean,
                @SerializedName("mid")
                val mid: String,
                @SerializedName("season_type")
                val seasonType: Int,
                @SerializedName("sections")
                val sections: List<Section>,
                @SerializedName("sign_state")
                val signState: Int,
                @SerializedName("stat")
                val stat: Stat,
                @SerializedName("title")
                val title: String
            ) : Parcelable {
                @Parcelize
                data class Section(
                    @SerializedName("episodes")
                    val episodes: List<Episode>,
                    @SerializedName("id")
                    val id: Int,
                    @SerializedName("season_id")
                    val seasonId: Int,
                    @SerializedName("title")
                    val title: String,
                    @SerializedName("type")
                    val type: Int
                ) : Parcelable {
                    @Parcelize
                    data class Episode(
                        @SerializedName("aid")
                        val aid: String,
                        @SerializedName("arc")
                        val arc: Arc,
                        @SerializedName("attribute")
                        val attribute: Int,
                        @SerializedName("bvid")
                        val bvid: String,
                        @SerializedName("cid")
                        val cid: String,
                        @SerializedName("id")
                        val id: Int,
                        @SerializedName("page")
                        val page: Page,
                        @SerializedName("season_id")
                        val seasonId: Int,
                        @SerializedName("section_id")
                        val sectionId: Int,
                        @SerializedName("title")
                        val title: String
                    ) : Parcelable {
                        @Parcelize
                        data class Arc(
                            @SerializedName("aid")
                            val aid: String,
                            @SerializedName("author")
                            val author: Author,
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
                            @SerializedName("is_chargeable_season")
                            val isChargeableSeason: Boolean,
                            @SerializedName("pic")
                            val pic: String,
                            @SerializedName("pubdate")
                            val pubdate: Int,
                            @SerializedName("rights")
                            val rights: Rights,
                            @SerializedName("stat")
                            val stat: Stat,
                            @SerializedName("state")
                            val state: Int,
                            @SerializedName("title")
                            val title: String,
                            @SerializedName("type_id")
                            val typeId: Int,
                            @SerializedName("type_name")
                            val typeName: String,
                            @SerializedName("videos")
                            val videos: Int
                        ) : Parcelable {
                            @Parcelize
                            data class Author(
                                @SerializedName("face")
                                val face: String,
                                @SerializedName("mid")
                                val mid: String,
                                @SerializedName("name")
                                val name: String
                            ) : Parcelable

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
                                @SerializedName("free_watch")
                                val freeWatch: Int,
                                @SerializedName("hd5")
                                val hd5: Int,
                                @SerializedName("is_cooperation")
                                val isCooperation: Int,
                                @SerializedName("movie")
                                val movie: Int,
                                @SerializedName("no_reprint")
                                val noReprint: Int,
                                @SerializedName("pay")
                                val pay: Int,
                                @SerializedName("ugc_pay")
                                val ugcPay: Int,
                                @SerializedName("ugc_pay_preview")
                                val ugcPayPreview: Int
                            ) : Parcelable

                            @Parcelize
                            data class Stat(
                                @SerializedName("aid")
                                val aid: String,
                                @SerializedName("argue_msg")
                                val argueMsg: String,
                                @SerializedName("coin")
                                val coin: Int,
                                @SerializedName("danmaku")
                                val danmaku: Int,
                                @SerializedName("dislike")
                                val dislike: Int,
                                @SerializedName("evaluation")
                                val evaluation: String,
                                @SerializedName("fav")
                                val fav: Int,
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
                        data class Page(
                            @SerializedName("cid")
                            val cid: String,
                            @SerializedName("dimension")
                            val dimension: Dimension,
                            @SerializedName("duration")
                            val duration: Int,
                            @SerializedName("from")
                            val from: String,
                            @SerializedName("page")
                            val page: Int,
                            @SerializedName("part")
                            val part: String,
                            @SerializedName("vid")
                            val vid: String,
                            @SerializedName("weblink")
                            val weblink: String
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
                        }
                    }
                }

                @Parcelize
                data class Stat(
                    @SerializedName("coin")
                    val coin: Int,
                    @SerializedName("danmaku")
                    val danmaku: Int,
                    @SerializedName("fav")
                    val fav: Int,
                    @SerializedName("his_rank")
                    val hisRank: Int,
                    @SerializedName("like")
                    val like: Int,
                    @SerializedName("now_rank")
                    val nowRank: Int,
                    @SerializedName("reply")
                    val reply: Int,
                    @SerializedName("season_id")
                    val seasonId: Int,
                    @SerializedName("share")
                    val share: Int,
                    @SerializedName("view")
                    val view: Int
                ) : Parcelable
            }
        }

        @Parcelize
        data class ViewAddit(
            @SerializedName("63")
            val x63: Boolean,
            @SerializedName("64")
            val x64: Boolean
        ) : Parcelable
    }
}