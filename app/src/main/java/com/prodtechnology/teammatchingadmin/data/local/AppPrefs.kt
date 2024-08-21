package com.prodtechnology.teammatchingadmin.data.local

import android.content.Context
import android.content.SharedPreferences
import com.prodtechnology.teammatchingadmin.data.remote.models.Event
import com.prodtechnology.teammatchingadmin.utils.TOKEN_LOCAL_KEY

object AppPrefs {
    private var token: String? = null
    private var userEmail: String? = null
    private var choseEvent: Event? = null
    private var myTeamId: Int? = null
    private var eventId: Int? = null

    private lateinit var prefs: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    fun init(context: Context){
        prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        editor = prefs.edit()

        token = prefs.getString(TOKEN_LOCAL_KEY, null)
    }

    fun setEvent(event: Event){
        this.choseEvent = event
    }

    fun getEvent() = choseEvent
    fun getToken() = token
    fun setToken(token: String?){
        this.token = token

        editor.putString(TOKEN_LOCAL_KEY, token)
        editor.apply()
    }

    fun getEmail() = userEmail
    fun setEmail(newEmail: String){
        this.userEmail = newEmail
    }
    fun setTeamId(id: Int){
        myTeamId = id
    }

    fun getTeamId() = myTeamId

    fun getEventId() = eventId

    fun setEventId(eventId: Int){
        this.eventId = eventId
    }

}