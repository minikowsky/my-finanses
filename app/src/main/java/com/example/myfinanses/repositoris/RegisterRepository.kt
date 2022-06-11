package com.example.myfinanses.repositoris

import android.util.Log
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
            Log.d("SIGN UP","User $email just signed up!")
            this.postValue(Pair(true, RegisterRepository.REGISTER_SUCCESS))
        }
        .addOnFailureListener {
            Log.d("SIGN UP","Attempt $email to sign up was refused!")
            this.postValue(Pair(true, RegisterRepository.REGISTER_ERROR))
        }
}