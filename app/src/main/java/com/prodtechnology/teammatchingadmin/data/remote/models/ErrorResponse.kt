package com.prodtechnology.teammatchingadmin.data.remote.models

import com.google.gson.annotations.SerializedName
import com.prodtechnology.teammatchingadmin.data.remote.models.ErrorDetail

data class ErrorResponse(
    @SerializedName("detail") val detail: List<ErrorDetail>
)