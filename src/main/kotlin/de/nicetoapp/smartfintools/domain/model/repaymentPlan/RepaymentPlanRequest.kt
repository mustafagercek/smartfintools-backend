package de.nicetoapp.smartfintools.domain.model.repaymentPlan

import org.springframework.format.annotation.DateTimeFormat
import java.util.*

data class RepaymentPlanRequest(
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) val startDate: Date,
        val amount: Float,
        val interestRate: Float,
        val amortizationRate: Float,
        val years: Int
    )