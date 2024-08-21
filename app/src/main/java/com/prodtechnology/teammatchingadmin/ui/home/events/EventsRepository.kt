package com.prodtechnology.teammatchingadmin.ui.home.events

import com.prodtechnology.teammatchingadmin.data.remote.RetrofitClient
import com.prodtechnology.teammatchingadmin.data.remote.models.Event
import com.prodtechnology.teammatchingadmin.data.remote.service.EventsService
import retrofit2.Call

class EventsRepository {
    private val client = RetrofitClient.getEventsClient()
    private val service = client.create(EventsService::class.java)

    fun getEvents(token: String): Call<List<Event>> {
        return service.getEvents(token)
    }
}