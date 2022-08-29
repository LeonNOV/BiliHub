package com.leon.bilihub.beans.publicBeans.resources.picture

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PictureInfo(
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
        @SerializedName("item")
        val item: Item
    ) : Parcelable {
        @Parcelize
        data class Item(
            @SerializedName("basic")
            val basic: Basic,
            @SerializedName("id_str")
            val idStr: String,
            @SerializedName("modules")
            val modules: Modules,
            @SerializedName("type")
            val type: String,
            @SerializedName("visible")
            val visible: Boolean
        ) : Parcelable {
            @Parcelize
            data class Basic(
                @SerializedName("comment_id_str")
                val commentIdStr: String,
                @SerializedName("comment_type")
                val commentType: Int,
                @SerializedName("like_icon")
                val likeIcon: LikeIcon,
                @SerializedName("rid_str")
                val ridStr: String
            ) : Parcelable {
                @Parcelize
                data class LikeIcon(
                    @SerializedName("action_url")
                    val actionUrl: String,
                    @SerializedName("end_url")
                    val endUrl: String,
                    @SerializedName("id")
                    val id: Int,
                    @SerializedName("start_url")
                    val startUrl: String
                ) : Parcelable
            }

            @Parcelize
            data class Modules(
                @SerializedName("module_author")
                val moduleAuthor: ModuleAuthor,
                @SerializedName("module_dynamic")
                val moduleDynamic: ModuleDynamic,
                @SerializedName("module_stat")
                val moduleStat: ModuleStat
            ) : Parcelable {
                @Parcelize
                data class ModuleAuthor(
                    @SerializedName("decorate")
                    val decorate: Decorate,
                    @SerializedName("face")
                    val face: String,
                    @SerializedName("face_nft")
                    val faceNft: Boolean,
                    @SerializedName("jump_url")
                    val jumpUrl: String,
                    @SerializedName("label")
                    val label: String,
                    @SerializedName("mid")
                    val mid: Int,
                    @SerializedName("name")
                    val name: String,
                    @SerializedName("official_verify")
                    val officialVerify: OfficialVerify,
                    @SerializedName("pendant")
                    val pendant: Pendant,
                    @SerializedName("pub_action")
                    val pubAction: String,
                    @SerializedName("pub_location_text")
                    val pubLocationText: String,
                    @SerializedName("pub_time")
                    val pubTime: String,
                    @SerializedName("pub_ts")
                    val pubTs: Int,
                    @SerializedName("type")
                    val type: String,
                    @SerializedName("vip")
                    val vip: Vip
                ) : Parcelable {
                    @Parcelize
                    data class Decorate(
                        @SerializedName("card_url")
                        val cardUrl: String,
                        @SerializedName("fan")
                        val fan: Fan,
                        @SerializedName("id")
                        val id: Int,
                        @SerializedName("jump_url")
                        val jumpUrl: String,
                        @SerializedName("name")
                        val name: String,
                        @SerializedName("type")
                        val type: Int
                    ) : Parcelable {
                        @Parcelize
                        data class Fan(
                            @SerializedName("color")
                            val color: String,
                            @SerializedName("is_fan")
                            val isFan: Boolean,
                            @SerializedName("num_str")
                            val numStr: String,
                            @SerializedName("number")
                            val number: Int
                        ) : Parcelable
                    }

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
                        @SerializedName("status")
                        val status: Int,
                        @SerializedName("theme_type")
                        val themeType: Int,
                        @SerializedName("type")
                        val type: Int
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
                data class ModuleDynamic(
                    @SerializedName("desc")
                    val desc: Desc,
                    @SerializedName("major")
                    val major: Major,
                ) : Parcelable {
                    @Parcelize
                    data class Desc(
                        @SerializedName("rich_text_nodes")
                        val richTextNodes: List<RichTextNode>,
                        @SerializedName("text")
                        val text: String
                    ) : Parcelable {
                        @Parcelize
                        data class RichTextNode(
                            @SerializedName("orig_text")
                            val origText: String,
                            @SerializedName("text")
                            val text: String,
                            @SerializedName("type")
                            val type: String
                        ) : Parcelable
                    }

                    @Parcelize
                    data class Major(
                        @SerializedName("draw")
                        val draw: Draw,
                        @SerializedName("type")
                        val type: String
                    ) : Parcelable {
                        @Parcelize
                        data class Draw(
                            @SerializedName("id")
                            val id: Int,
                            @SerializedName("items")
                            val items: ArrayList<Item>
                        ) : Parcelable {
                            @Parcelize
                            data class Item(
                                @SerializedName("height")
                                val height: Int,
                                @SerializedName("size")
                                val size: Double,
                                @SerializedName("src")
                                val src: String,
                                @SerializedName("width")
                                val width: Int
                            ) : Parcelable
                        }
                    }
                }

                @Parcelize
                data class ModuleStat(
                    @SerializedName("comment")
                    val comment: Comment,
                    @SerializedName("forward")
                    val forward: Forward,
                    @SerializedName("like")
                    val like: Like
                ) : Parcelable {
                    @Parcelize
                    data class Comment(
                        @SerializedName("count")
                        val count: Int,
                        @SerializedName("forbidden")
                        val forbidden: Boolean
                    ) : Parcelable

                    @Parcelize
                    data class Forward(
                        @SerializedName("count")
                        val count: Int,
                        @SerializedName("forbidden")
                        val forbidden: Boolean
                    ) : Parcelable

                    @Parcelize
                    data class Like(
                        @SerializedName("count")
                        val count: Int,
                        @SerializedName("forbidden")
                        val forbidden: Boolean,
                        @SerializedName("status")
                        val status: Boolean
                    ) : Parcelable
                }
            }
        }
    }
}