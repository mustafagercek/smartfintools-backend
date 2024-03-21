package de.nicetoapp.smartfintools

import de.nicetoapp.smartfintools.repository.DataService
import de.nicetoapp.smartfintools.repository.remote.GenesisService
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.reactive.function.client.WebClient

@SpringBootApplication
@EnableScheduling
class SmartFinToolsApplication{
	@Bean
	fun genesisService(@Qualifier("genesisWebClient") webClient: WebClient) = GenesisService(webClient)
}

fun main(args: Array<String>) {
	runBlocking {
		val context = runApplication<SmartFinToolsApplication>(*args)
		val genesisService = context.getBean(GenesisService::class.java)

		// Replace these with actual username and password
		val username = "yourUsername"
		val password = "yourPassword"

		genesisService.getYearlyCPIs(username, password).collect { responseDTO ->
			println(responseDTO)
		}
	}
}
