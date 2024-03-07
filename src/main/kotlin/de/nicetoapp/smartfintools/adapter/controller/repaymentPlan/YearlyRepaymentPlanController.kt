package de.nicetoapp.smartfintools.adapter.controller.repaymentPlan

import de.nicetoapp.smartfintools.domain.model.loan.AnnuityLoan
import de.nicetoapp.smartfintools.domain.model.repaymentPlan.RepaymentPlanRequest
import de.nicetoapp.smartfintools.domain.model.repaymentPlan.RepaymentPlanStepsResponse
import de.nicetoapp.smartfintools.domain.usecase.repaymentPlan.GetYearlyPaymentStepsUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Suppress("unused")
@RestController
@RequestMapping("/yearly-repayment-plans")
class YearlyRepaymentPlanController {

    @PostMapping
    fun createYearlyRepaymentPlan(@RequestBody loanRequest: RepaymentPlanRequest): RepaymentPlanStepsResponse {
        val loan = AnnuityLoan(
            loanRequest.startDate,
            loanRequest.amount,
            loanRequest.interestRate,
            loanRequest.amortizationRate,
            loanRequest.years
        )
        val steps = GetYearlyPaymentStepsUseCase().execute(loan)
        return RepaymentPlanStepsResponse(steps)
    }

}
