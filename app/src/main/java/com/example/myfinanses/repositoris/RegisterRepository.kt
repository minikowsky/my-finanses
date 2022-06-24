package com.example.myfinanses.repositoris

import androidx.lifecycle.MutableLiveData
import com.example.myfinanses.firebase.FirebaseReference

class RegisterRepository {
    companion object {
        const val REGISTER_SUCCESS = "Register was successful, now log in"
        const val REGISTER_ERROR = "Incorrect data"
    }
}

fun MutableLiveData<Pair<Boolean, String>>.signUp(email: String, password: String) {
    FirebaseReference.authFB.createUserWithEmailAndPassword(email, password)
        .addOnSuccessListener {
            this.postValue(Pair(true, RegisterRepository.REGISTER_SUCCESS))
        }
        .addOnFailureListener {
            this.postValue(Pair(false, RegisterRepository.REGISTER_ERROR))
        }
}