package com.example.myfinanses.managers

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateManager {

    private var dateInHomeBudget = LocalDate.now()

    fun getDateHomeBudget(): String =
        dateInHomeBudget.month.toString().lowercase()
            .replaceFirstChar { it.uppercase() } + " " + dateInHomeBudget.year

    fun getCurrentDate(): String {

        val currentDate = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        return currentDate.format(formatter)
    }

    fun nextMonthHomeBudget() {
        val nextMonth = dateInHomeBudget.plusMonths(1)
        dateInHomeBudget = nextMonth
    }

    fun previousMonthHomeBudget() {
        val previousMonth = dateInHomeBudget.minusMonths(1)
        dateInHomeBudget = previousMonth
    }
}