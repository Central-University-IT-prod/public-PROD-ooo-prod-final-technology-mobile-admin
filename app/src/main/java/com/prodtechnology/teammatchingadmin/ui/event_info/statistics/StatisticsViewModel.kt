package com.prodtechnology.teammatchingadmin.ui.event_info.statistics

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prodtechnology.teammatchingadmin.R
import com.prodtechnology.teammatchingadmin.data.remote.models.EventStatistics
import com.prodtechnology.teammatchingadmin.data.remote.models.UploadRequest
import com.prodtechnology.teammatchingadmin.utils.status.StatisticsStatus
import com.prodtechnology.teammatchingadmin.utils.status.UploadStatus
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatisticsViewModel(context: Context) : ViewModel() {
    private val repository = StatisticsRepository()

    private val defaultFailMessage = context.getString(R.string.error_something)

    private val _status: MutableLiveData<StatisticsStatus> = MutableLiveData()
    val status: LiveData<StatisticsStatus> = _status

    private val _uploadStatus: MutableLiveData<UploadStatus> = MutableLiveData()
    val uploadStatus: LiveData<UploadStatus> = _uploadStatus

    fun uploadUsers(token: String, request: UploadRequest){
        repository.uploadUsers("Bearer $token", request).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                try {
                    if(response.isSuccessful){
                        _uploadStatus.postValue(UploadStatus.Succeed())
                    }else{
                        val error = JSONObject(response.errorBody()!!.string())
                            .getJSONArray("detail").getJSONObject(0).getString("msg")
                        Log.d("RemoteErrors", error)
                        _uploadStatus.postValue(UploadStatus.Failed(error))
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                    _uploadStatus.postValue(UploadStatus.Failed(defaultFailMessage))
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                t.printStackTrace()
                _uploadStatus.postValue(UploadStatus.Failed(defaultFailMessage))
            }

        })
    }

    fun getStatistics(token: String, eventId: Int){
        repository.getStatistics("Bearer $token", eventId).enqueue(object : Callback<EventStatistics>{
            override fun onResponse(
                call: Call<EventStatistics>,
                response: Response<EventStatistics>
            ) {
                try {
                    if(response.isSuccessful){
                        _status.postValue(StatisticsStatus.Succeed(response.body()!!))
                    }else{
                        _status.postValue(StatisticsStatus.Failed(defaultFailMessage))
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                    _status.postValue(StatisticsStatus.Failed(defaultFailMessage))
                }
            }

            override fun onFailure(call: Call<EventStatistics>, t: Throwable) {
                t.printStackTrace()
                _status.postValue(StatisticsStatus.Failed(defaultFailMessage))
            }

        })
    }
}