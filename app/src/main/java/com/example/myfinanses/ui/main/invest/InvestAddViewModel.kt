package com.example.myfinanses.ui.main.invest

import android.util.Log
import androidx.lifecycle.*
import com.example.myfinanses.data.Asset
import com.example.myfinanses.data.Invest
import com.example.myfinanses.managers.DateManager
import com.example.myfinanses.repositoris.addInvest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InvestAddViewModel : ViewModel() {
    private val dateManager = DateManager()
    private val _add = MutableLiveData<Pair<Boolean, String>>()
    val add: LiveData<Pair<Boolean, String>>
        get() = _add

    val buyPrice = MutableLiveData("")
    val amount = MutableLiveData("")
    val assetType = MutableLiveData(0)

    val areInputsCorrect = MediatorLiveData<Boolean>().apply {
        addSource(buyPrice) {
            value = isAmountCorrect() && isBuyPriceCorrect()
        }
        addSource(amount) {
            value = isAmountCorrect() && isBuyPriceCorrect()
        }
    }

    private fun isAmountCorrect(): Boolean {
        if (amount.value!!.isNotEmpty()) {
            if (amount.value!!.toInt() > 0) {
                return true
            }
        }
        return false
    }

    private fun isBuyPriceCorrect(): Boolean {
        if (buyPrice.value!!.isNotEmpty()) {
            if (buyPrice.value!!.toInt() > 0) {
                return true
            }
        }
        return false
    }

    fun addInvest() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("ASSET TYPE",assetType.value!!.toString())
            val invest = Invest(
                getAsset(assetType.value!!),
                buyPrice.value!!.toDouble(),
                amount.value!!.toDouble(),
                dateManager.getCurrentDate()
            )
            _add.addInvest(invest)
        }
    }

    private fun getAsset(a: Int): Asset {
        val toReturn =  when(a) {
            0 -> Asset.GOLD
            1 -> Asset.ETH
            2 -> Asset.BTC
            else -> {Asset.GOLD}
        }
        return toReturn
    }
}