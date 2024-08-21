package com.prodtechnology.teammatchingadmin.ui.registration

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prodtechnology.teammatchingadmin.R
import com.prodtechnology.teammatchingadmin.data.remote.models.AuthResponse
import com.prodtechnology.teammatchingadmin.data.remote.models.User
import com.prodtechnology.teammatchingadmin.utils.status.AuthStatus
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationViewModel(context: Context) : ViewModel() {
    private val repository = RegistrationRepository()

    private val _status: MutableLiveData<AuthStatus> = MutableLiveData()
    val status: LiveData<AuthStatus> = _status

    private val defaultErrorMessage = context.getString(R.string.error_something)

    fun signUpUser(user: User){
        repository.signUpUser(user).enqueue(object : Callback<AuthResponse>{
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                try {
                    if(response.isSuccessful){
                        _status.postValue(AuthStatus.Succeed(response.body()!!.token, user.email))
                    }else{
                        val error = JSONObject(response.errorBody()!!.string()).getJSONArray("detail").getJSONObject(0).getString("msg")
                        _status.postValue(AuthStatus.Failed(error))
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                    _status.postValue(AuthStatus.Failed(defaultErrorMessage))
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                t.printStackTrace()
                _status.postValue(AuthStatus.Failed(defaultErrorMessage))
            }
        })
    }
}