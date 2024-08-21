package com.prodtechnology.teammatchingadmin.data.remote.models

import com.google.gson.annotations.SerializedName

data class ErrorDetail (
    @SerializedName("msg") val message: String
)