package com.example.myfinanses.ui.main.invest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinanses.data.Invest
import com.example.myfinanses.repositoris.InvestRepository
import com.example.myfinanses.repositoris.deleteInvest
import com.example.myfinanses.repositoris.deleteTransaction
import com.example.myfinanses.ui.adapters.InvestAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InvestViewModel : ViewModel() {
    val investmentsAdapter = InvestAdapter { invest -> showDialog(invest)}

    private val investRepository = InvestRepository()

    private val _showDialog = MutableLiveData<Invest>()
    val showDialog: LiveData<Invest>
        get() = _showDialog

    private val _delete = MutableLiveData<Pair<Boolean, String>>()
    val delete: LiveData<Pair<Boolean, String>>
        get() = _delete

    val total = MutableLiveData("")

    init {
        getInvests()
    }

    fun deleteInvest(invest: Invest) {
        viewModelScope.launch(Dispatchers.IO) {
            _delete.deleteInvest(invest)
        }
    }

    fun getInvests() {
        viewModelScope.launch(Dispatchers.IO) {
            investRepository.getInvests() {
                list -> addInvestToRVAndTotal(list)
            }
        }
    }

    private fun addInvestToRVAndTotal(list: List<Invest>) {
        investmentsAdapter.addNewItems(list)
        //TODO:
        var temp = 0.0
        list.forEach {
            temp += it.buyPrice
        }
        total.value = "$temp $"
    }

    private fun showDialog(invest: Invest) {
        _showDialog.value = invest
    }
}