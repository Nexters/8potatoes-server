package com.eightpotatoes.nexters.api.external.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("휴식맛쥬 API")
                    .description("휴게소 정보를 제공하는 API")
                    .version("1.0.0")
            )
    }
}