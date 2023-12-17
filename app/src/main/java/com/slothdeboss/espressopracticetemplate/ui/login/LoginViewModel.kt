package com.slothdeboss.espressopracticetemplate.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.slothdeboss.espressopracticetemplate.ui.util.DefaultValues
import com.slothdeboss.espressopracticetemplate.ui.util.Event
import com.slothdeboss.espressopracticetemplate.ui.util.validation.EmailValidator
import com.slothdeboss.espressopracticetemplate.ui.util.validation.PasswordValidator
import com.slothdeboss.espressopracticetemplate.ui.util.validation.ValidationResult

class LoginViewModel(
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator,
) : ViewModel() {

    private val _emailValidation = MutableLiveData<Event<ValidationResult>>()
    val emailValidation: LiveData<Event<ValidationResult>> = _emailValidation

    private val _passwordValidation = MutableLiveData<Event<ValidationResult>>()
    val passwordValidation: LiveData<Event<ValidationResult>> = _passwordValidation

    private val _shouldNavigateForward = MutableLiveData<Event<Unit>>()
    val shouldNavigateForward: LiveData<Event<Unit>> = _shouldNavigateForward

    private val _shouldShowUserNotExistsError = MutableLiveData<Event<Boolean>>()
    val shouldShowUserNotExistsError: LiveData<Event<Boolean>> = _shouldShowUserNotExistsError

    fun validateLoginData(
        email: String,
        password: String
    ) {
        val emailValidationResult = emailValidator.validate(email)
        val passwordValidationResult = passwordValidator.validate(password)

        val isDataValid = listOf(emailValidationResult, passwordValidationResult).all { it.isValid }

        _emailValidation.value = Event(emailValidationResult)
        _passwordValidation.value = Event(passwordValidationResult)

        if (isDataValid) {
            val isUserExists =  DefaultValues.isUserExists(email, password)
            _shouldShowUserNotExistsError.value = Event(!isUserExists)
            if (isUserExists) {
                _shouldNavigateForward.value = Event(Unit)
            }
        }
    }
}