package com.prodtechnology.teammatchingadmin.ui.account_info

import com.prodtechnology.teammatchingadmin.data.remote.RetrofitClient
import com.prodtechnology.teammatchingadmin.data.remote.service.AccountService
import com.prodtechnology.teammatchingadmin.data.remote.models.AuthResponse
import com.prodtechnology.teammatchingadmin.data.remote.models.ChangePasswordRequest
import retrofit2.Call

class AccountInfoRepository {
    private val client = RetrofitClient.getAccountInfoClient()
    private val service = client.create(AccountService::class.java)

    fun updatePassword(token: String, request: ChangePasswordRequest): Call<AuthResponse>{
        return service.updatePassword(token, request)
    }
}