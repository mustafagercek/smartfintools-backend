package de.nicetoapp.smartfintools.application

import de.nicetoapp.smartfintools.adapter.persistance.inflation.CpiParser
import de.nicetoapp.smartfintools.adapter.persistance.inflation.CpiRepository
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
    }

    private fun initializeDatabase() {
        val cpiData = CpiParser().parseCsvToYearlyCpi("src/main/resources/static/cpi.csv")
        yearlyCpiRepository.saveAll(cpiData)
        println("Database initialized successfully.")
    }
}
