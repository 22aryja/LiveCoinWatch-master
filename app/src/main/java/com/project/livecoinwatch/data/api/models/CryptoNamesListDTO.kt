package com.project.livecoinwatch.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CryptoNamesListDTO (
    @SerializedName("Data")
    @Expose
    val cryptoNames: List<CryptoNamesDTO>? = null
)
