package com.example.myfinanses.ui.main.homebudget.income

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myfinanses.R
import com.example.myfinanses.databinding.FragmentHomeBudgetIncomeBinding
import com.example.myfinanses.ui.extensions.showSnackBar
import com.example.myfinanses.ui.providers.SnackBarProvider

class HomeBudgetIncomeFragment : Fragment() {

    private val args: HomeBudgetIncomeFragmentArgs by navArgs()
    private lateinit var viewModel: HomeBudgetIncomeViewModel
    private lateinit var binding: FragmentHomeBudgetIncomeBinding
    private lateinit var snackBarProvider: SnackBarProvider

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home_budget_income,
            container,
            false
        )

        viewModel = HomeBudgetIncomeViewModel(args.date)

        return binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@HomeBudgetIncomeFragment.viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        snackBarProvider = SnackBarProvider(requireActivity())

        viewModel.addIncome.observe(viewLifecycleOwner) { values ->
            snackBarProvider.showSnackBar(values)

            if (values.first) {
                findNavController().popBackStack()
            }
        }
    }
}