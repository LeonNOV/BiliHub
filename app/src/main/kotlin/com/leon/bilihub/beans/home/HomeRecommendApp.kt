package com.leon.bilihub.beans.home

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeRecommendApp(
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
        @SerializedName("config")
        val config: Config,
        @SerializedName("items")
        val items: List<Item>
    ) : Parcelable {
        @Parcelize
        data class Config(
            @SerializedName("auto_refresh_time")
            val autoRefreshTime: Int,
            @SerializedName("auto_refresh_time_by_active")
            val autoRefreshTimeByActive: Int,
            @SerializedName("auto_refresh_time_by_appear")
            val autoRefreshTimeByAppear: Int,
            @SerializedName("autoplay_card")
            val autoplayCard: Int,
            @SerializedName("card_density_exp")
            val cardDensityExp: Int,
            @SerializedName("column")
            val column: Int,
            @SerializedName("enable_rcmd_guide")
            val enableRcmdGuide: Boolean,
            @SerializedName("feed_clean_abtest")
            val feedCleanAbtest: Int,
            @SerializedName("home_transfer_test")
            val homeTransferTest: Int,
            @SerializedName("inline_sound")
            val inlineSound: Int,
            @SerializedName("is_back_to_homepage")
            val isBackToHomepage: Boolean,
            @SerializedName("show_inline_danmaku")
            val showInlineDanmaku: Int,
            @SerializedName("toast")
            val toast: Toast,
            @SerializedName("visible_area")
            val visibleArea: Int
        ) : Parcelable {
            @Parcelize
            class Toast : Parcelable
        }

        @Parcelize
        data class Item(
            @SerializedName("args")
            val args: Args,
            @SerializedName("can_play")
            val canPlay: Int,
            @SerializedName("card_goto")
            val cardGoto: String,
            @SerializedName("card_type")
            val cardType: String,
            @SerializedName("cover")
            val cover: String,
            @SerializedName("cover_left_1_content_description")
            val coverLeft1ContentDescription: String,
            @SerializedName("cover_left_2_content_description")
            val coverLeft2ContentDescription: String,
            @SerializedName("cover_left_icon_1")
            val coverLeftIcon1: Int,
            @SerializedName("cover_left_icon_2")
            val coverLeftIcon2: Int,
            @SerializedName("cover_left_text_1")
            val coverLeftText1: String,
            @SerializedName("cover_left_text_2")
            val coverLeftText2: String,
            @SerializedName("cover_right_content_description")
            val coverRightContentDescription: String,
            @SerializedName("cover_right_text")
            val coverRightText: String,
            @SerializedName("desc_button")
            val descButton: DescButton,
            @SerializedName("goto")
            val goto: String,
            @SerializedName("idx")
            val idx: Int,
            @SerializedName("official_icon")
            val officialIcon: Int?,
            @SerializedName("param")
            val `param`: String,
            @SerializedName("player_args")
            val playerArgs: PlayerArgs,
            @SerializedName("talk_back")
            val talkBack: String,
            @SerializedName("title")
            val title: String,
            @SerializedName("uri")
            val uri: String
        ) : Parcelable {
            @Parcelize
            data class Args(
                @SerializedName("aid")
                val aid: Int,
                @SerializedName("rid")
                val rid: Int,
                @SerializedName("rname")
                val rname: String,
                @SerializedName("tid")
                val tid: Int,
                @SerializedName("tname")
                val tname: String,
                @SerializedName("up_id")
                val upId: String,
                @SerializedName("up_name")
                val upName: String
            ) : Parcelable

            @Parcelize
            data class DescButton(
                @SerializedName("event")
                val event: String,
                @SerializedName("event_v2")
                val eventV2: String,
                @SerializedName("text")
                val text: String,
                @SerializedName("type")
                val type: Int,
                @SerializedName("uri")
                val uri: String
            ) : Parcelable

            @Parcelize
            data class PlayerArgs(
                @SerializedName("aid")
                val aid: Int,
                @SerializedName("cid")
                val cid: Int,
                @SerializedName("duration")
                val duration: Int,
                @SerializedName("type")
                val type: String
            ) : Parcelable
        }
    }
}