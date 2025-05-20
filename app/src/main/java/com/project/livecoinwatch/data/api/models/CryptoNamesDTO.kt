package com.project.livecoinwatch.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CryptoNamesDTO (
    @SerializedName("CoinInfo")
    @Expose
    val cryptoName: CryptoNameDTO? = null
)
