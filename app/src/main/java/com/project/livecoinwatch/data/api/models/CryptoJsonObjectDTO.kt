package com.project.livecoinwatch.data.api.models

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CryptoJsonObjectDTO (
    @SerializedName("RAW")
    @Expose
    val cryptoJsonObject: JsonObject? = null
)
