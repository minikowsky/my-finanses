package com.example.myfinanses.ui.account.register

import android.util.Patterns
import androidx.lifecycle.*
import com.example.myfinanses.repositoris.signUp
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val _register = MutableLiveData<Pair<Boolean, String>>()
    val register: LiveData<Pair<Boolean, String>>
        get() = _register

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordAgain = MutableLiveData<String>()

    val areInputsCorrect = MediatorLiveData<Boolean>().apply {
        addSource(email) {
            value = isEmailCorrect()
        }
        addSource(password) {
            value = isPasswordCorrect()
        }
        addSource(passwordAgain) {
            value = isPasswordCorrect() && isPasswordAgainCorrect()
        }
    }

    fun register() {
        viewModelScope.launch {
            _register.signUp(email.value!!, password.value!!)
        }
    }

    private fun isEmailCorrect(): Boolean = if (email.value!!.contains('@')) {
        Patterns.EMAIL_ADDRESS.matcher(email.value!!).matches()
    } else {
        email.value!!.isNotBlank()
    }

    private fun isPasswordCorrect(): Boolean = password.value?.length!! > 5

    private fun isPasswordAgainCorrect(): Boolean = password.value!! == passwordAgain.value!!

}