package com.example.myfinanses.models

data class Transaction(

    val name: String = "",
    val amount: Int = 0,
    val type: TypeTransaction = TypeTransaction.INCOME,
    val date: String = ""
)
