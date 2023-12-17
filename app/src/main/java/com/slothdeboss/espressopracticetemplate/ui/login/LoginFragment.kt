package com.slothdeboss.espressopracticetemplate.ui.login

import android.graphics.Color
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import androidx.core.text.inSpans
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.slothdeboss.espressopracticetemplate.R
import com.slothdeboss.espressopracticetemplate.databinding.FragmentLoginBinding
import com.slothdeboss.espressopracticetemplate.ui.forgotPassword.ForgotPasswordFragment
import com.slothdeboss.espressopracticetemplate.ui.signUp.SignUpFragment
import com.slothdeboss.espressopracticetemplate.ui.util.ArgumentKeys
import com.slothdeboss.espressopracticetemplate.ui.welcome.WelcomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        observeViewModel()
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

        binding.tvForgotPassword.setOnClickListener {
            openForgotPasswordScreen()
        }

        initToolbar()
        initDontHaveAccountText()
    }

    private fun initToolbar() {
        binding.incToolbar.ivBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.incToolbar.tvToolbarTitle.text = getString(R.string.log_in_label)
    }

    private fun initDontHaveAccountText() {
        val dontHaveAccountString = buildSpannedString {
            append(getString(R.string.dont_have_an_account))
            append(" ")
            inSpans(
                object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        openSignUpScreen()
                    }

                }
            ) {
                color(ContextCompat.getColor(requireContext(), R.color.bright_blue)) {
                    append(getString(R.string.sign_up_label))
                }
            }
        }
        binding.tvDontHaveAccount.apply {
            text = dontHaveAccountString
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }
    }

    private fun openSignUpScreen() {
        parentFragmentManager.commit {
            val fragment = SignUpFragment.newInstance(
                email = binding.etEmail.text.toString()
            )
            replace(R.id.flContainer, fragment)
            addToBackStack(fragment::class.simpleName)
        }
    }

    private fun openForgotPasswordScreen() {
        parentFragmentManager.commit {
            val fragment = ForgotPasswordFragment.newInstance(
                email = binding.etEmail.text.toString()
            )
            replace(R.id.flContainer, fragment)
            addToBackStack(fragment::class.simpleName)
        }
    }

    private fun observeViewModel() {
        viewModel.emailValidation.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { result ->
                binding.tilEmail.isErrorEnabled = !result.isValid
                binding.tilEmail.error = result.error
            }
        }
        viewModel.passwordValidation.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { result ->
                binding.tilPassword.isErrorEnabled = !result.isValid
                binding.tilPassword.error = result.error
            }
        }
        viewModel.shouldNavigateForward.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                navigateToWelcomeScreen()
            }
        }
        viewModel.shouldShowUserNotExistsError.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                binding.tvUserNotExists.isVisible = it
            }
        }
    }

    private fun navigateToWelcomeScreen() {
        val email = binding.etEmail.text.toString()
        parentFragmentManager.popBackStack(
            null, FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        parentFragmentManager.commit {
            val fragment = WelcomeFragment.newInstance(email)
            replace(R.id.flContainer, fragment)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {

        fun newInstance(
            email: String? = null
        ): Fragment {
            return LoginFragment().apply {
                arguments = bundleOf(
                    ArgumentKeys.EMAIL to email
                )
            }
        }
    }
}
