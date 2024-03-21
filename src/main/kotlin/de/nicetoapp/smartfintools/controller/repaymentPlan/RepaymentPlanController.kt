package de.nicetoapp.smartfintools.controller.repaymentPlan

import de.nicetoapp.smartfintools.domain.entity.loan.AnnuityLoan
import de.nicetoapp.smartfintools.domain.entity.repaymentPlan.RepaymentPlan
import de.nicetoapp.smartfintools.domain.entity.repaymentPlan.RepaymentPlanStep
import de.nicetoapp.smartfintools.domain.usecase.repaymentPlan.GetMonthlyPaymentStepsUseCase
import de.nicetoapp.smartfintools.domain.usecase.repaymentPlan.GetRepaymentPlanUseCase
import de.nicetoapp.smartfintools.domain.usecase.repaymentPlan.GetYearlyPaymentStepsUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/repayment-plan")
class RepaymentPlanController {

    @PostMapping
    fun createRepaymentPlan(@RequestBody loan: AnnuityLoan): RepaymentPlan {
        return GetRepaymentPlanUseCase().execute(loan)
    }

    @PostMapping("/monthly")
    fun createMonthlyRepaymentPlan(@RequestBody loan: AnnuityLoan): List<RepaymentPlanStep> {
        val steps = GetMonthlyPaymentStepsUseCase().execute(loan)
        return steps
    }

    @PostMapping("/yearly")
    fun createYearlyRepaymentPlan(@RequestBody loan: AnnuityLoan): List<RepaymentPlanStep> {
        val steps = GetYearlyPaymentStepsUseCase().execute(loan)
        return steps
    }

    @PostMapping("/test-cpi")
    fun testCPI(@RequestBody loan: AnnuityLoan): List<RepaymentPlanStep> {
        val username = "yourUsername"
        val password = "yourPassword"

        val steps = GetYearlyPaymentStepsUseCase().execute(loan)
        return steps
    }
}
