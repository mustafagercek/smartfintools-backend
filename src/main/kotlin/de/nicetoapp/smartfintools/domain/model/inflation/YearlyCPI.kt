package de.nicetoapp.smartfintools.domain.model.inflation

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity
class YearlyCPI(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var year: Int = 0,
    var annualAverage: BigDecimal = BigDecimal.ZERO,
    var jan: BigDecimal = BigDecimal.ZERO,
    var feb: BigDecimal = BigDecimal.ZERO,
    var mar: BigDecimal = BigDecimal.ZERO,
    var apr: BigDecimal = BigDecimal.ZERO,
    var may: BigDecimal = BigDecimal.ZERO,
    var jun: BigDecimal = BigDecimal.ZERO,
    var jul: BigDecimal = BigDecimal.ZERO,
    var aug: BigDecimal = BigDecimal.ZERO,
    var sep: BigDecimal = BigDecimal.ZERO,
    var oct: BigDecimal = BigDecimal.ZERO,
    var nov: BigDecimal = BigDecimal.ZERO,
    var dec: BigDecimal = BigDecimal.ZERO
) {
    constructor() : this(null)
}
