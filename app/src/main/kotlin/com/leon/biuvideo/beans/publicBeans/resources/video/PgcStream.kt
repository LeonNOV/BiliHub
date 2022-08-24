package com.leon.biuvideo.beans.publicBeans.resources.video

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PgcStream(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: Result
) : Parcelable {
    @Parcelize
    data class Result(
        @SerializedName("accept_description")
        val acceptDescription: List<String>,
        @SerializedName("accept_format")
        val acceptFormat: String,
        @SerializedName("accept_quality")
        val acceptQuality: List<Int>,
        @SerializedName("bp")
        val bp: Int,
        @SerializedName("code")
        val code: Int,
        @SerializedName("durl")
        val durl: List<Durl>,
        @SerializedName("fnval")
        val fnval: Int,
        @SerializedName("fnver")
        val fnver: Int,
        @SerializedName("format")
        val format: String,
        @SerializedName("from")
        val from: String,
        @SerializedName("has_paid")
        val hasPaid: Boolean,
        @SerializedName("is_drm")
        val isDrm: Boolean,
        @SerializedName("is_preview")
        val isPreview: Int,
        @SerializedName("message")
        val message: String,
        @SerializedName("no_rexcode")
        val noRexcode: Int,
        @SerializedName("quality")
        val quality: Int,
        @SerializedName("record_info")
        val recordInfo: RecordInfo,
        @SerializedName("result")
        val result: String,
        @SerializedName("seek_param")
        val seekParam: String,
        @SerializedName("seek_type")
        val seekType: String,
        @SerializedName("status")
        val status: Int,
        @SerializedName("support_formats")
        val supportFormats: List<VideoStream.Data.SupportFormat>,
        @SerializedName("timelength")
        val timelength: Int,
        @SerializedName("type")
        val type: String,
        @SerializedName("video_codecid")
        val videoCodecid: Int,
        @SerializedName("video_project")
        val videoProject: Boolean
    ) : Parcelable {
        @Parcelize
        data class Durl(
            @SerializedName("ahead")
            val ahead: String,
            @SerializedName("backup_url")
            val backupUrl: List<String>,
            @SerializedName("length")
            val length: Int,
            @SerializedName("md5")
            val md5: String,
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
        data class RecordInfo(
            @SerializedName("record")
            val record: String,
            @SerializedName("record_icon")
            val recordIcon: String
        ) : Parcelable
    }
}