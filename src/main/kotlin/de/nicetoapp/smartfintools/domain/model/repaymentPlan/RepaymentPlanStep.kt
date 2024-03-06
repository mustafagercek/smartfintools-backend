package de.nicetoapp.smartfintools.domain.model.repaymentPlan

import java.util.Date

data class RepaymentPlanStep(
    val date: Date,
    val amortization: Float,
    val interest: Float,
    val remainingDebt: Float
)