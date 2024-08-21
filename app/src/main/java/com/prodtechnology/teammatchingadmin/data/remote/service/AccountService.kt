package com.prodtechnology.teammatchingadmin.data.remote.service

import com.prodtechnology.teammatchingadmin.data.remote.models.AuthResponse
import com.prodtechnology.teammatchingadmin.data.remote.models.ChangePasswordRequest
import com.prodtechnology.teammatchingadmin.data.remote.models.UploadRequest
import com.prodtechnology.teammatchingadmin.data.remote.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AccountService {
    @POST("api/admin/login")
    fun authUser(
        @Body user: User
    ) : Call<AuthResponse>

    @GET("api/admin/me")
    fun getUserByToken(
        @Header("Authorization") token: String
    ) : Call<User>

    @POST("api/admin")
    fun signUpUser(
        @Body user: User
    ) : Call<AuthResponse>

    @POST("api/admin/update_password")
    fun updatePassword(
        @Header("Authorization") token: String,
        @Body request: ChangePasswordRequest
    ) : Call<AuthResponse>
}