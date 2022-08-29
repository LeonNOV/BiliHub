package com.leon.bilihub.beans.partition

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PartitionTag(
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
        @SerializedName("rid")
        val rid: Int,
        @SerializedName("tags")
        val tags: List<Tag>
    ) : Parcelable {
        @Parcelize
        data class Tag(
            @SerializedName("highlight")
            val highlight: Int,
            @SerializedName("is_atten")
            val isAtten: Int,
            @SerializedName("tag_id")
            val tagId: Int,
            @SerializedName("tag_name")
            val tagName: String
        ) : Parcelable
    }
}