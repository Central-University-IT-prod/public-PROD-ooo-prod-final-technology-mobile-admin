package com.prodtechnology.teammatchingadmin.utils.status

import com.prodtechnology.teammatchingadmin.data.remote.models.TeamInfo

open class TeamStatus {
    data class Succeed(val team: TeamInfo) : TeamStatus()
    data class Failed(val error: String) : TeamStatus()
}