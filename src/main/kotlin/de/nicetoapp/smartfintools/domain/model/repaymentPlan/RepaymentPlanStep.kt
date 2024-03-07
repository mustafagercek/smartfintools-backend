package de.nicetoapp.smartfintools.domain.model.repaymentPlan

import java.math.BigDecimal
import java.util.Date

data class RepaymentPlanStep(
    val date: Date,
    val amortization: BigDecimal,
    val interest: BigDecimal,
    val remainingDebt: BigDecimal
)