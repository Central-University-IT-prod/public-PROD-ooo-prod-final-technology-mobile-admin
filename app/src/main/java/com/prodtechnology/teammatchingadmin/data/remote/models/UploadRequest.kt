package com.prodtechnology.teammatchingadmin.data.remote.models

import com.google.gson.annotations.SerializedName

data class UploadRequest(
    @SerializedName("spreadsheet_url") val url: String,
    @SerializedName("event_id") val eventId: Int
)
