package de.nicetoapp.smartfintools.adapter.persistance.inflation

import de.nicetoapp.smartfintools.domain.model.inflation.YearlyCPI
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CpiRepository : JpaRepository<YearlyCPI, Long> {
    fun findByYearBetween(startYear: Int, endYear: Int): List<YearlyCPI>
    fun findByYearGreaterThanEqual(startYear: Int): List<YearlyCPI>
    fun findByYearLessThanEqual(endYear: Int): List<YearlyCPI>

}
