package com.leon.biuvideo.beans.home.channel

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChannelCategory(
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
        @SerializedName("categories")
        val categories: List<Category>
    ) : Parcelable {
        @Parcelize
        data class Category(
            @SerializedName("channel_count")
            val channelCount: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String
        ) : Parcelable
    }
}