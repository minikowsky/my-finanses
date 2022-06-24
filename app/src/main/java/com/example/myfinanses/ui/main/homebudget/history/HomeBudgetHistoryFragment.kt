package com.example.myfinanses.ui.main.homebudget.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.myfinanses.R
import com.example.myfinanses.databinding.FragmentHomeBudgetHistoryBinding

class HomeBudgetHistoryFragment : Fragment() {

    private val args: HomeBudgetHistoryFragmentArgs by navArgs()
    private lateinit var viewModel: HomeBudgetHistoryViewModel
    private lateinit var binding: FragmentHomeBudgetHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home_budget_history,
            container,
            false
        )

        viewModel = HomeBudgetHistoryViewModel(args.date)

        return binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@HomeBudgetHistoryFragment.viewModel
        }.root
    }
}