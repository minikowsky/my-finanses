package com.example.myfinanses.ui.main.homebudget.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinanses.models.Transaction
import com.example.myfinanses.repositoris.HomeBudgetRepository
import com.example.myfinanses.ui.adapters.TransactionsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeBudgetHistoryViewModel(private val date: String) : ViewModel() {

    val transactionsAdapter = TransactionsAdapter()

    private val homeBudgetRepository = HomeBudgetRepository()

    init {
        getTransactions()
    }

    private fun getTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            homeBudgetRepository.getTransactions(date) { list -> addTransactionsToRV(list) }
        }
    }

    private fun addTransactionsToRV(list: List<Transaction>) {
        if (list.isNotEmpty()) {
            transactionsAdapter.addNewItems(list)
        }
    }
}