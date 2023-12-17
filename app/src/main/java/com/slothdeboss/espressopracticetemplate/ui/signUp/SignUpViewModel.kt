package com.slothdeboss.espressopracticetemplate.ui.signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.slothdeboss.espressopracticetemplate.ui.util.Event
import com.slothdeboss.espressopracticetemplate.ui.util.validation.ConfirmPasswordValidator
import com.slothdeboss.espressopracticetemplate.ui.util.validation.EmailValidator
import com.slothdeboss.espressopracticetemplate.ui.util.validation.PasswordValidator
import com.slothdeboss.espressopracticetemplate.ui.util.validation.ValidationResult

class SignUpViewModel(
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator,
    private val confirmPasswordValidator: ConfirmPasswordValidator,
) : ViewModel() {

    private val _emailValidation = MutableLiveData<Event<ValidationResult>>()
    val emailValidation: LiveData<Event<ValidationResult>> = _emailValidation

    private val _passwordValidation = MutableLiveData<Event<ValidationResult>>()
    val passwordValidation: LiveData<Event<ValidationResult>> = _passwordValidation

    private val _confirmPasswordValidation = MutableLiveData<Event<ValidationResult>>()
    val confirmPasswordValidation: LiveData<Event<ValidationResult>> = _confirmPasswordValidation

    private val _termsError = MutableLiveData<Event<Boolean>>()
    val termsError: LiveData<Event<Boolean>> = _termsError

    private val _shouldNavigateForward = MutableLiveData<Event<Unit>>()
    val shouldNavigateForward: LiveData<Event<Unit>> = _shouldNavigateForward

    fun validateSignUpData(
        email: String,
        password: String,
        confirmPassword: String,
        agreedToTerms: Boolean
    ) {
        val emailValidationResult = emailValidator.validate(email)
        val passwordValidationResult = passwordValidator.validate(password)
        val confirmPasswordResult = confirmPasswordValidator.validate(password, confirmPassword)

        val isDataValid = listOf(
            emailValidationResult,
            passwordValidationResult,
            confirmPasswordResult
        ).all { it.isValid }

        _emailValidation.value = Event(emailValidationResult)
        _passwordValidation.value = Event(passwordValidationResult)
        _confirmPasswordValidation.value = Event(confirmPasswordResult)
        _termsError.value = Event(!agreedToTerms)

        if (isDataValid) {
            _shouldNavigateForward.value = Event(Unit)
        }
    }
}
