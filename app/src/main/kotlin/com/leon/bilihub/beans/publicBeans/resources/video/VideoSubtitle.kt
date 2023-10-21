package com.leon.bilihub.beans.publicBeans.resources.video


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class VideoSubtitle(
    @SerializedName("background_alpha")
    val backgroundAlpha: Double,
    @SerializedName("background_color")
    val backgroundColor: String,
    @SerializedName("body")
    val body: List<Body>,
    @SerializedName("font_color")
    val fontColor: String,
    @SerializedName("font_size")
    val fontSize: Double,
    @SerializedName("Stroke")
    val stroke: String
) : Parcelable {
    @Parcelize
    data class Body(
        @SerializedName("content")
        val content: String,
        @SerializedName("from")
        val from: Double,
        @SerializedName("location")
        val location: Int,
        @SerializedName("to")
        val to: Double
    ) : Parcelable
}