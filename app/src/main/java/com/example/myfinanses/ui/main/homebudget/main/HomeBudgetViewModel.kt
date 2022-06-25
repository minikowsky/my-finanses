package com.example.myfinanses.ui.main.homebudget.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinanses.managers.DateManager
import com.example.myfinanses.managers.TransactionManager
import com.example.myfinanses.models.Transaction
import com.example.myfinanses.models.TypeTransaction
import com.example.myfinanses.repositoris.HomeBudgetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeBudgetViewModel : ViewModel() {

    private val dateManager = DateManager()
    private val homeBudgetRepository = HomeBudgetRepository()
    private val transactionManager = TransactionManager()

    val date = MutableLiveData("")
    val balance = MutableLiveData("")
    val incomes = MutableLiveData("")
    val expenses = MutableLiveData("")


    init {
        date.value = dateManager.getDateHomeBudget()
        clearValues()
        getTransactions()
    }

    fun previousMonth() {
        dateManager.previousMonthHomeBudget()
        date.value = dateManager.getDateHomeBudget()
        getTransactions()
    }

    fun nextMonth() {
        dateManager.nextMonthHomeBudget()
        date.value = dateManager.getDateHomeBudget()
        getTransactions()
    }

    private fun getTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            homeBudgetRepository.getTransactions(
                date.value!!,
                TypeTransaction.ALL
            ) { list -> addTransactions(list) }
        }
    }

    private fun addTransactions(transactions: List<Transaction>) {
        if (transactions.isNotEmpty()) {
            transactionManager.addTransactions(transactions)
            balance.value = transactionManager.getBalance()
            incomes.value = transactionManager.getIncomes()
            expenses.value = transactionManager.getExpense()
        } else {
            clearValues()
        }
    }

    private fun clearValues() {
        balance.value = "0 zł"
        incomes.value = "0 zł"
        expenses.value = "0 zł"
    }
}