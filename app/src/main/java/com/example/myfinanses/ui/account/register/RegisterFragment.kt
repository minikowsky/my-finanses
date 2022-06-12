package com.example.myfinanses.ui.account.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.myfinanses.R
import com.example.myfinanses.databinding.FragmentRegisterBinding
import com.example.myfinanses.ui.extensions.showSnackBar
import com.example.myfinanses.ui.providers.SnackBarProvider

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var snackBarProvider: SnackBarProvider
    private val viewModel = RegisterViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register,
            container,
            false
        )
        return binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@RegisterFragment.viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        snackBarProvider = SnackBarProvider(requireActivity())

        viewModel.register.observe(viewLifecycleOwner) { values ->
            snackBarProvider.showSnackBar(values)
            if(values.first){
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment);
            }
        }
    }
}