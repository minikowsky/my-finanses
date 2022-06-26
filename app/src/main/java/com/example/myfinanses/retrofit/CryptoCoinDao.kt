package com.example.myfinanses.retrofit

import com.example.myfinanses.data.CryptoCoin
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoCoinDao {
    @GET("/v1/assets/{asset_id}")
    @Headers("X-CoinAPI-Key: B3A37170-52B4-4705-A99B-6C0EEB28834C")
    fun get(@Path("asset_id") assetId: String): Call<List<CryptoCoin>>
}