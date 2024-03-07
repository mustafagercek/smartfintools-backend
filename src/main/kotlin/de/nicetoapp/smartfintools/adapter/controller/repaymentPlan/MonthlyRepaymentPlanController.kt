package de.nicetoapp.smartfintools.adapter.controller.repaymentPlan

import de.nicetoapp.smartfintools.domain.model.loan.AnnuityLoan
import de.nicetoapp.smartfintools.domain.model.repaymentPlan.MonthlyRepaymentPlanResponse
import de.nicetoapp.smartfintools.domain.model.repaymentPlan.RepaymentPlanRequest
import de.nicetoapp.smartfintools.domain.usecase.repaymentPlan.GetMonthlyPaymentStepsUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Suppress("unused")
@RestController
@RequestMapping("/monthly-repayment-plans")
class MonthlyRepaymentPlanController {

    @PostMapping
    fun createMonthlyRepaymentPlan(@RequestBody loanRequest: RepaymentPlanRequest): MonthlyRepaymentPlanResponse {
        val loan = AnnuityLoan(
            loanRequest.startDate,
            loanRequest.amount,
            loanRequest.interestRate,
            loanRequest.amortizationRate,
            loanRequest.years
        )
        val steps = GetMonthlyPaymentStepsUseCase().execute(loan)
        return MonthlyRepaymentPlanResponse(steps)
    }

}
