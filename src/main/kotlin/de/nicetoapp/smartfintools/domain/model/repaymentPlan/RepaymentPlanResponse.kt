package de.nicetoapp.smartfintools.domain.model.repaymentPlan

import java.math.BigDecimal

/**
 * Represents a repayment plan with detailed financial information.
 */
data class RepaymentPlanResponse(
    val monthlyPayment: BigDecimal,
    val yearlyPayment: BigDecimal,
    val totalPayment: BigDecimal,
    val remainingDebt: BigDecimal,
    val totalInterestPayment: BigDecimal,
    val totalAmortizationPayment: BigDecimal
)
