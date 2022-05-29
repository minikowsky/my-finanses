package com.example.myfinanses.ui.account.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myfinanses.R
import com.example.myfinanses.databinding.FragmentLoginBinding
import com.example.myfinanses.ui.extensions.showSnackBar
import com.example.myfinanses.ui.providers.SnackBarProvider

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var snackBarProvider: SnackBarProvider
    private val viewModel = LoginViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        return binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@LoginFragment.viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        snackBarProvider = SnackBarProvider(requireActivity())

        viewModel.login.observe(viewLifecycleOwner) { values ->
            snackBarProvider.showSnackBar(values)
        }

        binding.toRegister.setOnClickListener {
            //TODO Przejście do rejestracji lub można stworzyc pasek na dole z nawigacja
        }
    }
}