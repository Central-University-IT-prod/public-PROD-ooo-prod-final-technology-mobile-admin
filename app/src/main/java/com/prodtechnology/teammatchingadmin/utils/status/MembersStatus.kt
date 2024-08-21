package com.prodtechnology.teammatchingadmin.utils.status

import com.prodtechnology.teammatchingadmin.data.remote.models.UserInfo

open class MembersStatus {
    data class Succeed(val data: List<UserInfo>): MembersStatus()
    data class Failed(val error: String): MembersStatus()
}