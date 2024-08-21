package com.prodtechnology.teammatchingadmin.utils.status

import com.prodtechnology.teammatchingadmin.data.remote.models.Event

open class EventsStatus {
    data class Succeed(val events: List<Event>): EventsStatus()
    data class Failed(val error: String): EventsStatus()
}