package com.example.myfinanses.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val GOLD_API_URL = "https://www.goldapi.io/api/"
const val COIN_API_URL = "https://rest.coinapi.io/"

object RetrofitInstance {
    private val goldRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(GOLD_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val goldApi: GoldDao by lazy {
        goldRetrofit.create(GoldDao::class.java)
    }

    private val cryptoCoinRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(COIN_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val cryptoCoinApi: CryptoCoinDao by lazy {
        cryptoCoinRetrofit.create(CryptoCoinDao::class.java)
    }
}