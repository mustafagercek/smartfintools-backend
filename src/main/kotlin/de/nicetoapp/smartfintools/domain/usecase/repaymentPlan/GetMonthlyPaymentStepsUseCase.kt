package de.nicetoapp.smartfintools.domain.usecase.repaymentPlan

import de.nicetoapp.smartfintools.domain.model.repaymentPlan.AnnuityLoan
import de.nicetoapp.smartfintools.domain.model.repaymentPlan.RepaymentPlanStep
import java.util.*
import kotlin.math.round

class GetMonthlyPaymentStepsUseCase {
     fun execute(loan: AnnuityLoan): List<RepaymentPlanStep> {
        val stepCount = loan.years * 12
        val steps = mutableListOf<RepaymentPlanStep>()
        var previousRemainingDebt = loan.amount

        for (i in 1..stepCount) {
            val date = loan.startDate.addMonths(i)
            val monthlyPayment = loan.getMonthlyPayment()
            val remainingDebt = loan.getRemainingDebt(forMonth = i)
            val amortization = previousRemainingDebt - remainingDebt
            val interest = if (remainingDebt == 0f) previousRemainingDebt * loan.interestRate / 12.0 else monthlyPayment - amortization
            
            steps.add(
                RepaymentPlanStep(
                    date = date,
                    amortization = round(amortization),
                    interest = 100f,
                    remainingDebt = round(remainingDebt)
                )
            )
            
            if (remainingDebt == 0f) break

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
