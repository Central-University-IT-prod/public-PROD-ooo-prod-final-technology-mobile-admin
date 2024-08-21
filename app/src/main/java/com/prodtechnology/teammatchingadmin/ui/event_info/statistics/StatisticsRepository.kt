package com.prodtechnology.teammatchingadmin.ui.event_info.statistics

import com.prodtechnology.teammatchingadmin.data.remote.RetrofitClient
import com.prodtechnology.teammatchingadmin.data.remote.models.EventStatistics
import com.prodtechnology.teammatchingadmin.data.remote.models.UploadRequest
import com.prodtechnology.teammatchingadmin.data.remote.service.StatisticsService
import com.prodtechnology.teammatchingadmin.data.remote.service.UploadUsersService
import retrofit2.Call

class StatisticsRepository {
    private val statisticsClient = RetrofitClient.getStatisticsClient()
    private val statisticsService = statisticsClient.create(StatisticsService::class.java)

    private val uploadClient = RetrofitClient.getUploadUsersClient()
    private val uploadService = uploadClient.create(UploadUsersService::class.java)

    fun getStatistics(token: String, eventId: Int) : Call<EventStatistics>{
        return statisticsService.getStatistics(token, eventId)
    }

    fun uploadUsers(token: String, request: UploadRequest): Call<String>{
        return uploadService.uploadUsers(token, request)
    }
}