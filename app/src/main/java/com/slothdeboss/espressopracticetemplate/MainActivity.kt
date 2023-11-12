package com.slothdeboss.espressopracticetemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.slothdeboss.espressopracticetemplate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.showEmailError.observe(this) { shouldShow ->
            binding.tilEmail.isErrorEnabled = shouldShow
            binding.tilEmail.error = if (shouldShow) {
                getString(R.string.email_error)
            } else {
                null
            }
        }
        viewModel.showPasswordError.observe(this) { shouldShow ->
            Log.e("TAG", "Show password $shouldShow")
            binding.tilPassword.isErrorEnabled = shouldShow
            binding.tilPassword.error = if (shouldShow) {
                getString(R.string.password_error)
            } else {
                null
            }
        }
        viewModel.showLoading.observe(this) { shouldShow ->
            binding.cpiLoading.isVisible = shouldShow
        }
    }

    private fun initUI() {
        binding.btnLogIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.validateLoginData(
                email = email,
                password = password
            )
        }
    }
}
