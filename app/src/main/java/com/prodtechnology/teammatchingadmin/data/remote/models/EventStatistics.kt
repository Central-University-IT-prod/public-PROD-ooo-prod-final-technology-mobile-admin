package com.prodtechnology.teammatchingadmin.data.remote.models

import com.google.gson.annotations.SerializedName

data class EventStatistics(
    @SerializedName("users_qty") val usersQty: Int,
    @SerializedName("teams_qty") val teamsQty: Int,
    @SerializedName("backend_qty") val backendQty: Int,
    @SerializedName("frontend_qty") val frontedQty: Int,
    @SerializedName("mobile_qty") val mobileQty: Int
)