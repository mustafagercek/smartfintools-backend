package de.nicetoapp.smartfintools.domain.usecase

import de.nicetoapp.smartfintools.domain.model.repaymentPlan.AnnuityLoan
import de.nicetoapp.smartfintools.domain.model.repaymentPlan.RepaymentPlan
import org.springframework.stereotype.Service
import kotlin.math.min

@Service
class CalculateRepaymentPlanUseCase {
    fun execute(loan: AnnuityLoan): RepaymentPlan {
        val n = loan.years.toFloat()
        val monthlyPayment: Float = loan.getMonthlyPayment()
        val yearlyPayment = monthlyPayment * 12.0f
        val totalPayment = yearlyPayment * n

        val totalAmortizationPayment = min(loan.getAmortization(forMonth = loan.years * 12), loan.amount)
        val remainingDebt = loan.amount - totalAmortizationPayment
        val totalInterestPayment = totalPayment - totalAmortizationPayment

        return RepaymentPlan(
            monthlyPayment = monthlyPayment,
            yearlyPayment = yearlyPayment,
            totalPayment = totalPayment,
            remainingDebt = remainingDebt,
            totalInterestPayment = totalInterestPayment,
            totalAmortizationPayment = totalAmortizationPayment
        )
    }
}
