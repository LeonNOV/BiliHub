package com.leon.bilihub.beans.publicBeans.resources.video

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PgcRelation(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("episode_id")
        val episodeId: Int,
        @SerializedName("related_up")
        val relatedUp: List<RelatedUp>,
        @SerializedName("stat")
        val stat: Stat,
        @SerializedName("user_community")
        val userCommunity: UserCommunity
    ) : Parcelable {
        @Parcelize
        data class RelatedUp(
            @SerializedName("attribute")
            val attribute: Int,
            @SerializedName("avatar")
            val avatar: String,
            @SerializedName("is_follow")
            val isFollow: Int,
            @SerializedName("mid")
            val mid: String,
            @SerializedName("uname")
            val uname: String
        ) : Parcelable

        @Parcelize
        data class Stat(
            @SerializedName("coin")
            val coin: Int,
            @SerializedName("dm")
            val dm: Int,
            @SerializedName("like")
            val like: Int,
            @SerializedName("reply")
            val reply: Int,
            @SerializedName("view")
            val view: Int
        ) : Parcelable

        @Parcelize
        data class UserCommunity(
            @SerializedName("coin_number")
            val coinNumber: Int,
            @SerializedName("favorite")
            val favorite: Int,
            @SerializedName("is_original")
            val isOriginal: Int,
            @SerializedName("like")
            val like: Int
        ) : Parcelable
    }
}