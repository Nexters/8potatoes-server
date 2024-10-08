package com.eightpotatoes.nexters.client.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class AppConfig(
    @Value("\${api.base.url}") private val baseUrl: String,
) {

    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
                .codecs { configurer ->
                    configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024)  // 16MB
                }
                .baseUrl(baseUrl)
                .build()
    }
}
