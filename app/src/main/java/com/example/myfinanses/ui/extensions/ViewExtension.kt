package com.example.myfinanses.ui.extensions

import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

fun CardView.setColor(@ColorRes colorId: Int) {
    setCardBackgroundColor(ContextCompat.getColor(context, colorId))
}

fun TextView.setColor(@ColorRes colorId: Int) {
    setTextColor(ContextCompat.getColor(context, colorId))
}