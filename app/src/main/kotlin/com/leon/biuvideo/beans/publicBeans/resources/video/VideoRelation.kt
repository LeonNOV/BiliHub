package com.leon.biuvideo.beans.publicBeans.resources.video

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoRelation(
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
        @SerializedName("attention")
        val attention: Boolean,
        @SerializedName("coin")
        val coin: Int,
        @SerializedName("dislike")
        val dislike: Boolean,
        @SerializedName("favorite")
        val favorite: Boolean,
        @SerializedName("like")
        val like: Boolean,
        @SerializedName("season_fav")
        val seasonFav: Boolean
    ) : Parcelable
}