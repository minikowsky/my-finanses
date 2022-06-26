package com.example.myfinanses.ui.main.invest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.myfinanses.R
import com.example.myfinanses.databinding.FragmentInvestBinding
import com.example.myfinanses.ui.extensions.showSnackBar
import com.example.myfinanses.ui.providers.SnackBarProvider

class InvestFragment : Fragment() {

    private lateinit var binding: FragmentInvestBinding
    private val viewModel = InvestViewModel()
    private lateinit var snackBarProvider: SnackBarProvider

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_invest,
            container,
            false
        )
        snackBarProvider = SnackBarProvider(requireActivity())
        return binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@InvestFragment.viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showDialog.observe(viewLifecycleOwner) {
            viewModel.deleteInvest(it)
        }

        viewModel.delete.observe(viewLifecycleOwner) {
            snackBarProvider.showSnackBar(it)
        }

        binding.buttonAddInvest.setOnClickListener {
            findNavController().navigate(R.id.action_investFragment_to_investAddFragment)
        }

        binding.buttonCurrentPrices.setOnClickListener {
            findNavController().navigate(R.id.action_investFragment_to_investAssetsValueFragment)
        }

    }
}