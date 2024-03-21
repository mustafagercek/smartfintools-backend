package de.nicetoapp.smartfintools.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class DataService(private val webClient: WebClient) {
    private val logger: Logger = LoggerFactory.getLogger(DataService::class.java)

    fun getDataFlow(username: String, password: String): Flow<Map<String, Any>> {
        return webClient.get()
            .uri { uriBuilder -> 
                uriBuilder.path("data/table")
                    .queryParam("username", "DE7GH316OK")
                    .queryParam("password", "gyjhac-Kurkyb-gecje3")
                    .queryParam("name", "61111-0001")
                    .queryParam("area", "all")
                    .queryParam("language", "de")
                    .queryParam("startyear", "1900")
                    .queryParam("endyear", "")
                    .build()
            }
            .retrieve()
            .bodyToFlux(object : ParameterizedTypeReference<Map<String, Any>>() {})
            .asFlow()
    }
}
