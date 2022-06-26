package com.example.myfinanses.ui.main.invest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myfinanses.R
import com.example.myfinanses.databinding.FragmentInvestAssetsValueBinding

class InvestAssetsValueFragment : Fragment() {

    private val viewModel = InvestAssetsValueViewModel()
    private lateinit var binding: FragmentInvestAssetsValueBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_invest_assets_value,
            container,
            false
        )

        return binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@InvestAssetsValueFragment.viewModel
        }.root
    }
}