package com.prodtechnology.teammatchingadmin.utils.status

import com.prodtechnology.teammatchingadmin.data.remote.models.TeamInfo

open class TeamsStatus {
    data class Succeed(val data: List<TeamInfo>): TeamsStatus()
    data class Failed(val error: String): TeamsStatus()
}