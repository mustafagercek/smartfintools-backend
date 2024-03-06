package de.nicetoapp.smartfintools.domain.model.repaymentPlan

/**
 * Represents a repayment plan with detailed financial information.
 */
data class RepaymentPlan(
    val monthlyPayment: Float,
    val yearlyPayment: Float,  // 12 times the monthly payment
    val totalPayment: Float,
    val remainingDebt: Float,
    val totalInterestPayment: Float,
    val totalAmortizationPayment: Float
)
