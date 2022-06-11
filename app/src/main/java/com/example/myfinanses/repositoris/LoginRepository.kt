package com.example.myfinanses.repositoris

import android.util.Log
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
            Log.d("Sign In", "User $email just signed in!")
            this.postValue(Pair(true, LoginRepository.LOGIN_SUCCESS))
        }
        .addOnFailureListener {
            Log.d("Test", "Attempt $email to sign up was refused!")
            this.postValue(Pair(false, LoginRepository.LOGIN_ERROR))
        }
}