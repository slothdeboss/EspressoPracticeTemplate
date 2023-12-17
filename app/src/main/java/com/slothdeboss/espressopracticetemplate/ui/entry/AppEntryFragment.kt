package com.slothdeboss.espressopracticetemplate.ui.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.slothdeboss.espressopracticetemplate.R
import com.slothdeboss.espressopracticetemplate.databinding.FragmentAppEntryBinding
import com.slothdeboss.espressopracticetemplate.ui.login.LoginFragment
import com.slothdeboss.espressopracticetemplate.ui.signUp.SignUpFragment

class AppEntryFragment : Fragment() {

    private var _binding: FragmentAppEntryBinding? = null
    private val binding: FragmentAppEntryBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppEntryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogIn.setOnClickListener {
            openNextFragment(LoginFragment.newInstance())
        }

        binding.btnSignUp.setOnClickListener {
            openNextFragment(SignUpFragment.newInstance())
        }
    }

    private fun openNextFragment(fragment: Fragment) {
        parentFragmentManager.commit {
            replace(R.id.flContainer, fragment)
            addToBackStack(fragment::class.simpleName)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {

        fun newInstance(): Fragment {
            return AppEntryFragment()
        }
    }
}
