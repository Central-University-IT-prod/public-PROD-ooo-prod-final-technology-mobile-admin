package com.prodtechnology.teammatchingadmin.utils.status

import com.prodtechnology.teammatchingadmin.data.remote.models.EventStatistics

open class StatisticsStatus {
    data class Succeed(val data: EventStatistics): StatisticsStatus()
    data class Failed(val error: String): StatisticsStatus()
}