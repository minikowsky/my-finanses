package com.example.myfinanses.ui.main.homebudget.income

import androidx.lifecycle.*
import com.example.myfinanses.managers.DateManager
import com.example.myfinanses.models.Transaction
import com.example.myfinanses.models.TypeTransaction
import com.example.myfinanses.repositoris.addTransaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeBudgetIncomeViewModel(private val date: String) : ViewModel() {

    private val dateManager = DateManager()

    private val _addIncome = MutableLiveData<Pair<Boolean, String>>()
    val addIncome: LiveData<Pair<Boolean, String>>
        get() = _addIncome

    val name = MutableLiveData("")
    val amount = MutableLiveData("")

    val areInputsCorrect = MediatorLiveData<Boolean>().apply {
        addSource(name) {
            value = isNameCorrect() && isAmountCorrect()
        }
        addSource(amount) {
            value = isNameCorrect() && isAmountCorrect()
        }
    }

    private fun isNameCorrect(): Boolean = name.value!!.isNotEmpty()

    private fun isAmountCorrect(): Boolean {

        if (amount.value!!.isNotEmpty()) {
            if (amount.value!!.toInt() > 0) {
                return true
            }
        }
        return false
    }

    fun addIncome() {
        viewModelScope.launch(Dispatchers.IO) {
            val transaction =
                Transaction(
                    name.value!!,
                    amount.value!!.toInt(),
                    TypeTransaction.INCOME,
                    dateManager.getCurrentDate()
                )
            _addIncome.addTransaction(transaction, date)
        }
    }
}