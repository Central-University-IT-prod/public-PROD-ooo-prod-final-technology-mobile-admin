package com.prodtechnology.teammatchingadmin.ui.team


import com.prodtechnology.teammatchingadmin.data.remote.RetrofitClient
import com.prodtechnology.teammatchingadmin.data.remote.models.TeamInfo
import com.prodtechnology.teammatchingadmin.data.remote.service.TeamsMembersService
import retrofit2.Call

class TeamRepository {
    private val client = RetrofitClient.getTeamsMembersClient()
    private val service = client.create(TeamsMembersService::class.java)

    fun getTeamById(token: String, id: Int) : Call<TeamInfo>{
        return service.getTeamById(token, id)
    }
}