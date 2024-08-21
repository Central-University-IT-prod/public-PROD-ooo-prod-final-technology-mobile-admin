package com.prodtechnology.teammatchingadmin.utils.status

open class UploadStatus {
    class Succeed(): UploadStatus()
    data class Failed(val error: String): UploadStatus()
}