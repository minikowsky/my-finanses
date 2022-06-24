package com.example.myfinanses.managers

import com.example.myfinanses.models.Transaction
import com.example.myfinanses.models.TypeTransaction

class TransactionManager() {

    private var incomes = 0
    private var expense = 0
    private var balance = 0

    fun addTransactions(transactions: List<Transaction>) {
        clearValues()
        transactions.forEach { transaction ->
            when (transaction.type) {
                TypeTransaction.INCOME -> incomes += transaction.amount
                TypeTransaction.EXPENSE -> expense += transaction.amount
            }
        }
        balance = incomes - expense
    }

    fun getIncomes() = "$incomes $CURRENCY"

    fun getExpense() = "$expense $CURRENCY"

    fun getBalance() = "$balance $CURRENCY"

    private fun clearValues() {
        incomes = 0
        expense = 0
        balance = 0
    }

    companion object {
        private const val CURRENCY = "zł"
    }
}