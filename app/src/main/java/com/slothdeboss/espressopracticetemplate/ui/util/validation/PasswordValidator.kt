package com.slothdeboss.espressopracticetemplate.ui.util.validation

import android.content.Context
import com.slothdeboss.espressopracticetemplate.R

class PasswordValidator(
    private val context: Context
) {

    fun validate(password: String): ValidationResult {
        return when {
            password.isBlank() -> ValidationResult.invalid(context.getString(R.string.password_cant_be_empty))
            password.length < PASSWORD_LENGTH -> ValidationResult.invalid(context.getString(R.string.password_is_too_short))
            else -> ValidationResult.valid()
        }
    }

    companion object {
        private const val PASSWORD_LENGTH = 6
    }
}
