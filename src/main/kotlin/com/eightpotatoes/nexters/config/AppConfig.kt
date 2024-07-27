package com.eightpotatoes.nexters.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class AppConfig {

    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
                .baseUrl("http://data.ex.co.kr")
                .build();
    }
}
