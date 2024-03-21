package de.nicetoapp.smartfintools.domain.entity.repaymentPlan

import org.springframework.format.annotation.DateTimeFormat
import java.util.*

data class Loan(
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) val startDate: Date,
        val amount: Float,
        val interestRate: Float,
        val amortizationRate: Float,
        val years: Int
    )