package com.eightpotatoes.nexters.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
@EntityScan("com.eightpotatoes.nexters")
@EnableJpaRepositories(basePackages = ["com.eightpotatoes.nexters"])
class JpaAuditingConfiguration {
}
