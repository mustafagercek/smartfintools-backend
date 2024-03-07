package de.nicetoapp.smartfintools.adapter.controller.repaymentPlan

import de.nicetoapp.smartfintools.domain.model.loan.AnnuityLoan
import de.nicetoapp.smartfintools.domain.model.repaymentPlan.RepaymentPlanRequest
import de.nicetoapp.smartfintools.domain.model.repaymentPlan.RepaymentPlanResponse
import de.nicetoapp.smartfintools.domain.usecase.repaymentPlan.GetRepaymentPlanUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Suppress("unused")
@RestController
@RequestMapping("/repayment-plans")
class RepaymentPlanController {

    @PostMapping
    fun createRepaymentPlan(@RequestBody loanRequest: RepaymentPlanRequest): RepaymentPlanResponse {
        val loan = AnnuityLoan(
            loanRequest.startDate,
            loanRequest.amount,
            loanRequest.interestRate,
            loanRequest.amortizationRate,
            loanRequest.years
        )
        return GetRepaymentPlanUseCase().execute(loan)
    }
}