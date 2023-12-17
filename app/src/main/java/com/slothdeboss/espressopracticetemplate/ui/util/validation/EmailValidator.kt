package com.slothdeboss.espressopracticetemplate.ui.util.validation

import android.content.Context
import android.util.Patterns
import com.slothdeboss.espressopracticetemplate.R

class EmailValidator(
    private val context: Context
) {

    fun validate(email: String): ValidationResult {
        return when {
            email.isBlank() -> ValidationResult.invalid(context.getString(R.string.empty_email_error))
            !isEmail(email) -> ValidationResult.invalid(context.getString(R.string.email_error))
            else -> ValidationResult.valid()
        }
    }

    private fun isEmail(value: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(value).matches()
    }
}