package com.example.myfinanses.ui.providers

import android.app.AlertDialog
import android.content.Context
import com.example.myfinanses.models.Transaction

class DeleteTransactionDialogProvider(val context: Context) {

    fun show(positiveResult: () -> Unit, transactionName: String) {

        val builder = AlertDialog.Builder(context)

        builder.setTitle("Removing Transaction")
        builder.setMessage("Are you sure you want to delete ${transactionName}?")
        builder.setCancelable(false)
        builder.setNegativeButton("NO") { dialog, _ -> dialog.cancel() }
        builder.setPositiveButton("YES") { _, _ -> positiveResult() }

        builder.create().show()
    }
}