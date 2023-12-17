package com.slothdeboss.espressopracticetemplate.ui.util

object DefaultValues {
    const val VALID_EMAIL = "example@gmail.com"
    const val VALID_PASSWORD = "Qwerty12345"

    fun isUserExists(
        email: String,
        password: String
    ): Boolean {
        return email == VALID_EMAIL && password == VALID_PASSWORD
    }
}
