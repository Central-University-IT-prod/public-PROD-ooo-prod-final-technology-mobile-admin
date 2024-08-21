package com.prodtechnology.teammatchingadmin.ui.login

import com.prodtechnology.teammatchingadmin.data.remote.models.AuthResponse
import com.prodtechnology.teammatchingadmin.data.remote.RetrofitClient
import com.prodtechnology.teammatchingadmin.data.remote.service.AccountService
import com.prodtechnology.teammatchingadmin.data.remote.models.User
import retrofit2.Call

class LoginRepository {
    private val client = RetrofitClient.getAccountClient()
    private val service = client.create(AccountService::class.java)

    fun authUser(user: User): Call<AuthResponse> {
        return service.authUser(user)
    }

    fun authUserToken(token: String): Call<User>{
        return service.getUserByToken("Bearer $token")
    }
}