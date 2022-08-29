package com.leon.bilihub.beans.publicBeans.resources.live

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LiveStream(
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
        @SerializedName("accept_quality")
        val acceptQuality: List<String>,
        @SerializedName("current_qn")
        val currentQn: Int,
        @SerializedName("current_quality")
        val currentQuality: Int,
        @SerializedName("durl")
        val durl: List<Durl>,
        @SerializedName("quality_description")
        val qualityDescription: List<QualityDescription>
    ) : Parcelable {
        @Parcelize
        data class Durl(
            @SerializedName("length")
            val length: Int,
            @SerializedName("order")
            val order: Int,
            @SerializedName("p2p_type")
            val p2pType: Int,
            @SerializedName("stream_type")
            val streamType: Int,
            @SerializedName("url")
            val url: String,
            var selected: Boolean
        ) : Parcelable

        @Parcelize
        data class QualityDescription(
            @SerializedName("desc")
            val desc: String,
            @SerializedName("qn")
            val qn: Int,
            var selected: Boolean
        ) : Parcelable
    }
}