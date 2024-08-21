package com.prodtechnology.teammatchingadmin.ui.event_info.teams_members


import com.prodtechnology.teammatchingadmin.data.remote.RetrofitClient
import com.prodtechnology.teammatchingadmin.data.remote.models.TeamInfo
import com.prodtechnology.teammatchingadmin.data.remote.service.TeamsMembersService
import com.prodtechnology.teammatchingadmin.data.remote.models.UserInfo
import retrofit2.Call

class TeamsMembersRepository {
    private val client = RetrofitClient.getTeamsMembersClient()
    private val service = client.create(TeamsMembersService::class.java)

    fun getTeamsCall(token: String, eventId: Int): Call<List<TeamInfo>>{
        return service.getTeams(token, eventId)
    }

    fun getMembersCall(token: String, eventId: Int) : Call<List<UserInfo>>{
        return service.getMembers(token, eventId)
    }
}