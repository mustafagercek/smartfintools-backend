package de.nicetoapp.smartfintools.adapter.controller

import de.nicetoapp.smartfintools.domain.model.repaymentPlan.AnnuityLoan
import de.nicetoapp.smartfintools.domain.model.repaymentPlan.RepaymentPlan
import de.nicetoapp.smartfintools.domain.model.repaymentPlan.RepaymentPlanStep
import de.nicetoapp.smartfintools.domain.usecase.CalculateRepaymentPlanUseCase
import de.nicetoapp.smartfintools.domain.usecase.repaymentPlan.GetMonthlyPaymentStepsUseCase
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Date
import kotlin.math.min

@RestController
class LoanController {

    @GetMapping("/calculateRepaymentPlan")
    fun calculateRepaymentPlan(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) startDate: Date,
        @RequestParam amount: Float,
        @RequestParam interestRate: Float,
        @RequestParam amortizationRate: Float,
        @RequestParam years: Int
    ): RepaymentPlan {
        val loan = AnnuityLoan(startDate, amount, interestRate, amortizationRate, years)
        return CalculateRepaymentPlanUseCase().execute(loan)
    }

    @GetMapping("/getMonthlyPaymentSteps")
    fun getMonthlyPaymentSteps(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) startDate: Date,
        @RequestParam amount: Float,
        @RequestParam interestRate: Float,
        @RequestParam amortizationRate: Float,
        @RequestParam years: Int
    ): List<RepaymentPlanStep> {
        val loan = AnnuityLoan(startDate, amount, interestRate, amortizationRate, years)
        return GetMonthlyPaymentStepsUseCase().execute(loan)
    }
}
