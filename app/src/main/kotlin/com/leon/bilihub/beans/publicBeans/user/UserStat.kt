package com.leon.bilihub.beans.publicBeans.user


import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserStat(
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
        @SerializedName("follower")
        val follower: Int,
        @SerializedName("following")
        val following: Int,
        @SerializedName("mid")
        val mid: String
    ) : Parcelable
}