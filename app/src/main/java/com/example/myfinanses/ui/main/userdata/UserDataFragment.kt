package com.example.myfinanses.ui.main.userdata

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myfinanses.R
import com.example.myfinanses.databinding.FragmentUserDataBinding
import com.example.myfinanses.ui.account.AccountActivity
import com.example.myfinanses.ui.providers.DeleteTransactionDialogProvider
import com.example.myfinanses.ui.providers.LogOutDialogProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserDataFragment : Fragment() {

    private lateinit var binding: FragmentUserDataBinding
    private lateinit var logOutDialogProvider: LogOutDialogProvider
    private val viewModel = UserDataViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user_data,
            container,
            false
        )

        logOutDialogProvider = LogOutDialogProvider(requireContext())

        return binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@UserDataFragment.viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logout.setOnClickListener {
            logOutDialogProvider.show { startRegisterActivity() }
        }
    }

    private fun startRegisterActivity() {
        Firebase.auth.signOut()
        val intent = Intent(context, AccountActivity::class.java).apply {
            flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        startActivity(intent)
    }
}