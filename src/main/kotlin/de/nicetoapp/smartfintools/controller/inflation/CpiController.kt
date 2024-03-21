package de.nicetoapp.smartfintools.controller.inflation

import de.nicetoapp.smartfintools.domain.entity.inflation.YearlyCPI
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Suppress("unused")
@RestController
@RequestMapping("api/cpi-data")
class CpiController(private val yearlyCpiRepository: CpiRepository) {

    @GetMapping("/yearly")
    fun getAllInflationData(
        @RequestParam(required = false) startYear: Int?,
        @RequestParam(required = false) endYear: Int?
    ): List<YearlyCPI> {
        return when {
            startYear != null && endYear != null -> yearlyCpiRepository.findByYearBetween(startYear, endYear)
            startYear != null -> yearlyCpiRepository.findByYearGreaterThanEqual(startYear)
            endYear != null -> yearlyCpiRepository.findByYearLessThanEqual(endYear)
            else -> yearlyCpiRepository.findAll()
        }
    }
}
