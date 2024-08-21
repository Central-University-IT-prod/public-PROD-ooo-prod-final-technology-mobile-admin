package com.prodtechnology.teammatchingadmin.utils.status

open class AddEventStatus {
    class Succeed: AddEventStatus()
    data class Failed(val error: String): AddEventStatus()
}