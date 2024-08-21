package com.prodtechnology.teammatchingadmin.data.remote.models

import com.google.gson.annotations.SerializedName

data class Skill(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String
)