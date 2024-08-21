package com.prodtechnology.teammatchingadmin.data.remote.service

import com.prodtechnology.teammatchingadmin.data.remote.models.AddResponse
import com.prodtechnology.teammatchingadmin.data.remote.models.Event
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface EventsService {
    @GET("api/admin-events")
    fun getEvents(
        @Header("Authorization") token: String
    ): Call<List<Event>>

    @POST("api/admin-events")
    fun addEvent(
        @Header("Authorization") token: String,
        @Body event: Event
    ) : Call<AddResponse>
}