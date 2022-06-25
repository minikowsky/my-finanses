package com.example.myfinanses.ui.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myfinanses.R
import com.example.myfinanses.databinding.ActivityAccountBinding
import com.example.myfinanses.firebase.FirebaseReference
import com.example.myfinanses.firebase.FirebaseReference.authFB
import com.example.myfinanses.firebase.FirebaseReference.database
import com.example.myfinanses.firebase.FirebaseReference.userReference
import com.example.myfinanses.ui.main.MainActivity

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding

    override fun onStart() {
        super.onStart()

        if (isCurrentUser()) {
            startMainActivity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        binding.bottomNavViewAccount.setupWithNavController(navHostFragment.navController)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun isCurrentUser() = authFB.currentUser != null

    private fun startMainActivity() {
        userReference = database.getReference(authFB.currentUser!!.uid)
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        startActivity(intent)
    }
}