package com.example.myfinanses.repositoris

import androidx.lifecycle.MutableLiveData
import com.example.myfinanses.firebase.FirebaseReference
import com.example.myfinanses.models.Transaction
import com.example.myfinanses.models.TypeTransaction
import com.example.myfinanses.repositoris.HomeBudgetRepository.Companion.CORRECT_DELETE
import com.example.myfinanses.repositoris.HomeBudgetRepository.Companion.ERROR_DELETE
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class HomeBudgetRepository {

    fun getTransactions(
        date: String,
        type: TypeTransaction,
        addTransaction: (List<Transaction>) -> Unit
    ) {
        FirebaseReference.userReference.child(date)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    addTransaction(emptyList())
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val listTransactions = mutableListOf<Transaction>()
                    for (row in snapshot.children) {
                        val transaction = row.getValue(Transaction::class.java)!!
                        if (type == TypeTransaction.ALL) {
                            listTransactions.add(transaction)
                        } else {
                            if (transaction.type == type) {
                                listTransactions.add(transaction)
                            }
                        }
                    }
                    addTransaction(listTransactions.reversed())
                }
            })
    }

    companion object {
        const val CORRECT_ADD = "The transaction has been successfully add."
        const val ERROR_ADD = "The transaction could not be added."
        const val CORRECT_DELETE = "The transaction has been successfully deleted."
        const val ERROR_DELETE = "The transaction could not be delete."
    }
}

fun MutableLiveData<Pair<Boolean, String>>.addTransaction(
    transaction: Transaction,
    date: String,
) {
    FirebaseReference.userReference.child(date).push()
        .setValue(transaction)
        .addOnCanceledListener {
            this.postValue(Pair(false, HomeBudgetRepository.ERROR_ADD))
        }
        .addOnSuccessListener {
            this.postValue(Pair(true, HomeBudgetRepository.CORRECT_ADD))
        }
}

fun MutableLiveData<Pair<Boolean, String>>.deleteTransaction(
    transaction: Transaction,
    date: String,
) {
    val result = this
    FirebaseReference.userReference.child(date)
        .addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                result.postValue(Pair(false, ERROR_DELETE))
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (row in snapshot.children) {
                    if (row.getValue(Transaction::class.java)!! == transaction) {
                        FirebaseReference.userReference.child(date).child(row.key!!).removeValue()
                        result.postValue(Pair(true, CORRECT_DELETE))
                    }
                }
            }
        })
}