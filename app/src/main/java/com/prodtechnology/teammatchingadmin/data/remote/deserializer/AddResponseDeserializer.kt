package com.prodtechnology.teammatchingadmin.data.remote.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.prodtechnology.teammatchingadmin.data.remote.models.AddResponse
import java.lang.reflect.Type

class AddResponseDeserializer : JsonDeserializer<AddResponse>{
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): AddResponse {
        return try {
            val error = json!!.asJsonObject.get("detail").asJsonObject.get("msg").asString
            AddResponse(123, error)
        }catch (e: Exception){
            AddResponse(null, "")
        }
    }
}