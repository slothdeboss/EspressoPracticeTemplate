package com.slothdeboss.espressopracticetemplate.ui.forgotPassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.slothdeboss.espressopracticetemplate.ui.util.validation.EmailValidator
import com.slothdeboss.espressopracticetemplate.ui.util.validation.ValidationResult

class ForgotPasswordViewModel(
    private val emailValidator: EmailValidator,
) : ViewModel() {

    private val _emailValidation = MutableLiveData<ValidationResult>()
    val emailValidation: LiveData<ValidationResult> = _emailValidation

    fun validateEmail(email: String) {
        _emailValidation.value = emailValidator.validate(email)
    }
}
