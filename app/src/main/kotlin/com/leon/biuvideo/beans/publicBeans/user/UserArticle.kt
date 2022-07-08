package com.leon.biuvideo.beans.publicBeans.user

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserArticle(
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
        @SerializedName("articles")
        val articles: List<Article>,
        @SerializedName("count")
        val count: Int,
        @SerializedName("pn")
        val pn: Int,
        @SerializedName("ps")
        val ps: Int
    ) : Parcelable {
        @Parcelize
        data class Article(
            @SerializedName("act_id")
            val actId: Int,
            @SerializedName("apply_time")
            val applyTime: String,
            @SerializedName("author")
            val author: Author,
            @SerializedName("banner_url")
            val bannerUrl: String,
            @SerializedName("categories")
            val categories: List<Category>,
            @SerializedName("category")
            val category: Category,
            @SerializedName("check_time")
            val checkTime: String,
            @SerializedName("cover_avid")
            val coverAvid: Int,
            @SerializedName("ctime")
            val ctime: Int,
            @SerializedName("id")
            val id: Int,
            @SerializedName("image_urls")
            val imageUrls: List<String>,
            @SerializedName("is_like")
            val isLike: Boolean,
            @SerializedName("media")
            val media: Media,
            @SerializedName("origin_image_urls")
            val originImageUrls: List<String>,
            @SerializedName("original")
            val original: Int,
            @SerializedName("publish_time")
            val publishTime: Int,
            @SerializedName("reprint")
            val reprint: Int,
            @SerializedName("state")
            val state: Int,
            @SerializedName("stats")
            val stats: Stats,
            @SerializedName("summary")
            val summary: String,
            @SerializedName("template_id")
            val templateId: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("type")
            val type: Int,
            @SerializedName("words")
            val words: Int
        ) : Parcelable {
            @Parcelize
            data class Author(
                @SerializedName("face")
                val face: String,
                @SerializedName("mid")
                val mid: Int,
                @SerializedName("name")
                val name: String,
                @SerializedName("nameplate")
                val nameplate: Nameplate,
                @SerializedName("official_verify")
                val officialVerify: OfficialVerify,
                @SerializedName("pendant")
                val pendant: Pendant,
                @SerializedName("vip")
                val vip: Vip
            ) : Parcelable {
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
                    @SerializedName("name")
                    val name: String,
                    @SerializedName("pid")
                    val pid: Int
                ) : Parcelable

                @Parcelize
                data class Vip(
                    @SerializedName("avatar_subscript")
                    val avatarSubscript: Int,
                    @SerializedName("due_date")
                    val dueDate: Int,
                    @SerializedName("label")
                    val label: Label,
                    @SerializedName("nickname_color")
                    val nicknameColor: String,
                    @SerializedName("status")
                    val status: Int,
                    @SerializedName("theme_type")
                    val themeType: Int,
                    @SerializedName("type")
                    val type: Int,
                    @SerializedName("vip_pay_type")
                    val vipPayType: Int
                ) : Parcelable {
                    @Parcelize
                    data class Label(
                        @SerializedName("label_theme")
                        val labelTheme: String,
                        @SerializedName("path")
                        val path: String,
                        @SerializedName("text")
                        val text: String
                    ) : Parcelable
                }
            }

            @Parcelize
            data class Category(
                @SerializedName("id")
                val id: Int,
                @SerializedName("name")
                val name: String,
                @SerializedName("parent_id")
                val parentId: Int
            ) : Parcelable

            @Parcelize
            data class Media(
                @SerializedName("area")
                val area: String,
                @SerializedName("cover")
                val cover: String,
                @SerializedName("media_id")
                val mediaId: Int,
                @SerializedName("score")
                val score: Int,
                @SerializedName("spoiler")
                val spoiler: Int,
                @SerializedName("title")
                val title: String,
                @SerializedName("type_id")
                val typeId: Int,
                @SerializedName("type_name")
                val typeName: String
            ) : Parcelable

            @Parcelize
            data class Stats(
                @SerializedName("coin")
                val coin: Int,
                @SerializedName("dislike")
                val dislike: Int,
                @SerializedName("dynamic")
                val `dynamic`: Int,
                @SerializedName("favorite")
                val favorite: Int,
                @SerializedName("like")
                val like: Int,
                @SerializedName("reply")
                val reply: Int,
                @SerializedName("share")
                val share: Int,
                @SerializedName("view")
                val view: Int
            ) : Parcelable
        }
    }
}