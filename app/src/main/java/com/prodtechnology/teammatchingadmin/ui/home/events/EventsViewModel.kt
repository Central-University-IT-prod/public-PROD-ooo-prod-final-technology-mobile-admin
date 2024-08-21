package com.prodtechnology.teammatchingadmin.ui.home.events

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prodtechnology.teammatchingadmin.R
import com.prodtechnology.teammatchingadmin.data.remote.models.Event
import com.prodtechnology.teammatchingadmin.utils.status.EventsStatus
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventsViewModel(
    context: Context
) : ViewModel() {
    private val repository = EventsRepository()

    private val _status: MutableLiveData<EventsStatus> = MutableLiveData()
    val status: LiveData<EventsStatus> = _status

    private val defaultErrorMessage = context.getString(R.string.error_something)

    fun getEvents(token: String){
        repository.getEvents("Bearer $token").enqueue(object : Callback<List<Event>>{
            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                try {
                    val data = response.body()!!
                    _status.postValue(EventsStatus.Succeed(data))
                }catch (e: Exception){
                    val error = JSONObject(response.errorBody()!!.string())
                        .getJSONArray("detail").getJSONObject(0).getString("msg")
                    _status.postValue(EventsStatus.Failed(error))
                }
            }

            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                t.printStackTrace()
                _status.postValue(EventsStatus.Failed(defaultErrorMessage))
            }

        })
    }
}