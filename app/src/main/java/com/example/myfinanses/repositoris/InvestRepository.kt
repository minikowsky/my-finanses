package com.example.myfinanses.repositoris

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myfinanses.data.Invest
import com.example.myfinanses.firebase.FirebaseReference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class InvestRepository {

    fun getInvests(getInvests: (List<Invest>) -> Unit) {
        FirebaseReference.userReference.child("Invest")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var list = mutableListOf<Invest>()
                    if(snapshot.exists()) {
                        list = toInvest(snapshot)
                    }
                    getInvests(list.reversed())
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("Firebase database - invest",error.details)
                    getInvests(emptyList())
                }
            })
    }

    fun toInvest(snapshot: DataSnapshot) : MutableList<Invest> {
        val list = mutableListOf<Invest>()
        for(invest in snapshot.children) {
            val value: Invest? = invest.getValue<Invest>(Invest::class.java)
            list.add(value!!)
        }
        return list
    }

    companion object {
        const val CORRECT_ADD = "The invest has been successfully add."
        const val ERROR_ADD = "The invest could not be added."
        const val CORRECT_DELETE = "The invest has been successfully deleted."
        const val ERROR_DELETE = "The invest could not be delete."
    }
}


fun MutableLiveData<Pair<Boolean, String>>.addInvest(
    invest: Invest,
) {
    FirebaseReference.userReference.child("Invest").push()
        .setValue(invest)
        .addOnCanceledListener {
            this.postValue(Pair(false, InvestRepository.ERROR_ADD))
        }
        .addOnSuccessListener {
            this.postValue(Pair(true, InvestRepository.CORRECT_ADD))
        }
}

fun MutableLiveData<Pair<Boolean, String>>.deleteInvest(
    invest: Invest,
) {
    val result = this
    FirebaseReference.userReference.child("Invest")
        .addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                result.postValue(Pair(false, InvestRepository.ERROR_DELETE))
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (row in snapshot.children) {
                    if (row.getValue(Invest::class.java)!! == invest) {
                        FirebaseReference.userReference.child("Invest").child(row.key!!)
                            .removeValue()
                        result.postValue(Pair(true, InvestRepository.CORRECT_DELETE))
                    }
                }
            }
        })
}