package de.nicetoapp.smartfintools.config

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Configuration
class WebClientConfig {
    companion object {
        const val GENESIS_BASE_URL = "https://www-genesis.destatis.de/genesisWS/rest/2020/"
    }

    @Bean
    fun genesisWebClient(): WebClient {
        return WebClient.builder()
            .baseUrl(GENESIS_BASE_URL)
            .filter(logRequest())
            .filter(logResponse())
            .build()
    }

    private fun logRequest(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofRequestProcessor { clientRequest ->
            LoggerFactory.getLogger(WebClientConfig::class.java)
                .info("Request: {} {}", clientRequest.method(), clientRequest.url())
            clientRequest.headers().forEach { name, values ->
                values.forEach { value ->
                    LoggerFactory.getLogger(WebClientConfig::class.java).info("{}: {}", name, value)
                }
            }
            Mono.just(clientRequest)
        }
    }

    private fun logResponse(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofResponseProcessor { clientResponse ->
            LoggerFactory.getLogger(WebClientConfig::class.java).info("Response: {}", clientResponse.statusCode())
            clientResponse.headers().asHttpHeaders().forEach { name, values ->
                values.forEach { value ->
                    LoggerFactory.getLogger(WebClientConfig::class.java).info("{}: {}", name, value)
                }
            }

            // Copying the body for logging purposes
            clientResponse.bodyToMono(String::class.java).doOnNext { body ->
                LoggerFactory.getLogger(WebClientConfig::class.java).info("Response body: {}", body)
            }.then(Mono.just(clientResponse))
        }
    }

}
