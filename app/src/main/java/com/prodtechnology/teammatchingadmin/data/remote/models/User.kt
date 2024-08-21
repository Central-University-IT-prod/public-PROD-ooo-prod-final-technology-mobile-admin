package com.prodtechnology.teammatchingadmin.data.remote.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String?
)