package com.example.myfinanses.ui.providers

import android.app.Activity
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.example.myfinanses.R
import com.google.android.material.snackbar.Snackbar

class SnackBarProvider(private val activity: Activity) {

    fun showSuccess(message: String) {
        showSnackBar(message, R.color.green)
    }

    fun showError(message: String) {
        showSnackBar(message, R.color.red)
    }

    private fun showSnackBar(message: String, @ColorRes color: Int) {
        val snackBar = Snackbar.make(
            activity.findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        )
        snackBar.view.setBackgroundColor(ContextCompat.getColor(activity, color))
        snackBar.show()
    }
}