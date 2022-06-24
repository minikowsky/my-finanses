package com.example.myfinanses.repositoris

import androidx.lifecycle.MutableLiveData
import com.example.myfinanses.firebase.FirebaseReference
import com.example.myfinanses.models.User
import com.example.myfinanses.repositoris.RegisterRepository.Companion.USER_INFO

class RegisterRepository {
    companion object {
        const val REGISTER_SUCCESS = "Register was successful, now log in"
        const val REGISTER_ERROR = "Incorrect data"
        const val USER_INFO = "UserInfo"
    }
}

fun MutableLiveData<Pair<Boolean, String>>.signUp(email: String, password: String, user: User) {
    FirebaseReference.authFB.createUserWithEmailAndPassword(email, password)
        .addOnSuccessListener {
            this.postValue(Pair(true, RegisterRepository.REGISTER_SUCCESS))
            FirebaseReference.database.getReference(it.user!!.uid).child(USER_INFO)
                .setValue(user)
        }
        .addOnFailureListener {
            this.postValue(Pair(false, RegisterRepository.REGISTER_ERROR))
        }
}