package com.prodtechnology.teammatchingadmin.ui.add_event

import com.prodtechnology.teammatchingadmin.data.remote.RetrofitClient
import com.prodtechnology.teammatchingadmin.data.remote.models.AddResponse
import com.prodtechnology.teammatchingadmin.data.remote.models.Event
import com.prodtechnology.teammatchingadmin.data.remote.service.EventsService
import retrofit2.Call

class AddEventRepository {
    private val client = RetrofitClient.getEventsClient()
    private val service = client.create(EventsService::class.java)

    fun addEvent(token: String, event: Event): Call<AddResponse>{
        return service.addEvent(token, event)
    }
}