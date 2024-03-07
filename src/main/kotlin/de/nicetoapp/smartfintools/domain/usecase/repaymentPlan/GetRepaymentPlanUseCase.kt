package de.nicetoapp.smartfintools.domain.usecase.repaymentPlan

import de.nicetoapp.smartfintools.domain.model.loan.AnnuityLoan
import de.nicetoapp.smartfintools.domain.model.repaymentPlan.RepaymentPlanResponse
import org.springframework.stereotype.Service
import kotlin.math.min

import java.math.BigDecimal
import java.math.RoundingMode

@Service
class GetRepaymentPlanUseCase {
    fun execute(loan: AnnuityLoan): RepaymentPlanResponse {
        val n = BigDecimal(loan.years.toString())

        val monthlyPayment = BigDecimal(loan.getMonthlyPayment().toString()).setScale(2, RoundingMode.HALF_UP)
        val yearlyPayment = monthlyPayment.multiply(BigDecimal("12")).setScale(2, RoundingMode.HALF_UP)
        val totalPayment = yearlyPayment.multiply(n).setScale(2, RoundingMode.HALF_UP)

        val totalAmortizationPayment = BigDecimal(min(loan.getAmortization(forMonth = loan.years * 12), loan.amount).toString()).setScale(2, RoundingMode.HALF_UP)
        val remainingDebt = BigDecimal(loan.amount.toString()).subtract(totalAmortizationPayment).setScale(2, RoundingMode.HALF_UP)
        val totalInterestPayment = totalPayment.subtract(totalAmortizationPayment).setScale(2, RoundingMode.HALF_UP)

        return RepaymentPlanResponse(
            monthlyPayment = monthlyPayment,
            yearlyPayment = yearlyPayment,
            totalPayment = totalPayment,
            remainingDebt = remainingDebt,
            totalInterestPayment = totalInterestPayment,
            totalAmortizationPayment = totalAmortizationPayment
        )
    }
}
