package de.nicetoapp.smartfintools.adapter.controller.inflation

import de.nicetoapp.smartfintools.adapter.persistance.inflation.CpiRepository
import de.nicetoapp.smartfintools.domain.model.inflation.YearlyCPI
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/inflation")
class InflationController(private val yearlyCpiRepository: CpiRepository) {

    @GetMapping
    fun getAllInflationData(
        @RequestParam(required = false) startYear: Int?,
        @RequestParam(required = false) endYear: Int?
    ): List<YearlyCPI> {
        return if (startYear != null && endYear != null) {
            yearlyCpiRepository.findByYearBetween(startYear, endYear)
        } else {
            yearlyCpiRepository.findAll()
        }
    }
}
