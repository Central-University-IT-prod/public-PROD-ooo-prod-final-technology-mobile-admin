package com.prodtechnology.teammatchingadmin.data.remote.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.prodtechnology.teammatchingadmin.data.remote.models.AuthResponse
import java.lang.reflect.Type

class AuthResponseDeserializer : JsonDeserializer<AuthResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): AuthResponse {
        val token = json!!.asJsonObject.get("access_token").asString
        val error: String = try {
            json.asJsonObject.get("detail").asJsonArray.get(0).asJsonObject.get("msg").asString
        }catch (e: Exception){
            ""
        }
        return if(token == null){
            AuthResponse("", error)
        }else{
            AuthResponse(token, "")
        }

    }
}