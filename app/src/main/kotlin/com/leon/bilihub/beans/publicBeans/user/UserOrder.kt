package com.leon.bilihub.beans.publicBeans.user

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserOrder(
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
        val list: List<Order>,
        @SerializedName("pn")
        val pn: Int,
        @SerializedName("ps")
        val ps: Int,
        @SerializedName("total")
        val total: Int
    ) : Parcelable {
        @Parcelize
        data class Order(
            @SerializedName("areas")
            val areas: List<Area>,
            @SerializedName("badge")
            val badge: String,
            @SerializedName("badge_ep")
            val badgeEp: String,
            @SerializedName("badge_info")
            val badgeInfo: BadgeInfo,
            @SerializedName("badge_infos")
            val badgeInfos: BadgeInfos?,
            @SerializedName("badge_type")
            val badgeType: Int,
            @SerializedName("both_follow")
            val bothFollow: Boolean,
            @SerializedName("can_watch")
            val canWatch: Int,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("evaluate")
            val evaluate: String,
            @SerializedName("first_ep")
            val firstEp: Int,
            @SerializedName("first_ep_info")
            val firstEpInfo: FirstEpInfo,
            @SerializedName("follow_status")
            val followStatus: Int,
            @SerializedName("formal_ep_count")
            val formalEpCount: Int,
            @SerializedName("is_finish")
            val isFinish: Int,
            @SerializedName("is_new")
            val isNew: Int,
            @SerializedName("is_play")
            val isPlay: Int,
            @SerializedName("is_started")
            val isStarted: Int,
            @SerializedName("media_attr")
            val mediaAttr: Int,
            @SerializedName("media_id")
            val mediaId: Int,
            @SerializedName("mode")
            val mode: Int,
            @SerializedName("new_ep")
            val newEp: NewEp,
            @SerializedName("producers")
            val producers: List<Producer>?,
            @SerializedName("progress")
            val progress: String,
            @SerializedName("publish")
            val publish: Publish,
            @SerializedName("rating")
            val rating: Rating,
            @SerializedName("renewal_time")
            val renewalTime: String?,
            @SerializedName("rights")
            val rights: Rights,
            @SerializedName("season_attr")
            val seasonAttr: Int,
            @SerializedName("season_id")
            val seasonId: String,
            @SerializedName("season_status")
            val seasonStatus: Int,
            @SerializedName("season_title")
            val seasonTitle: String,
            @SerializedName("season_type")
            val seasonType: Int,
            @SerializedName("season_type_name")
            val seasonTypeName: String,
            @SerializedName("season_version")
            val seasonVersion: String,
            @SerializedName("section")
            val section: List<Section>,
            @SerializedName("series")
            val series: Series,
            @SerializedName("short_url")
            val shortUrl: String,
            @SerializedName("square_cover")
            val squareCover: String,
            @SerializedName("stat")
            val stat: Stat,
            @SerializedName("styles")
            val styles: List<String>,
            @SerializedName("subtitle")
            val subtitle: String,
            @SerializedName("subtitle_14")
            val subtitle14: String?,
            @SerializedName("subtitle_25")
            val subtitle25: String?,
            @SerializedName("summary")
            val summary: String,
            @SerializedName("title")
            val title: String,
            @SerializedName("total_count")
            val totalCount: Int,
            @SerializedName("url")
            val url: String,
            @SerializedName("viewable_crowd_type")
            val viewableCrowdType: Int?
        ) : Parcelable {
            @Parcelize
            data class Area(
                @SerializedName("id")
                val id: Int,
                @SerializedName("name")
                val name: String
            ) : Parcelable

            @Parcelize
            data class BadgeInfo(
                @SerializedName("bg_color")
                val bgColor: String,
                @SerializedName("bg_color_night")
                val bgColorNight: String,
                @SerializedName("img")
                val img: String?,
                @SerializedName("text")
                val text: String?
            ) : Parcelable

            @Parcelize
            data class BadgeInfos(
                @SerializedName("content_attr")
                val contentAttr: ContentAttr?,
                @SerializedName("vip_or_pay")
                val vipOrPay: VipOrPay
            ) : Parcelable {
                @Parcelize
                data class ContentAttr(
                    @SerializedName("bg_color")
                    val bgColor: String,
                    @SerializedName("bg_color_night")
                    val bgColorNight: String,
                    @SerializedName("img")
                    val img: String,
                    @SerializedName("text")
                    val text: String
                ) : Parcelable

                @Parcelize
                data class VipOrPay(
                    @SerializedName("bg_color")
                    val bgColor: String,
                    @SerializedName("bg_color_night")
                    val bgColorNight: String,
                    @SerializedName("img")
                    val img: String,
                    @SerializedName("text")
                    val text: String
                ) : Parcelable
            }

            @Parcelize
            data class FirstEpInfo(
                @SerializedName("cover")
                val cover: String,
                @SerializedName("duration")
                val duration: Int,
                @SerializedName("id")
                val id: Int,
                @SerializedName("long_title")
                val longTitle: String?,
                @SerializedName("pub_time")
                val pubTime: String,
                @SerializedName("title")
                val title: String
            ) : Parcelable

            @Parcelize
            data class NewEp(
                @SerializedName("cover")
                val cover: String,
                @SerializedName("duration")
                val duration: Int,
                @SerializedName("id")
                val id: Int,
                @SerializedName("index_show")
                val indexShow: String,
                @SerializedName("long_title")
                val longTitle: String?,
                @SerializedName("pub_time")
                val pubTime: String,
                @SerializedName("title")
                val title: String
            ) : Parcelable

            @Parcelize
            data class Producer(
                @SerializedName("is_contribute")
                val isContribute: Int?,
                @SerializedName("mid")
                val mid: String,
                @SerializedName("type")
                val type: Int
            ) : Parcelable

            @Parcelize
            data class Publish(
                @SerializedName("pub_time")
                val pubTime: String,
                @SerializedName("pub_time_show")
                val pubTimeShow: String,
                @SerializedName("release_date")
                val releaseDate: String,
                @SerializedName("release_date_show")
                val releaseDateShow: String
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
                val allowBp: Int?,
                @SerializedName("allow_bp_rank")
                val allowBpRank: Int?,
                @SerializedName("allow_preview")
                val allowPreview: Int?,
                @SerializedName("allow_review")
                val allowReview: Int,
                @SerializedName("demand_end_time")
                val demandEndTime: DemandEndTime?,
                @SerializedName("is_rcmd")
                val isRcmd: Int?,
                @SerializedName("is_selection")
                val isSelection: Int,
                @SerializedName("selection_style")
                val selectionStyle: Int
            ) : Parcelable {
                @Parcelize
                class DemandEndTime : Parcelable
            }

            @Parcelize
            data class Section(
                @SerializedName("attr")
                val attr: Int?,
                @SerializedName("ban_area_show")
                val banAreaShow: Int,
                @SerializedName("copyright")
                val copyright: String,
                @SerializedName("episode_ids")
                val episodeIds: List<Int>,
                @SerializedName("limit_group")
                val limitGroup: Int,
                @SerializedName("season_id")
                val seasonId: String,
                @SerializedName("section_id")
                val sectionId: Int,
                @SerializedName("title")
                val title: String?,
                @SerializedName("type")
                val type: Int?,
                @SerializedName("watch_platform")
                val watchPlatform: Int
            ) : Parcelable

            @Parcelize
            data class Series(
                @SerializedName("new_season_id")
                val newSeasonId: Int,
                @SerializedName("season_count")
                val seasonCount: Int,
                @SerializedName("series_id")
                val seriesId: Int,
                @SerializedName("series_ord")
                val seriesOrd: Int,
                @SerializedName("title")
                val title: String
            ) : Parcelable

            @Parcelize
            data class Stat(
                @SerializedName("coin")
                val coin: Int,
                @SerializedName("danmaku")
                val danmaku: Int,
                @SerializedName("favorite")
                val favorite: Int,
                @SerializedName("follow")
                val follow: Int,
                @SerializedName("likes")
                val likes: Int,
                @SerializedName("reply")
                val reply: Int,
                @SerializedName("series_follow")
                val seriesFollow: Int,
                @SerializedName("series_view")
                val seriesView: Int,
                @SerializedName("view")
                val view: Int
            ) : Parcelable
        }
    }
}