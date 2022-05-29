package com.example.myfinanses.ui.extensions

import com.example.myfinanses.ui.providers.SnackBarProvider

fun SnackBarProvider.showSnackBar(values: Pair<Boolean, String>) {

    val message = values.second
    if (values.first) {
        this.showSuccess(message)
    } else {
        this.showError(message)
    }
}