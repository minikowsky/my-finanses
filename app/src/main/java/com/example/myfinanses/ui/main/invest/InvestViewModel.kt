package com.example.myfinanses.ui.main.invest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinanses.data.Asset
import com.example.myfinanses.data.Invest
import com.example.myfinanses.repositoris.AssetRepository
import com.example.myfinanses.repositoris.InvestRepository
import com.example.myfinanses.repositoris.deleteInvest
import com.example.myfinanses.repositoris.deleteTransaction
import com.example.myfinanses.ui.adapters.InvestAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class InvestViewModel : ViewModel() {
    val investmentsAdapter = InvestAdapter { invest -> showDialog(invest)}

    private val investRepository = InvestRepository()

    private val _showDialog = MutableLiveData<Invest>()
    val showDialog: LiveData<Invest>
        get() = _showDialog

    private val _delete = MutableLiveData<Pair<Boolean, String>>()
    val delete: LiveData<Pair<Boolean, String>>
        get() = _delete

    val buyPrice = MutableLiveData("")
    val currentPrice = MutableLiveData("")

    val currentGold = MutableLiveData(0.0)
    val currentEth = MutableLiveData(0.0)
    val currentBtc = MutableLiveData(0.0)

    init {
        getCurrentValues()
        getInvests()
    }

    fun deleteInvest(invest: Invest) {
        viewModelScope.launch(Dispatchers.IO) {
            _delete.deleteInvest(invest)
        }
    }

    private fun getInvests() {
        viewModelScope.launch(Dispatchers.IO) {
            investRepository.getInvests() {
                list -> addInvestToRVAndTotal(list)
            }
        }
    }

    private fun getCurrentValues() {
        viewModelScope.launch(Dispatchers.IO) {

            val goldFromApi = AssetRepository.getGoldPrice()
            if(goldFromApi != null) {
                currentGold.postValue(goldFromApi.price)
            }

            val btcFromApi = AssetRepository.getBitcoinPrice()
            if(btcFromApi != null) {
                currentBtc.postValue(btcFromApi.price_usd)
            }

            val ethFromApi = AssetRepository.getEthereumPrice()
            if(ethFromApi != null) {
                currentEth.postValue(ethFromApi.price_usd)
            }
        }
    }


    private fun addInvestToRVAndTotal(list: List<Invest>) {
        investmentsAdapter.addNewItems(list)

        var buyPriceTemp = 0.0
        var currentPriceTemp = 0.0
        list.forEach {
            buyPriceTemp += it.buyPrice * it.amount
            currentPriceTemp += when(it.assetName) {
                Asset.GOLD -> it.amount * currentGold.value!!
                Asset.ETH -> it.amount * currentEth.value!!
                Asset.BTC -> it.amount * currentBtc.value!!
            }
        }
        buyPrice.value = "${(buyPriceTemp*100.0).roundToInt() / 100.0} $"
        currentPrice.value = "${(currentPriceTemp*100.0).roundToInt() / 100.0} $"
    }

    private fun showDialog(invest: Invest) {
        _showDialog.value = invest
    }
}