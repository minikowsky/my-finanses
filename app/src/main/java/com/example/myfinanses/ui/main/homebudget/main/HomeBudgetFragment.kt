package com.example.myfinanses.ui.main.homebudget.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.myfinanses.R
import com.example.myfinanses.databinding.FragmentHomeBudgetBinding

class HomeBudgetFragment : Fragment() {

    private val viewModel = HomeBudgetViewModel()
    private lateinit var binding: FragmentHomeBudgetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home_budget,
            container,
            false
        )

        return binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@HomeBudgetFragment.viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            expense.setOnClickListener {
                val action =
                    HomeBudgetFragmentDirections.actionHomeBudgetFragmentToHomeBudgetExpenseFragment(
                        viewModel!!.date.value!!
                    )
                findNavController().navigate(action)
            }

            income.setOnClickListener {
                val action =
                    HomeBudgetFragmentDirections.actionHomeBudgetFragmentToHomeBudgetIncomeFragment(
                        viewModel!!.date.value!!
                    )
                findNavController().navigate(action)
            }

            history.setOnClickListener {
                val action =
                    HomeBudgetFragmentDirections.actionHomeBudgetFragmentToHomeBudgetHistoryFragment(
                        viewModel!!.date.value!!
                    )
                findNavController().navigate(action)
            }
        }
    }
}