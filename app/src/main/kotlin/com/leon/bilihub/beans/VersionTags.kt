package com.leon.bilihub.beans


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import java.util.LinkedList

@Parcelize
data class VersionTags(
    @SerializedName("Branches")
    val branches: List<String>,
    @SerializedName("Tags")
    val tags: LinkedList<String>
) : Parcelable