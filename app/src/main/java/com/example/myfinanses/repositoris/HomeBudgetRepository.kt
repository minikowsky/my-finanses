package com.example.myfinanses.repositoris

import androidx.lifecycle.MutableLiveData
import com.example.myfinanses.firebase.FirebaseReference
import com.example.myfinanses.models.Transaction
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class HomeBudgetRepository {

    fun getTransactions(date: String, addTransaction: (List<Transaction>) -> Unit) {
        FirebaseReference.userReference.child(date)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    addTransaction(emptyList())
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val listTransactions = mutableListOf<Transaction>()
                    for (row in snapshot.children) {
                        listTransactions.add(row.getValue(Transaction::class.java)!!)
                    }
                    addTransaction(listTransactions.reversed())
                }
            })
    }

    companion object {
        const val CORRECT = "The transaction has been successfully add."
        const val ERROR = "The transaction could not be added"
    }
}

fun MutableLiveData<Pair<Boolean, String>>.addTransaction(
    transaction: Transaction,
    date: String,
) {
    FirebaseReference.userReference.child(date).push()
        .setValue(transaction)
        .addOnCanceledListener {
            this.postValue(Pair(false, HomeBudgetRepository.ERROR))
        }
        .addOnSuccessListener {
            this.postValue(Pair(true, HomeBudgetRepository.CORRECT))
        }
}