package com.example.myfinanses.data

import com.google.gson.annotations.SerializedName

data class CryptoCoin (
    @SerializedName(value = "price_usd")
    val price_usd: Double
    )