package com.prodtechnology.teammatchingadmin.data.remote

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.prodtechnology.teammatchingadmin.data.remote.models.AuthResponse
import com.prodtechnology.teammatchingadmin.data.remote.deserializer.AuthResponseDeserializer
import com.prodtechnology.teammatchingadmin.data.remote.models.AddResponse
import com.prodtechnology.teammatchingadmin.data.remote.deserializer.AddResponseDeserializer
import com.prodtechnology.teammatchingadmin.data.remote.deserializer.UploadResponseDeserializer
import com.prodtechnology.teammatchingadmin.utils.API_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var loginClient: Retrofit? = null
    private var eventsClient: Retrofit? = null
    private var accountInfoClient: Retrofit? = null
    private var teamsMembersClient: Retrofit? = null
    private var statisticsClient: Retrofit? = null
    private var usersUploadClient: Retrofit? = null
    private var teamClient: Retrofit? = null

    fun getTeamClient(): Retrofit{
        return teamClient ?: run {
            teamClient = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            teamClient!!
        }
    }

    fun getUploadUsersClient(): Retrofit{
        return usersUploadClient ?: run {
            usersUploadClient = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                    GsonBuilder().registerTypeAdapter(object : TypeToken<String>() {}.type,
                        UploadResponseDeserializer()).create()
                ))
                .build()
            usersUploadClient!!
        }
    }

    fun getStatisticsClient(): Retrofit{
        return statisticsClient ?: run {
            statisticsClient = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            statisticsClient!!
        }
    }

    fun getAccountClient(): Retrofit{
        return loginClient ?: run {
            loginClient = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                    GsonBuilder().registerTypeAdapter(object : TypeToken<AuthResponse>() {}.type,
                        AuthResponseDeserializer()).create()
                ))
                .build()
            loginClient!!
        }
    }

    fun getEventsClient(): Retrofit{
        return eventsClient ?: run {
            eventsClient = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                    GsonBuilder().registerTypeAdapter(object : TypeToken<AddResponse>() {}.type,
                        AddResponseDeserializer()).create()
                ))
                .build()
            eventsClient!!
        }
    }

    fun getAccountInfoClient(): Retrofit{
        return accountInfoClient ?: run {
            accountInfoClient = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            accountInfoClient!!
        }
    }

    fun getTeamsMembersClient(): Retrofit{
        return teamsMembersClient ?: run {
            teamsMembersClient = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            teamsMembersClient!!
        }
    }
}