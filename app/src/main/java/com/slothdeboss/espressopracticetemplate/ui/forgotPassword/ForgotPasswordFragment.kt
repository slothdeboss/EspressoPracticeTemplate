package com.slothdeboss.espressopracticetemplate.ui.forgotPassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.slothdeboss.espressopracticetemplate.R
import com.slothdeboss.espressopracticetemplate.databinding.FragmentForgotPasswordBinding
import com.slothdeboss.espressopracticetemplate.ui.util.ArgumentKeys
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgotPasswordFragment : Fragment() {

    private val viewModel: ForgotPasswordViewModel by viewModel()

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding: FragmentForgotPasswordBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.emailValidation.observe(viewLifecycleOwner) { result ->
            if (result.isValid) {
                openSuccessResetPasswordFragment()
            } else {
                binding.tilEmail.error = result.error
                binding.tilEmail.isErrorEnabled = !result.error.isNullOrBlank()
            }
        }
    }

    private fun initUI() {
        initToolbar()

        binding.btnReset.setOnClickListener {
            val email = binding.etEmail.text.toString()

            viewModel.validateEmail(email)
        }

        getEmail()?.let { email ->
            binding.etEmail.setText(email)
        }
    }

    private fun openSuccessResetPasswordFragment() {
        val email = binding.etEmail.text.toString()
        parentFragmentManager.popBackStack(
            null, FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        parentFragmentManager.commit {
            val fragment = SuccessResetPasswordFragment.newInstance(email)
            replace(R.id.flContainer, fragment)
        }
    }

    private fun initToolbar() {
        binding.incToolbar.tvToolbarTitle.text = getString(R.string.forgot_password)

        binding.incToolbar.ivBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun getEmail() = arguments?.getString(ArgumentKeys.EMAIL)

    companion object {

        fun newInstance(
            email: String? = null
        ): Fragment {
            return ForgotPasswordFragment().apply {
                arguments = bundleOf(
                    ArgumentKeys.EMAIL to email
                )
            }
        }
    }
}
