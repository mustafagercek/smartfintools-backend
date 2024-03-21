package de.nicetoapp.smartfintools.service

import de.nicetoapp.smartfintools.data.persistance.inflation.CpiParser
import de.nicetoapp.smartfintools.controller.inflation.CpiRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DatabaseInitializer(
    val yearlyCpiRepository: CpiRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        clearDatabase()
        initializeDatabase()
    }

    private fun clearDatabase() {
        yearlyCpiRepository.deleteAll()
        println("Database cleared...")
    }

    private fun initializeDatabase() {
        val cpiData = CpiParser().parseCsvToYearlyCpi("static/seed_cpi.csv")
        yearlyCpiRepository.saveAll(cpiData)
        println("Database initialized...")
    }
}
