package de.nicetoapp.smartfintools.repository.remote

import de.nicetoapp.smartfintools.repository.remote.dto.ResponseDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.reactive.asFlow
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class GenesisService(private val webClient: WebClient) {

    fun getYearlyCPIs(username: String, password: String): Flow<ResponseDTO> {
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
            .bodyToFlux(ResponseDTO::class.java)
            .asFlow()
    }
}
