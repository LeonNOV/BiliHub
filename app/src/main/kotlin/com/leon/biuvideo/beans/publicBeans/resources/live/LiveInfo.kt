package com.leon.biuvideo.beans.publicBeans.resources.live

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LiveInfo(
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
        @SerializedName("anchor_info")
        val anchorInfo: AnchorInfo,
        @SerializedName("area_rank_info")
        val areaRankInfo: AreaRankInfo,
        @SerializedName("dm_brush_info")
        val dmBrushInfo: DmBrushInfo,
        @SerializedName("dm_tag_info")
        val dmTagInfo: DmTagInfo,
        @SerializedName("news_info")
        val newsInfo: NewsInfo,
        @SerializedName("player_throttle_info")
        val playerThrottleInfo: PlayerThrottleInfo,
        @SerializedName("rankdb_info")
        val rankdbInfo: RankdbInfo,
        @SerializedName("room_info")
        val roomInfo: RoomInfo,
        @SerializedName("show_reserve_status")
        val showReserveStatus: Boolean,
        @SerializedName("switch_info")
        val switchInfo: SwitchInfo,
        @SerializedName("topic_info")
        val topicInfo: TopicInfo,
        @SerializedName("watched_show")
        val watchedShow: WatchedShow
    ) : Parcelable {

        @Parcelize
        data class AnchorInfo(
            @SerializedName("base_info")
            val baseInfo: BaseInfo,
            @SerializedName("live_info")
            val liveInfo: LiveInfo,
            @SerializedName("medal_info")
            val medalInfo: MedalInfo,
            @SerializedName("relation_info")
            val relationInfo: RelationInfo
        ) : Parcelable {
            @Parcelize
            data class BaseInfo(
                @SerializedName("face")
                val face: String,
                @SerializedName("gender")
                val gender: String,
                @SerializedName("official_info")
                val officialInfo: OfficialInfo,
                @SerializedName("uname")
                val uname: String
            ) : Parcelable {
                @Parcelize
                data class OfficialInfo(
                    @SerializedName("desc")
                    val desc: String,
                    @SerializedName("is_nft")
                    val isNft: Int,
                    @SerializedName("nft_dmark")
                    val nftDmark: String,
                    @SerializedName("role")
                    val role: Int,
                    @SerializedName("title")
                    val title: String
                ) : Parcelable
            }

            @Parcelize
            data class LiveInfo(
                @SerializedName("current")
                val current: List<Int>,
                @SerializedName("level")
                val level: Int,
                @SerializedName("level_color")
                val levelColor: Int,
                @SerializedName("next")
                val next: List<Int>,
                @SerializedName("rank")
                val rank: String,
                @SerializedName("score")
                val score: Int,
                @SerializedName("upgrade_score")
                val upgradeScore: Int
            ) : Parcelable

            @Parcelize
            data class MedalInfo(
                @SerializedName("fansclub")
                val fansclub: Int,
                @SerializedName("medal_id")
                val medalId: Int,
                @SerializedName("medal_name")
                val medalName: String
            ) : Parcelable

            @Parcelize
            data class RelationInfo(
                @SerializedName("attention")
                val attention: Int
            ) : Parcelable
        }

        @Parcelize
        data class AreaRankInfo(
            @SerializedName("areaRank")
            val areaRank: AreaRank,
            @SerializedName("liveRank")
            val liveRank: LiveRank
        ) : Parcelable {
            @Parcelize
            data class AreaRank(
                @SerializedName("index")
                val index: Int,
                @SerializedName("rank")
                val rank: String
            ) : Parcelable

            @Parcelize
            data class LiveRank(
                @SerializedName("rank")
                val rank: String
            ) : Parcelable
        }

        @Parcelize
        data class BattleRankEntryInfo(
            @SerializedName("first_rank_img_url")
            val firstRankImgUrl: String,
            @SerializedName("rank_name")
            val rankName: String,
            @SerializedName("show_status")
            val showStatus: Int
        ) : Parcelable

        @Parcelize
        data class DmBrushInfo(
            @SerializedName("brush_count")
            val brushCount: Int,
            @SerializedName("min_time")
            val minTime: Int,
            @SerializedName("slice_count")
            val sliceCount: Int,
            @SerializedName("storage_time")
            val storageTime: Int
        ) : Parcelable

        @Parcelize
        data class DmTagInfo(
            @SerializedName("dm_chronos_extra")
            val dmChronosExtra: String,
            @SerializedName("dm_setting_switch")
            val dmSettingSwitch: Int,
            @SerializedName("dm_tag")
            val dmTag: Int,
            @SerializedName("extra")
            val extra: String
        ) : Parcelable

        @Parcelize
        data class NewsInfo(
            @SerializedName("content")
            val content: String,
            @SerializedName("ctime")
            val ctime: String,
            @SerializedName("uid")
            val uid: String
        ) : Parcelable

        @Parcelize
        data class PlayerThrottleInfo(
            @SerializedName("fullscreen_sleep_time")
            val fullscreenSleepTime: Int,
            @SerializedName("normal_sleep_time")
            val normalSleepTime: Int,
            @SerializedName("prompt_time")
            val promptTime: Int,
            @SerializedName("status")
            val status: Int,
            @SerializedName("tab_sleep_time")
            val tabSleepTime: Int
        ) : Parcelable

        @Parcelize
        data class RankdbInfo(
            @SerializedName("color")
            val color: String,
            @SerializedName("h5_url")
            val h5Url: String,
            @SerializedName("rank_desc")
            val rankDesc: String,
            @SerializedName("roomid")
            val roomid: Int,
            @SerializedName("timestamp")
            val timestamp: Int,
            @SerializedName("web_url")
            val webUrl: String
        ) : Parcelable

        @Parcelize
        data class RoomInfo(
            @SerializedName("area_id")
            val areaId: Int,
            @SerializedName("area_name")
            val areaName: String,
            @SerializedName("background")
            val background: String,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("description")
            val description: String,
            @SerializedName("hidden_status")
            val hiddenStatus: Int,
            @SerializedName("hidden_time")
            val hiddenTime: Int,
            @SerializedName("is_studio")
            val isStudio: Boolean,
            @SerializedName("keyframe")
            val keyframe: String,
            @SerializedName("live_screen_type")
            val liveScreenType: Int,
            @SerializedName("live_start_time")
            val liveStartTime: Int,
            @SerializedName("live_status")
            val liveStatus: Int,
            @SerializedName("lock_status")
            val lockStatus: Int,
            @SerializedName("lock_time")
            val lockTime: Int,
            @SerializedName("on_voice_join")
            val onVoiceJoin: Int,
            @SerializedName("online")
            val online: Int,
            @SerializedName("parent_area_id")
            val parentAreaId: Int,
            @SerializedName("parent_area_name")
            val parentAreaName: String,
            @SerializedName("pendants")
            val pendants: Pendants,
            @SerializedName("pk_status")
            val pkStatus: Int,
            @SerializedName("room_id")
            val roomId: Int,
            @SerializedName("room_type")
            val roomType: RoomType,
            @SerializedName("short_id")
            val shortId: Int,
            @SerializedName("special_type")
            val specialType: Int,
            @SerializedName("tags")
            val tags: String,
            @SerializedName("title")
            val title: String,
            @SerializedName("uid")
            val uid: String,
            @SerializedName("up_session")
            val upSession: String
        ) : Parcelable {
            @Parcelize
            data class Pendants(
                @SerializedName("frame")
                val frame: Frame
            ) : Parcelable {
                @Parcelize
                data class Frame(
                    @SerializedName("desc")
                    val desc: String,
                    @SerializedName("name")
                    val name: String,
                    @SerializedName("value")
                    val value: String
                ) : Parcelable
            }

            @Parcelize
            data class RoomType(
                @SerializedName("2-3")
                val x23: Int,
                @SerializedName("3-21")
                val x321: Int,
                @SerializedName("3-29")
                val x329: Int,
                @SerializedName("3-50")
                val x350: Int
            ) : Parcelable
        }

        @Parcelize
        data class SwitchInfo(
            @SerializedName("close_danmaku")
            val closeDanmaku: Boolean,
            @SerializedName("close_gift")
            val closeGift: Boolean,
            @SerializedName("close_guard")
            val closeGuard: Boolean,
            @SerializedName("close_online")
            val closeOnline: Boolean
        ) : Parcelable

        @Parcelize
        data class TopicInfo(
            @SerializedName("topic_id")
            val topicId: Int,
            @SerializedName("topic_name")
            val topicName: String
        ) : Parcelable

        @Parcelize
        data class WatchedShow(
            @SerializedName("icon")
            val icon: String,
            @SerializedName("icon_location")
            val iconLocation: Int,
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
}