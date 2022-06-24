package com.example.myfinanses.ui.main.userdata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinanses.firebase.FirebaseReference.authFB
import com.example.myfinanses.models.User
import com.example.myfinanses.repositoris.HomeBudgetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDataViewModel : ViewModel() {

    val firstName = MutableLiveData("")
    val lastName = MutableLiveData("")
    val email = MutableLiveData("")

    private val homeBudgetRepository = HomeBudgetRepository()

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            homeBudgetRepository.getUserInfo { user -> addUserInfo(user) }
        }
    }

    private fun addUserInfo(user: User) {
        firstName.value = user.firstName
        lastName.value = user.lastName
        email.value = authFB.currentUser!!.email
    }
}