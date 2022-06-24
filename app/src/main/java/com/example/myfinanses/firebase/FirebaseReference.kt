package com.example.myfinanses.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

object FirebaseReference {

    val authFB = FirebaseAuth.getInstance()

    private val database =
        FirebaseDatabase.getInstance("https://my-finanses-40a0d-default-rtdb.europe-west1.firebasedatabase.app/")
    val userReference = database.getReference(authFB.currentUser!!.uid)
}