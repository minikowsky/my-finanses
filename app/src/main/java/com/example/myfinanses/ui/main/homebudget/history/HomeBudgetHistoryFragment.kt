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
import com.example.myfinanses.ui.extensions.showSnackBar
import com.example.myfinanses.ui.providers.DeleteTransactionDialogProvider
import com.example.myfinanses.ui.providers.SnackBarProvider

class HomeBudgetHistoryFragment : Fragment() {

    private val args: HomeBudgetHistoryFragmentArgs by navArgs()
    private lateinit var viewModel: HomeBudgetHistoryViewModel
    private lateinit var binding: FragmentHomeBudgetHistoryBinding
    private lateinit var deleteTransactionDialogProvider: DeleteTransactionDialogProvider
    private lateinit var snackBarProvider: SnackBarProvider

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
        deleteTransactionDialogProvider = DeleteTransactionDialogProvider(requireContext())
        snackBarProvider = SnackBarProvider(requireActivity())

        return binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@HomeBudgetHistoryFragment.viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showDialog.observe(viewLifecycleOwner) { transition ->
            deleteTransactionDialogProvider.show(
                { viewModel.deleteTransaction(transition) }, transition.name
            )
        }

        viewModel.delete.observe(viewLifecycleOwner) { values ->
            snackBarProvider.showSnackBar(values)
        }
    }
}