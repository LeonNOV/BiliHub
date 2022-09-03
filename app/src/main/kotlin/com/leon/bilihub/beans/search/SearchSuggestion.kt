package com.leon.bilihub.beans.search


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchSuggestion(
    @SerializedName("result")
    val result: Result
) : Parcelable {
    @Parcelize
    data class Result(
        @SerializedName("tag")
        val tag: List<Tag>
    ) : Parcelable {
        @Parcelize
        data class Tag(
            @SerializedName("name")
            val name: String,
            @SerializedName("ref")
            val ref: Int,
            @SerializedName("spid")
            val spid: Int,
            @SerializedName("term")
            val term: String,
            @SerializedName("value")
            val value: String
        ) : Parcelable
    }
}