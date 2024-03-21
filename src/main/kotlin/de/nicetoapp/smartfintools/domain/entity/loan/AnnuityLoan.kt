package de.nicetoapp.smartfintools.domain.entity.loan

import java.util.*
import kotlin.math.max
import kotlin.math.pow

data class AnnuityLoan(
    val startDate: Date,
    val amount: Float,
    val interestRate: Float,
    val amortizationRate: Float,
    val years: Int
) {
    private val mAmortization: Float
    /**
     * Monthly interest rate, derived by dividing the annual interest rate by 12.
     * This is used to calculate the interest component of the monthly payment.
     */
    private val mI: Float = interestRate / 12.0f

    /**
     * Monthly amortization rate, derived by dividing the annual amortization rate by 12.
     * This is used to calculate the principal component of the monthly payment.
     */
    private val mA: Float = amortizationRate / 12.0f


    init {
        mAmortization = amount * mA
    }

    fun getMonthlyPayment(): Float {
        val totalRate = amortizationRate + interestRate
        val yearlyPayment = amount * totalRate
        return yearlyPayment / 12.0f
    }

    fun getRemainingDebt(forMonth: Int): Float {
        return max(amount - getAmortization(forMonth), 0f)
    }

    fun getAmortization(forMonth: Int): Float {
        return amount * mA * ((1 + mI).pow(forMonth) - 1) / mI
    }

}
