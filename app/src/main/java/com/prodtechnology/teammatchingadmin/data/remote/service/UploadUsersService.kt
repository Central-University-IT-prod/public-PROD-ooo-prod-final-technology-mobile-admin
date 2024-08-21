package com.prodtechnology.teammatchingadmin.data.remote.service

import com.prodtechnology.teammatchingadmin.data.remote.models.UploadRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface UploadUsersService {
    @POST("api/upload-members")
    fun uploadUsers(
        @Header("Authorization") token: String,
        @Body request: UploadRequest
    ) : Call<String>
}