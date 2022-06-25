package com.example.myfinanses.ui.account.register

import android.util.Patterns
import androidx.lifecycle.*
import com.example.myfinanses.models.User
import com.example.myfinanses.repositoris.signUp
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val _register = MutableLiveData<Pair<Boolean, String>>()
    val register: LiveData<Pair<Boolean, String>>
        get() = _register

    val firstName = MutableLiveData("")
    val lastName = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val passwordAgain = MutableLiveData("")

    val areInputsCorrect = MediatorLiveData<Boolean>().apply {
        addSource(email) {
            value = isPasswordCorrect() && isPasswordAgainCorrect() && isEmailCorrect() &&
                    isFirstNameCorrect() && isLastNameCorrect()
        }
        addSource(password) {
            value = isPasswordCorrect() && isPasswordAgainCorrect() && isEmailCorrect() &&
                    isFirstNameCorrect() && isLastNameCorrect()
        }
        addSource(passwordAgain) {
            value = isPasswordCorrect() && isPasswordAgainCorrect() && isEmailCorrect() &&
                    isFirstNameCorrect() && isLastNameCorrect()
        }
        addSource(firstName) {
            value = isPasswordCorrect() && isPasswordAgainCorrect() && isEmailCorrect() &&
                    isFirstNameCorrect() && isLastNameCorrect()
        }
        addSource(lastName) {
            value = isPasswordCorrect() && isPasswordAgainCorrect() && isEmailCorrect() &&
                    isFirstNameCorrect() && isLastNameCorrect()
        }
    }

    fun register() {
        viewModelScope.launch {
            _register.signUp(
                email.value!!,
                password.value!!,
                User(firstName.value!!, lastName.value!!)
            )
        }
    }

    private fun isEmailCorrect(): Boolean = if (email.value!!.contains('@')) {
        Patterns.EMAIL_ADDRESS.matcher(email.value!!).matches()
    } else {
        email.value!!.isNotBlank()
    }

    private fun isFirstNameCorrect(): Boolean = firstName.value!!.isNotBlank()

    private fun isLastNameCorrect(): Boolean = lastName.value!!.isNotBlank()

    private fun isPasswordCorrect(): Boolean = password.value?.length!! > 5

    private fun isPasswordAgainCorrect(): Boolean = password.value!! == passwordAgain.value!!
}