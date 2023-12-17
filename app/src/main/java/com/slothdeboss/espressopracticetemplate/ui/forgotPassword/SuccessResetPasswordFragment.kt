package com.slothdeboss.espressopracticetemplate.ui.forgotPassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.slothdeboss.espressopracticetemplate.R
import com.slothdeboss.espressopracticetemplate.databinding.FragmentSuccessResetPasswordBinding
import com.slothdeboss.espressopracticetemplate.ui.entry.AppEntryFragment
import com.slothdeboss.espressopracticetemplate.ui.util.ArgumentKeys

class SuccessResetPasswordFragment : Fragment() {

    private var _binding: FragmentSuccessResetPasswordBinding? = null
    private val binding: FragmentSuccessResetPasswordBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuccessResetPasswordBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvSuccessMessage.text = getString(
            R.string.reset_password_success_message,
            getEmail()
        )

        binding.btnGood.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.flContainer, AppEntryFragment.newInstance())
            }

        }
    }

    private fun getEmail(): String {
        val email = requireArguments().getString(ArgumentKeys.EMAIL).orEmpty()
        require(email.isNotBlank()) {
            "Email should not be blank"
        }
        return email
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {

        fun newInstance(
            email: String
        ): Fragment {
            return SuccessResetPasswordFragment().apply {
                arguments = bundleOf(
                    ArgumentKeys.EMAIL to email
                )
            }
        }
    }

}
