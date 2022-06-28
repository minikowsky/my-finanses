package com.example.myfinanses.ui.main.invest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinanses.data.Asset
import com.example.myfinanses.repositoris.AssetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class InvestAssetsValueViewModel : ViewModel() {

    val goldValue = MutableLiveData("")
    val btcValue = MutableLiveData("")
    val ethValue = MutableLiveData("")

    init {
        getCurrentValues()
    }

    private fun getCurrentValues() {
        viewModelScope.launch(Dispatchers.IO) {
            val goldFromApi = AssetRepository.getGoldPrice()
            if(goldFromApi != null) {
                goldValue.postValue("${goldFromApi.price} $")
            }

            val btcFromApi = AssetRepository.getBitcoinPrice()
            if(btcFromApi != null) {
                btcValue.postValue("${(btcFromApi.price_usd*100.0).roundToInt() / 100.0} $")
            }

            val ethFromApi = AssetRepository.getEthereumPrice()
            if(ethFromApi != null) {
                ethValue.postValue("${(ethFromApi.price_usd*100.0).roundToInt() / 100.0} $")
            }
        }
    }
}