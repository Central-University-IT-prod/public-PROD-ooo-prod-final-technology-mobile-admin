package com.prodtechnology.teammatchingadmin.ui.account_info

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prodtechnology.teammatchingadmin.R
import com.prodtechnology.teammatchingadmin.data.local.AppPrefs
import com.prodtechnology.teammatchingadmin.data.remote.models.AuthResponse
import com.prodtechnology.teammatchingadmin.data.remote.models.ChangePasswordRequest
import com.prodtechnology.teammatchingadmin.utils.status.AuthStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountInfoViewModel(context: Context) : ViewModel() {
    private val repository = AccountInfoRepository()

    private val _passwordStatus: MutableLiveData<AuthStatus> = MutableLiveData()
    val passwordStatus: LiveData<AuthStatus> = _passwordStatus

    private val defaultErrorMessage = context.getString(R.string.error_something)

    fun updatePassword(token: String, request: ChangePasswordRequest){
        repository.updatePassword("Bearer $token", request).enqueue(object : Callback<AuthResponse>{
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                try {
                    if(response.isSuccessful){
                        _passwordStatus.postValue(AuthStatus.Succeed(response.body()!!.token, AppPrefs.getEmail()!!))
                    }else{
                        _passwordStatus.postValue(AuthStatus.Failed(response.errorBody()!!.string()))
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                    _passwordStatus.postValue(AuthStatus.Failed(defaultErrorMessage))
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                t.printStackTrace()
                _passwordStatus.postValue(AuthStatus.Failed(defaultErrorMessage))
            }

        })
    }

}