package com.example.myfinanses.data

import java.util.*

data class Invest (
    val assetName: Asset = Asset.GOLD,
    val buyPrice: Double = 0.0,
    val amount: Double = 0.0,
    val date: String = ""
    )