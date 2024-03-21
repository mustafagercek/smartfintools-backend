package de.nicetoapp.smartfintools.domain.usecase.repaymentPlan

import de.nicetoapp.smartfintools.domain.entity.loan.AnnuityLoan
import de.nicetoapp.smartfintools.domain.entity.repaymentPlan.RepaymentPlanStep
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

class GetMonthlyPaymentStepsUseCase {
    fun execute(loan: AnnuityLoan): List<RepaymentPlanStep> {
        val stepCount = loan.years * 12
        val steps = mutableListOf<RepaymentPlanStep>()
        var previousRemainingDebt = BigDecimal(loan.amount.toString())

        for (i in 1..stepCount) {
            val date = loan.startDate.addMonths(i)
            val monthlyPayment = BigDecimal(loan.getMonthlyPayment().toString())
            val remainingDebt = BigDecimal(loan.getRemainingDebt(forMonth = i).toString())
            val amortization = previousRemainingDebt.subtract(remainingDebt).setScale(2, RoundingMode.HALF_UP)
            val interest: BigDecimal = if (remainingDebt == BigDecimal.ZERO) {
                previousRemainingDebt.multiply(BigDecimal(loan.interestRate.toString())).divide(BigDecimal("12"), 2, RoundingMode.HALF_UP)
            } else {
                monthlyPayment.subtract(amortization).setScale(2, RoundingMode.HALF_UP)
            }

            steps.add(
                RepaymentPlanStep(
                    date = date,
                    amortization = amortization,
                    interest = interest,
                    remainingDebt = remainingDebt.setScale(2, RoundingMode.HALF_UP)
                )
            )

            if (remainingDebt == BigDecimal.ZERO) break

            previousRemainingDebt = remainingDebt
        }

        return steps
    }

    private fun Date.addMonths(months: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = this
        calendar.add(Calendar.MONTH, months)
        return calendar.time
    }
}
