package de.nicetoapp.smartfintools.domain.usecase.repaymentPlan

import de.nicetoapp.smartfintools.domain.model.loan.AnnuityLoan
import de.nicetoapp.smartfintools.domain.model.repaymentPlan.RepaymentPlanStep
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

class GetYearlyPaymentStepsUseCase {
    fun execute(loan: AnnuityLoan): List<RepaymentPlanStep> {
        val stepCount = loan.years
        val steps = mutableListOf<RepaymentPlanStep>()
        var previousRemainingDebt = BigDecimal(loan.amount.toString())

        for (i in 1..stepCount) {
            val date = loan.startDate.addYears(i)
            val yearlyPayment = BigDecimal(loan.getMonthlyPayment().toString()).multiply(BigDecimal("12"))
            val remainingDebt = BigDecimal(loan.getRemainingDebt(forMonth = i * 12).toString())
            val amortization = previousRemainingDebt.subtract(remainingDebt).setScale(2, RoundingMode.HALF_UP)
            val interest: BigDecimal = if (remainingDebt == BigDecimal.ZERO) {
                previousRemainingDebt.multiply(BigDecimal(loan.interestRate.toString())).setScale(2, RoundingMode.HALF_UP)
            } else {
                yearlyPayment.subtract(amortization).setScale(2, RoundingMode.HALF_UP)
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

    private fun Date.addYears(years: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = this
        calendar.add(Calendar.YEAR, years)
        return calendar.time
    }
}
