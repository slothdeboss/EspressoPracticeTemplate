package com.slothdeboss.espressopracticetemplate.ui.util.validation

import android.content.Context
import com.slothdeboss.espressopracticetemplate.R

class ConfirmPasswordValidator(
    private val passwordValidator: PasswordValidator,
    private val context: Context,
) {

    fun validate(
        password: String,
        confirmPassword: String
    ): ValidationResult {
        val confirmPasswordValidation = passwordValidator.validate(confirmPassword)
        return when {
            !confirmPasswordValidation.isValid -> confirmPasswordValidation
            confirmPassword != password -> ValidationResult.invalid(context.getString(R.string.passwords_should_match))
            else -> ValidationResult.valid()
        }
    }
}