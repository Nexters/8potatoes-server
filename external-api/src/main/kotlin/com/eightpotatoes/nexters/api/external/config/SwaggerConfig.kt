package com.eightpotatoes.nexters.api.external.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.ForwardedHeaderFilter


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
            .servers(
                listOf(
                    io.swagger.v3.oas.models.servers.Server().url("http://localhost:8082"),
                    io.swagger.v3.oas.models.servers.Server().url("http://175.45.205.16:8082"),
                    io.swagger.v3.oas.models.servers.Server().url("https://server-hyusik-matju.site")
                )
            )
    }

    @Bean
    fun forwardedHeaderFilter(): ForwardedHeaderFilter {
        return ForwardedHeaderFilter()
    }
}
