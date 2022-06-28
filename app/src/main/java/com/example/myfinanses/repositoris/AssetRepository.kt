package com.example.myfinanses.repositoris

import com.example.myfinanses.data.CryptoCoin
import com.example.myfinanses.data.Gold
import com.example.myfinanses.retrofit.RetrofitInstance
import retrofit2.awaitResponse

class AssetRepository {
    companion object {
        suspend fun getGoldPrice(): Gold? {
            return RetrofitInstance.goldApi.get().awaitResponse().body()
        }

        suspend fun  getBitcoinPrice(): CryptoCoin? {
            return RetrofitInstance.cryptoCoinApi.get("BTC").awaitResponse().body()?.get(0)
        }

        suspend fun  getEthereumPrice(): CryptoCoin? {
            return RetrofitInstance.cryptoCoinApi.get("ETH").awaitResponse().body()?.get(0)
        }
    }
}