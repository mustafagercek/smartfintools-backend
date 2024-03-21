package de.nicetoapp.smartfintools.domain.entity.inflation

import de.nicetoapp.smartfintools.domain.entity.common.Country
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id

//@Entity
//class YearlyConsumerPriceIndex(
//    @Id
//    val year: Int,
//    @Enumerated(EnumType.STRING)
//    val country: Country,
//    val indexValue: Double,
//    val yoyChange: Double?,
//    val momChange: Double?,
//)