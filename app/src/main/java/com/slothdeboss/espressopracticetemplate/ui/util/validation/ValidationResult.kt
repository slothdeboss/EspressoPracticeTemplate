package com.slothdeboss.espressopracticetemplate.ui.util.validation

data class ValidationResult(
    val isValid: Boolean,
    val error: String?
) {

    companion object {

        fun valid() = ValidationResult(isValid = true, error = null)
        fun invalid(error: String) = ValidationResult(isValid = false, error = error)
    }
}
