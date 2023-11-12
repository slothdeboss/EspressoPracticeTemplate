package com.slothdeboss.espressopracticetemplate

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _showEmailError = MutableLiveData<Boolean>()
    val showEmailError: LiveData<Boolean> = _showEmailError

    private val _showPasswordError = MutableLiveData<Boolean>()
    val showPasswordError: LiveData<Boolean> = _showPasswordError

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> = _showLoading

    fun validateLoginData(
        email: String,
        password: String
    ) {
        val isEmailValid = email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.isNotBlank() && password.length >= 10

        Log.e("TAG", "EMail $isEmailValid password $isPasswordValid")

        _showEmailError.value = !isEmailValid
        _showPasswordError.value = !isPasswordValid

        _showLoading.value = isEmailValid && isPasswordValid
    }
}
