package com.project.livecoinwatch.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CryptoNameDTO (
    @SerializedName("Name")
    @Expose
    val name: String? = null
)
