package com.prodtechnology.teammatchingadmin.ui.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prodtechnology.teammatchingadmin.data.remote.models.TeamInfo
import com.prodtechnology.teammatchingadmin.ui.team.TeamRepository
import com.prodtechnology.teammatchingadmin.utils.status.TeamStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamViewModel : ViewModel() {
    private val repository = TeamRepository()

    private val _teamStatus: MutableLiveData<TeamStatus> = MutableLiveData()
    val teamStatus: LiveData<TeamStatus> = _teamStatus

    fun getTeamById(token: String, id: Int){
        repository.getTeamById("Bearer $token", id).enqueue(object : Callback<TeamInfo>{
            override fun onResponse(call: Call<TeamInfo>, response: Response<TeamInfo>) {
                try {
                    if(response.isSuccessful){
                        _teamStatus.postValue(TeamStatus.Succeed(response.body()!!))
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                    _teamStatus.postValue(TeamStatus.Failed(""))
                }
            }

            override fun onFailure(call: Call<TeamInfo>, t: Throwable) {
                t.printStackTrace()
                _teamStatus.postValue(TeamStatus.Failed(""))
            }
        })
    }
}