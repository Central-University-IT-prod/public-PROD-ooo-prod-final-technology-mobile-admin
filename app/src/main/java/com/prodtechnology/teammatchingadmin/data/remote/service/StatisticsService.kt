package com.prodtechnology.teammatchingadmin.data.remote.service

import com.prodtechnology.teammatchingadmin.data.remote.models.EventStatistics
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface StatisticsService {
    @GET("api/admin-events/stats")
    fun getStatistics(
        @Header("Authorization") token: String,
        @Query("event_id") id: Int
    ) : Call<EventStatistics>
}