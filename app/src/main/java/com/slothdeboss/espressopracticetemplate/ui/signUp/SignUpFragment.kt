package com.slothdeboss.espressopracticetemplate.ui.signUp

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
import com.slothdeboss.espressopracticetemplate.databinding.FragmentSignUpBinding
import com.slothdeboss.espressopracticetemplate.ui.login.LoginFragment
import com.slothdeboss.espressopracticetemplate.ui.util.ArgumentKeys
import com.slothdeboss.espressopracticetemplate.ui.welcome.WelcomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {

    private val viewModel: SignUpViewModel by viewModel()

    private var _binding: FragmentSignUpBinding? = null
    private val binding: FragmentSignUpBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        observeViewModel()
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
        viewModel.confirmPasswordValidation.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { result ->
                binding.tilConfirmPassword.isErrorEnabled = !result.isValid
                binding.tilConfirmPassword.error = result.error
            }
        }
        viewModel.termsError.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                binding.tvTermsError.isVisible = it
            }
        }
        viewModel.shouldNavigateForward.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                navigateToWelcomeScreen()
            }
        }
    }

    private fun initUI() {
        initToolbar()
        initAlreadyHaveAccountText()

        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()
            val agreedToTerms = binding.cbTerms.isChecked

            viewModel.validateSignUpData(
                email = email,
                password = password,
                confirmPassword = confirmPassword,
                agreedToTerms = agreedToTerms
            )
        }
    }

    private fun initAlreadyHaveAccountText() {
        val alreadyHaveAccountString = buildSpannedString {
            append(getString(R.string.already_have_an_account))
            append(" ")
            inSpans(
                object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        openLoginScreen()
                    }

                }
            ) {
                color(ContextCompat.getColor(requireContext(), R.color.bright_blue)) {
                    append(getString(R.string.log_in_label))
                }
            }
        }
        binding.tvAlreadyHaveAnAccount.apply {
            text = alreadyHaveAccountString
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }
    }

    private fun openLoginScreen() {
        parentFragmentManager.commit {
            val email = binding.etEmail.text.toString()
            val fragment = LoginFragment.newInstance(email)
            replace(R.id.flContainer, fragment)
            addToBackStack(fragment::class.simpleName)
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


    private fun initToolbar() {
        binding.incToolbar.tvToolbarTitle.text = getString(R.string.sign_up_label)

        binding.incToolbar.ivBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    companion object {

        fun newInstance(
            email: String? = null
        ): Fragment {
            return SignUpFragment().apply {
                arguments = bundleOf(
                    ArgumentKeys.EMAIL to email
                )
            }
        }
    }
}
