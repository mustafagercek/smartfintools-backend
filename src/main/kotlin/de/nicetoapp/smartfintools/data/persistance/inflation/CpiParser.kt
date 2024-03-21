package de.nicetoapp.smartfintools.data.persistance.inflation

import de.nicetoapp.smartfintools.domain.entity.inflation.YearlyCPI
import org.springframework.core.io.ClassPathResource
import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.BigDecimal

class CpiParser {

    fun parseCsvToYearlyCpi(filePath: String): List<YearlyCPI> {
        val yearlyCpis = mutableListOf<YearlyCPI>()
        val resource = ClassPathResource(filePath)
        val inputStream = resource.inputStream
        val reader = BufferedReader(InputStreamReader(inputStream))
        reader.readLine()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            val values = line!!.split(",").map { it.trim() }
            if (values.size >= 13) {
                val yearlyCpi = YearlyCPI(
                    year = values[0].toInt(),
                    annualAverage = BigDecimal(values[13].ifEmpty { "0" }),
                    jan = BigDecimal(values[1].ifEmpty { "0" }),
                    feb = BigDecimal(values[2].ifEmpty { "0" }),
                    mar = BigDecimal(values[3].ifEmpty { "0" }),
                    apr = BigDecimal(values[4].ifEmpty { "0" }),
                    may = BigDecimal(values[5].ifEmpty { "0" }),
                    jun = BigDecimal(values[6].ifEmpty { "0" }),
                    jul = BigDecimal(values[7].ifEmpty { "0" }),
                    aug = BigDecimal(values[8].ifEmpty { "0" }),
                    sep = BigDecimal(values[9].ifEmpty { "0" }),
                    oct = BigDecimal(values[10].ifEmpty { "0" }),
                    nov = BigDecimal(values[11].ifEmpty { "0" }),
                    dec = BigDecimal(values[12].ifEmpty { "0" })
                )
                yearlyCpis.add(yearlyCpi)
            }
        }
        // Close resources
        reader.close()
        inputStream.close()
        return yearlyCpis
    }
}