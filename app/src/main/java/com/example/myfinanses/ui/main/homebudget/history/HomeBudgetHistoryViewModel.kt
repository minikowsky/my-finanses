package com.example.myfinanses.ui.main.homebudget.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinanses.models.Transaction
import com.example.myfinanses.models.TypeTransaction
import com.example.myfinanses.repositoris.HomeBudgetRepository
import com.example.myfinanses.repositoris.deleteTransaction
import com.example.myfinanses.ui.adapters.TransactionsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeBudgetHistoryViewModel(private val date: String) : ViewModel() {

    val transactionsAdapter = TransactionsAdapter { transaction -> showDialog(transaction) }

    private val homeBudgetRepository = HomeBudgetRepository()

    private val _showDialog = MutableLiveData<Transaction>()
    val showDialog: LiveData<Transaction>
        get() = _showDialog

    private val _delete = MutableLiveData<Pair<Boolean, String>>()
    val delete: LiveData<Pair<Boolean, String>>
        get() = _delete

    init {
        getAllTransactions()
    }

    fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch(Dispatchers.IO) {
            _delete.deleteTransaction(transaction, date)
        }
    }

    fun getIncomes() {
        getTransactions(TypeTransaction.INCOME)
    }

    fun getExpenses() {
        getTransactions(TypeTransaction.EXPENSE)
    }

    fun getAllTransactions() {
        getTransactions(TypeTransaction.ALL)
    }

    private fun getTransactions(type: TypeTransaction) {
        viewModelScope.launch(Dispatchers.IO) {
            homeBudgetRepository.getTransactions(
                date,
                type
            ) { list -> addTransactionsToRV(list) }
        }
    }

    private fun addTransactionsToRV(list: List<Transaction>) {
        transactionsAdapter.addNewItems(list)
    }

    private fun showDialog(transaction: Transaction) {
        _showDialog.value = transaction
    }
}