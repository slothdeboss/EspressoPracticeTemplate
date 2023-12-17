package com.slothdeboss.espressopracticetemplate.di

import com.slothdeboss.espressopracticetemplate.ui.forgotPassword.ForgotPasswordViewModel
import com.slothdeboss.espressopracticetemplate.ui.login.LoginViewModel
import com.slothdeboss.espressopracticetemplate.ui.signUp.SignUpViewModel
import com.slothdeboss.espressopracticetemplate.ui.util.validation.ConfirmPasswordValidator
import com.slothdeboss.espressopracticetemplate.ui.util.validation.EmailValidator
import com.slothdeboss.espressopracticetemplate.ui.util.validation.PasswordValidator
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


private val validationModule = module {
    singleOf(::EmailValidator)
    singleOf(::PasswordValidator)
    singleOf(::ConfirmPasswordValidator)
}

private val viewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::ForgotPasswordViewModel)
    viewModelOf(::SignUpViewModel)
}

val appModules = listOf(
    validationModule,
    viewModelModule,
)
