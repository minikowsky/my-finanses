package com.example.myfinanses.retrofit

import com.example.myfinanses.data.Gold
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface GoldDao {
    @GET("XAU/USD")
    @Headers("x-access-token: goldapi-95tjtl4ubemdl-io")
    fun get(): Call<Gold>
}
