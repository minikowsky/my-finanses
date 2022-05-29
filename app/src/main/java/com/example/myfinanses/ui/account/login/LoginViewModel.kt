package com.example.myfinanses.ui.account.login

import androidx.lifecycle.*
import com.example.myfinanses.repositoris.signUp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _logIn = MutableLiveData<Pair<Boolean, String>>()
    val login: LiveData<Pair<Boolean, String>>
        get() = _logIn

    val email = MutableLiveData("")
    val password = MutableLiveData("")

    val areInputsCorrect = MediatorLiveData<Boolean>().apply {
        addSource(email) {
            value = isEmailCorrect() && isPasswordCorrect()
        }
        addSource(password) {
            value = isEmailCorrect() && isPasswordCorrect()
        }
    }

    fun logIn() {
        viewModelScope.launch(Dispatchers.IO) {
            _logIn.signUp(email.value!!, password.value!!)
        }
    }

    private fun isEmailCorrect(): Boolean = email.value!!.isNotEmpty()

    private fun isPasswordCorrect(): Boolean = password.value!!.isNotEmpty()
}