package com.leon.biuvideo.beans.publicBeans.resources.video

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoStream(
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
        @SerializedName("accept_description")
        val acceptDescription: List<String>,
        @SerializedName("accept_format")
        val acceptFormat: String,
        @SerializedName("accept_quality")
        val acceptQuality: List<Int>,
        @SerializedName("durl")
        val durl: List<Durl>,
        @SerializedName("format")
        val format: String,
        @SerializedName("from")
        val from: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("quality")
        val quality: Int,
        @SerializedName("result")
        val result: String,
        @SerializedName("seek_param")
        val seekParam: String,
        @SerializedName("seek_type")
        val seekType: String,
        @SerializedName("support_formats")
        val supportFormats: List<SupportFormat>,
        @SerializedName("timelength")
        val timelength: Int,
        @SerializedName("video_codecid")
        val videoCodecid: Int
    ) : Parcelable {
        @Parcelize
        data class Durl(
            @SerializedName("ahead")
            val ahead: String,
            @SerializedName("backup_url")
            val backupUrl: List<String>,
            @SerializedName("length")
            val length: Int,
            @SerializedName("order")
            val order: Int,
            @SerializedName("size")
            val size: Int,
            @SerializedName("url")
            val url: String,
            @SerializedName("vhead")
            val vhead: String
        ) : Parcelable

        @Parcelize
        data class SupportFormat(
            @SerializedName("display_desc")
            val displayDesc: String,
            @SerializedName("format")
            val format: String,
            @SerializedName("new_description")
            val newDescription: String,
            @SerializedName("quality")
            val quality: Int,
            @SerializedName("superscript")
            val superscript: String
        ) : Parcelable
    }
}