package com.prodtechnology.teammatchingadmin.data.remote.service


import com.prodtechnology.teammatchingadmin.data.remote.models.CreateResponse
import com.prodtechnology.teammatchingadmin.data.remote.models.TeamInfo
import com.prodtechnology.teammatchingadmin.data.remote.models.TeamRequest
import com.prodtechnology.teammatchingadmin.data.remote.models.UserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TeamsMembersService {
    @GET("api/teams")
    fun getTeams(
        @Header("Authorization") token: String,
        @Query("event_id") eventId: Int
    ) : Call<List<TeamInfo>>

    @GET("api/admin-events/event-users")
    fun getMembers(
        @Header("Authorization") token: String,
        @Query("event_id") eventId: Int
    ) : Call<List<UserInfo>>

    @GET("api/teams/{team_id}")
    fun getTeamById(
        @Header("Authorization") token: String,
        @Path("team_id") teamId: Int
    ) : Call<TeamInfo>
}