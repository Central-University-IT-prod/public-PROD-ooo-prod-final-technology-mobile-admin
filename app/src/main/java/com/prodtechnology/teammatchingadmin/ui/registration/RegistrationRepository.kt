package com.prodtechnology.teammatchingadmin.ui.registration

import com.prodtechnology.teammatchingadmin.data.remote.models.AuthResponse
import com.prodtechnology.teammatchingadmin.data.remote.RetrofitClient
import com.prodtechnology.teammatchingadmin.data.remote.service.AccountService
import com.prodtechnology.teammatchingadmin.data.remote.models.User
import retrofit2.Call

class RegistrationRepository {
    private val client = RetrofitClient.getAccountClient()
    private val service = client.create(AccountService::class.java)

    fun signUpUser(user: User) : Call<AuthResponse>{
        return service.signUpUser(user)
    }
}