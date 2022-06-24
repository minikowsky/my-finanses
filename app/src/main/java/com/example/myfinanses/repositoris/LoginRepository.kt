package com.example.myfinanses.repositoris

import androidx.lifecycle.MutableLiveData
import com.example.myfinanses.firebase.FirebaseReference

class LoginRepository {

    companion object {
        const val LOGIN_SUCCESS = "Login was successful"
        const val LOGIN_ERROR = "Incorrect data"
    }
}

fun MutableLiveData<Pair<Boolean, String>>.signIn(email: String, password: String) {
    FirebaseReference.authFB.signInWithEmailAndPassword(email, password)
        .addOnSuccessListener {
            this.postValue(Pair(true, LoginRepository.LOGIN_SUCCESS))
        }
        .addOnFailureListener {
            this.postValue(Pair(false, LoginRepository.LOGIN_ERROR))
        }
}