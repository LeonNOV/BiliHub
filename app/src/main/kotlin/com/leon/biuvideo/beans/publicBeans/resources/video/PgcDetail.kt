package com.leon.biuvideo.beans.publicBeans.resources.video

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PgcDetail(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: Result
) : Parcelable {
    @Parcelize
    data class Result(
        @SerializedName("activity")
        val activity: Activity,
        @SerializedName("alias")
        val alias: String,
        @SerializedName("areas")
        val areas: List<Area>,
        @SerializedName("bkg_cover")
        val bkgCover: String,
        @SerializedName("cover")
        val cover: String,
        @SerializedName("episodes")
        val episodes: List<Episode>,
        @SerializedName("evaluate")
        val evaluate: String,
        @SerializedName("freya")
        val freya: Freya,
        @SerializedName("jp_title")
        val jpTitle: String,
        @SerializedName("link")
        val link: String,
        @SerializedName("media_id")
        val mediaId: Int,
        @SerializedName("mode")
        val mode: Int,
        @SerializedName("new_ep")
        val newEp: NewEp,
        @SerializedName("payment")
        val payment: Payment,
        @SerializedName("positive")
        val positive: Positive,
        @SerializedName("publish")
        val publish: Publish,
        @SerializedName("rating")
        val rating: Rating?,
        @SerializedName("record")
        val record: String,
        @SerializedName("rights")
        val rights: Rights,
        @SerializedName("season_id")
        val seasonId: String,
        @SerializedName("season_title")
        val seasonTitle: String,
        @SerializedName("seasons")
        val seasons: List<Season>,
        @SerializedName("section")
        val section: List<Section>?,
        @SerializedName("series")
        val series: Series,
        @SerializedName("share_copy")
        val shareCopy: String,
        @SerializedName("share_sub_title")
        val shareSubTitle: String,
        @SerializedName("share_url")
        val shareUrl: String,
        @SerializedName("show")
        val show: Show,
        @SerializedName("show_season_type")
        val showSeasonType: Int,
        @SerializedName("square_cover")
        val squareCover: String,
        @SerializedName("stat")
        val stat: Stat,
        @SerializedName("status")
        val status: Int,
        @SerializedName("subtitle")
        val subtitle: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("total")
        val total: Int,
        @SerializedName("type")
        val type: Int,
        @SerializedName("up_info")
        val upInfo: UpInfo,
        @SerializedName("user_status")
        val userStatus: UserStatus
    ) : Parcelable {
        @Parcelize
        data class Activity(
            @SerializedName("head_bg_url")
            val headBgUrl: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("title")
            val title: String
        ) : Parcelable

        @Parcelize
        data class Area(
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String
        ) : Parcelable

        @Parcelize
        data class Episode(
            @SerializedName("aid")
            val aid: Int,
            @SerializedName("badge")
            val badge: String,
            @SerializedName("badge_info")
            val badgeInfo: BadgeInfo,
            @SerializedName("badge_type")
            val badgeType: Int,
            @SerializedName("bvid")
            val bvid: String,
            @SerializedName("cid")
            val cid: String,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("dimension")
            val dimension: Dimension,
            @SerializedName("duration")
            val duration: Int,
            @SerializedName("from")
            val from: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("is_view_hide")
            val isViewHide: Boolean,
            @SerializedName("link")
            val link: String,
            @SerializedName("long_title")
            val longTitle: String,
            @SerializedName("pub_time")
            val pubTime: Int,
            @SerializedName("pv")
            val pv: Int,
            @SerializedName("release_date")
            val releaseDate: String,
            @SerializedName("rights")
            val rights: Rights,
            @SerializedName("share_copy")
            val shareCopy: String,
            @SerializedName("share_url")
            val shareUrl: String,
            @SerializedName("short_link")
            val shortLink: String,
            @SerializedName("status")
            val status: Int,
            @SerializedName("subtitle")
            val subtitle: String,
            @SerializedName("title")
            val title: String,
            @SerializedName("vid")
            val vid: String,
            var itemState: ItemState
        ) : Parcelable {
            @Parcelize
            data class BadgeInfo(
                @SerializedName("bg_color")
                val bgColor: String,
                @SerializedName("bg_color_night")
                val bgColorNight: String,
                @SerializedName("text")
                val text: String
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
                @SerializedName("allow_demand")
                val allowDemand: Int,
                @SerializedName("allow_dm")
                val allowDm: Int,
                @SerializedName("allow_download")
                val allowDownload: Int,
                @SerializedName("area_limit")
                val areaLimit: Int
            ) : Parcelable

            @Parcelize
            data class ItemState(
                var epColor: Int,
                var epSelected: Boolean
            ) : Parcelable
        }

        @Parcelize
        data class Freya(
            @SerializedName("bubble_desc")
            val bubbleDesc: String,
            @SerializedName("bubble_show_cnt")
            val bubbleShowCnt: Int,
            @SerializedName("icon_show")
            val iconShow: Int
        ) : Parcelable

        @Parcelize
        data class NewEp(
            @SerializedName("desc")
            val desc: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("is_new")
            val isNew: Int,
            @SerializedName("title")
            val title: String
        ) : Parcelable

        @Parcelize
        data class Payment(
            @SerializedName("discount")
            val discount: Int,
            @SerializedName("pay_type")
            val payType: PayType,
            @SerializedName("price")
            val price: String,
            @SerializedName("promotion")
            val promotion: String,
            @SerializedName("tip")
            val tip: String,
            @SerializedName("view_start_time")
            val viewStartTime: Int,
            @SerializedName("vip_discount")
            val vipDiscount: Int,
            @SerializedName("vip_first_promotion")
            val vipFirstPromotion: String,
            @SerializedName("vip_promotion")
            val vipPromotion: String
        ) : Parcelable {
            @Parcelize
            data class PayType(
                @SerializedName("allow_discount")
                val allowDiscount: Int,
                @SerializedName("allow_pack")
                val allowPack: Int,
                @SerializedName("allow_ticket")
                val allowTicket: Int,
                @SerializedName("allow_time_limit")
                val allowTimeLimit: Int,
                @SerializedName("allow_vip_discount")
                val allowVipDiscount: Int,
                @SerializedName("forbid_bb")
                val forbidBb: Int
            ) : Parcelable
        }

        @Parcelize
        data class Positive(
            @SerializedName("id")
            val id: Int,
            @SerializedName("title")
            val title: String
        ) : Parcelable

        @Parcelize
        data class Publish(
            @SerializedName("is_finish")
            val isFinish: Int,
            @SerializedName("is_started")
            val isStarted: Int,
            @SerializedName("pub_time")
            val pubTime: String,
            @SerializedName("pub_time_show")
            val pubTimeShow: String,
            @SerializedName("unknow_pub_date")
            val unknowPubDate: Int,
            @SerializedName("weekday")
            val weekday: Int
        ) : Parcelable

        @Parcelize
        data class Rating(
            @SerializedName("count")
            val count: Int,
            @SerializedName("score")
            val score: Double
        ) : Parcelable

        @Parcelize
        data class Rights(
            @SerializedName("allow_bp")
            val allowBp: Int,
            @SerializedName("allow_bp_rank")
            val allowBpRank: Int,
            @SerializedName("allow_download")
            val allowDownload: Int,
            @SerializedName("allow_review")
            val allowReview: Int,
            @SerializedName("area_limit")
            val areaLimit: Int,
            @SerializedName("ban_area_show")
            val banAreaShow: Int,
            @SerializedName("can_watch")
            val canWatch: Int,
            @SerializedName("copyright")
            val copyright: String,
            @SerializedName("forbid_pre")
            val forbidPre: Int,
            @SerializedName("freya_white")
            val freyaWhite: Int,
            @SerializedName("is_cover_show")
            val isCoverShow: Int,
            @SerializedName("is_preview")
            val isPreview: Int,
            @SerializedName("only_vip_download")
            val onlyVipDownload: Int,
            @SerializedName("resource")
            val resource: String,
            @SerializedName("watch_platform")
            val watchPlatform: Int
        ) : Parcelable

        @Parcelize
        data class Season(
            @SerializedName("badge")
            val badge: String,
            @SerializedName("badge_info")
            val badgeInfo: BadgeInfo,
            @SerializedName("badge_type")
            val badgeType: Int,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("horizontal_cover_1610")
            val horizontalCover1610: String,
            @SerializedName("horizontal_cover_169")
            val horizontalCover169: String,
            @SerializedName("media_id")
            val mediaId: Int,
            @SerializedName("new_ep")
            val newEp: NewEp,
            @SerializedName("season_id")
            val seasonId: String,
            @SerializedName("season_title")
            val seasonTitle: String,
            @SerializedName("season_type")
            val seasonType: Int,
            @SerializedName("stat")
            val stat: Stat,
            var itemState: ItemState
        ) : Parcelable {
            @Parcelize
            data class BadgeInfo(
                @SerializedName("bg_color")
                val bgColor: String,
                @SerializedName("bg_color_night")
                val bgColorNight: String,
                @SerializedName("text")
                val text: String
            ) : Parcelable

            @Parcelize
            data class NewEp(
                @SerializedName("cover")
                val cover: String,
                @SerializedName("id")
                val id: Int,
                @SerializedName("index_show")
                val indexShow: String
            ) : Parcelable

            @Parcelize
            data class Stat(
                @SerializedName("favorites")
                val favorites: Int,
                @SerializedName("series_follow")
                val seriesFollow: Int,
                @SerializedName("views")
                val views: Int
            ) : Parcelable

            @Parcelize
            data class ItemState(
                var titleColor: Int,
                var selected: Boolean
            ) : Parcelable
        }

        @Parcelize
        data class Section(
            @SerializedName("attr")
            val attr: Int,
            @SerializedName("episode_id")
            val episodeId: Int,
            @SerializedName("episode_ids")
            val episodeIds: List<Int>?,
            @SerializedName("episodes")
            val episodes: List<Episode>,
            @SerializedName("id")
            val id: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("type")
            val type: Int
        ) : Parcelable {
            @Parcelize
            data class Episode(
                @SerializedName("aid")
                val aid: Int,
                @SerializedName("badge")
                val badge: String,
                @SerializedName("badge_info")
                val badgeInfo: BadgeInfo,
                @SerializedName("badge_type")
                val badgeType: Int,
                @SerializedName("bvid")
                val bvid: String,
                @SerializedName("cid")
                val cid: Int,
                @SerializedName("cover")
                val cover: String,
                @SerializedName("dimension")
                val dimension: Dimension,
                @SerializedName("duration")
                val duration: Int,
                @SerializedName("from")
                val from: String,
                @SerializedName("id")
                val id: Int,
                @SerializedName("is_view_hide")
                val isViewHide: Boolean,
                @SerializedName("link")
                val link: String,
                @SerializedName("long_title")
                val longTitle: String,
                @SerializedName("pub_time")
                val pubTime: Int,
                @SerializedName("pv")
                val pv: Int,
                @SerializedName("release_date")
                val releaseDate: String,
                @SerializedName("rights")
                val rights: Rights,
                @SerializedName("share_copy")
                val shareCopy: String,
                @SerializedName("share_url")
                val shareUrl: String,
                @SerializedName("short_link")
                val shortLink: String,
                @SerializedName("stat")
                val stat: Stat,
                @SerializedName("status")
                val status: Int,
                @SerializedName("subtitle")
                val subtitle: String,
                @SerializedName("title")
                val title: String,
                @SerializedName("vid")
                val vid: String
            ) : Parcelable {
                @Parcelize
                data class BadgeInfo(
                    @SerializedName("bg_color")
                    val bgColor: String,
                    @SerializedName("bg_color_night")
                    val bgColorNight: String,
                    @SerializedName("text")
                    val text: String
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
                    @SerializedName("allow_demand")
                    val allowDemand: Int,
                    @SerializedName("allow_dm")
                    val allowDm: Int,
                    @SerializedName("allow_download")
                    val allowDownload: Int,
                    @SerializedName("area_limit")
                    val areaLimit: Int
                ) : Parcelable

                @Parcelize
                data class Stat(
                    @SerializedName("coin")
                    val coin: Int,
                    @SerializedName("danmakus")
                    val danmakus: Int,
                    @SerializedName("likes")
                    val likes: Int,
                    @SerializedName("play")
                    val play: Int,
                    @SerializedName("reply")
                    val reply: Int
                ) : Parcelable
            }
        }

        @Parcelize
        data class Series(
            @SerializedName("series_id")
            val seriesId: Int,
            @SerializedName("series_title")
            val seriesTitle: String
        ) : Parcelable

        @Parcelize
        data class Show(
            @SerializedName("wide_screen")
            val wideScreen: Int
        ) : Parcelable

        @Parcelize
        data class Stat(
            @SerializedName("coins")
            val coins: Int,
            @SerializedName("danmakus")
            val danmakus: Int,
            @SerializedName("favorite")
            val favorite: Int,
            @SerializedName("favorites")
            val favorites: Int,
            @SerializedName("likes")
            val likes: Int,
            @SerializedName("reply")
            val reply: Int,
            @SerializedName("share")
            val share: Int,
            @SerializedName("views")
            val views: Int
        ) : Parcelable

        @Parcelize
        data class UpInfo(
            @SerializedName("avatar")
            val avatar: String,
            @SerializedName("avatar_subscript_url")
            val avatarSubscriptUrl: String,
            @SerializedName("follower")
            val follower: Int,
            @SerializedName("is_follow")
            val isFollow: Int,
            @SerializedName("mid")
            val mid: Int,
            @SerializedName("nickname_color")
            val nicknameColor: String,
            @SerializedName("pendant")
            val pendant: Pendant,
            @SerializedName("theme_type")
            val themeType: Int,
            @SerializedName("uname")
            val uname: String,
            @SerializedName("verify_type")
            val verifyType: Int,
            @SerializedName("vip_label")
            val vipLabel: VipLabel,
            @SerializedName("vip_status")
            val vipStatus: Int,
            @SerializedName("vip_type")
            val vipType: Int
        ) : Parcelable {
            @Parcelize
            data class Pendant(
                @SerializedName("image")
                val image: String,
                @SerializedName("name")
                val name: String,
                @SerializedName("pid")
                val pid: Int
            ) : Parcelable

            @Parcelize
            data class VipLabel(
                @SerializedName("bg_color")
                val bgColor: String,
                @SerializedName("bg_style")
                val bgStyle: Int,
                @SerializedName("border_color")
                val borderColor: String,
                @SerializedName("text")
                val text: String,
                @SerializedName("text_color")
                val textColor: String
            ) : Parcelable
        }

        @Parcelize
        data class UserStatus(
            @SerializedName("area_limit")
            val areaLimit: Int,
            @SerializedName("ban_area_show")
            val banAreaShow: Int,
            @SerializedName("follow")
            val follow: Int,
            @SerializedName("follow_status")
            val followStatus: Int,
            @SerializedName("login")
            val login: Int,
            @SerializedName("pay")
            val pay: Int,
            @SerializedName("pay_pack_paid")
            val payPackPaid: Int,
            @SerializedName("sponsor")
            val sponsor: Int
        ) : Parcelable
    }
}