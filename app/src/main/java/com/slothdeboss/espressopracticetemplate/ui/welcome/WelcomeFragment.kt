package com.slothdeboss.espressopracticetemplate.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.slothdeboss.espressopracticetemplate.R
import com.slothdeboss.espressopracticetemplate.databinding.FragmentWelcomeBinding
import com.slothdeboss.espressopracticetemplate.ui.util.ArgumentKeys

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding: FragmentWelcomeBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvWelcomeUser.text = getString(R.string.welcome_user, getEmail())
    }

    private fun getEmail(): String {
        val email = requireArguments().getString(ArgumentKeys.EMAIL).orEmpty()
        require(email.isNotBlank()) {
            "Email can't be empty"
        }
        return email
    }

    companion object {

        fun newInstance(email: String): Fragment {
            return WelcomeFragment().apply {
                arguments = bundleOf(
                    ArgumentKeys.EMAIL to email
                )
            }
        }
    }
}