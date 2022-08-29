package com.leon.bilihub.beans.publicBeans.user

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserPicture(
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
        @SerializedName("items")
        val items: List<Item>
    ) : Parcelable {
        @Parcelize
        data class Item(
            @SerializedName("count")
            val count: Int,
            @SerializedName("ctime")
            val ctime: Int,
            @SerializedName("description")
            val description: String,
            @SerializedName("doc_id")
            val docId: Int,
            @SerializedName("dyn_id")
            val dynId: String,
            @SerializedName("like")
            val like: Int,
            @SerializedName("pictures")
            val pictures: List<Picture>,
            @SerializedName("poster_uid")
            val posterUid: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("view")
            val view: Int
        ) : Parcelable {
            @Parcelize
            data class Picture(
                @SerializedName("img_height")
                val imgHeight: Int,
                @SerializedName("img_size")
                val imgSize: Double,
                @SerializedName("img_src")
                val imgSrc: String,
                @SerializedName("img_width")
                val imgWidth: Int
            ) : Parcelable
        }
    }
}