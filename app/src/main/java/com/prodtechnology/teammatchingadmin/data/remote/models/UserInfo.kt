package com.prodtechnology.teammatchingadmin.data.remote.models

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("email") var email: String,
    @SerializedName("fullname") var fullname: String,
    @SerializedName("password") var password: String,
    @SerializedName("telegram_username") var telegram: String,
    @SerializedName("skills") var skills: List<Skill>,
    @SerializedName("age") var age: Int,
    @SerializedName("profession") var profession: String,
    @SerializedName("bio") var bio: String
)
