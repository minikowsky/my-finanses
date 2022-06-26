package com.example.myfinanses.ui.main.invest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.myfinanses.R
import com.example.myfinanses.data.Asset
import com.example.myfinanses.databinding.FragmentInvestAddBinding
import com.example.myfinanses.ui.extensions.showSnackBar
import com.example.myfinanses.ui.providers.SnackBarProvider

class InvestAddFragment : Fragment() {

    private val viewModel = InvestAddViewModel()
    private lateinit var binding: FragmentInvestAddBinding
    private lateinit var snackBarProvider: SnackBarProvider

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_invest_add,
            container,
            false
        )

        snackBarProvider = SnackBarProvider(requireActivity())

        return binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@InvestAddFragment.viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinnerArray = arrayListOf<String>(
            Asset.GOLD.name,
            Asset.ETH.name,
            Asset.BTC.name)
        binding.addInvestType.adapter = ArrayAdapter(
            requireContext(),
            R.layout.asset_type,
            spinnerArray)

        viewModel.add.observe(viewLifecycleOwner) {
            snackBarProvider.showSnackBar(it)

            if (it.first) {
                findNavController().popBackStack()
            }
        }
    }

}