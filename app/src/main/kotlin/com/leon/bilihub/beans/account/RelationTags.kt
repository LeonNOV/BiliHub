package com.leon.bilihub.beans.account

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RelationTags(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("ttl")
    val ttl: Int
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("count")
        val count: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("tagid")
        val tagid: String,
        @SerializedName("tip")
        val tip: String,
        var isSelected: Boolean
    ) : Parcelable
}