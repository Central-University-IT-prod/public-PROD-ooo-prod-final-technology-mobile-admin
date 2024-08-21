package com.prodtechnology.teammatchingadmin.data.remote.models

import com.google.gson.annotations.SerializedName

data class Event (
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("max_member_qty") val maxMembers: Int,
    @SerializedName("team_creation_deadline") val endDate: String
)