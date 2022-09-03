package com.leon.bilihub.beans.home.drawerFunction


import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Series(
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
        val list: List<Item>
    ) : Parcelable {

        @Parcelize
        data class Item(
            @SerializedName("name")
            val name: String,
            @SerializedName("number")
            val number: Int,
            @SerializedName("status")
            val status: Int,
            @SerializedName("subject")
            val subject: String
        ) : Parcelable
    }
}