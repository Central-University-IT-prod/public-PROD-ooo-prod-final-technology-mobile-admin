package com.prodtechnology.teammatchingadmin.ui.add_event

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prodtechnology.teammatchingadmin.R
import com.prodtechnology.teammatchingadmin.data.remote.models.AddResponse
import com.prodtechnology.teammatchingadmin.data.remote.models.Event
import com.prodtechnology.teammatchingadmin.utils.status.AddEventStatus
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddEventViewModel(context: Context) : ViewModel() {
    private val repository = AddEventRepository()

    private val _status: MutableLiveData<AddEventStatus> = MutableLiveData()
    val status: LiveData<AddEventStatus> = _status

    private val defaultErrorMessage = context.getString(R.string.error_something)

    fun addEvent(token: String, event: Event){
        repository.addEvent("Bearer $token", event).enqueue(object : Callback<AddResponse>{
            override fun onResponse(call: Call<AddResponse>, response: Response<AddResponse>) {
                try {
                    Log.d("AddEvent", response.code().toString())
                    if(response.isSuccessful){
                        _status.postValue(AddEventStatus.Succeed())
                    }else{
                        val error = JSONObject(response.errorBody()!!.string())
                            .getJSONArray("detail").getJSONObject(0).getString("msg")
                        _status.postValue(AddEventStatus.Failed(error))
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                    _status.postValue(AddEventStatus.Failed(defaultErrorMessage))
                }
            }

            override fun onFailure(call: Call<AddResponse>, t: Throwable) {
                t.printStackTrace()
                _status.postValue(AddEventStatus.Failed(defaultErrorMessage))
            }

        })
    }
}