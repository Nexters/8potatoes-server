package com.eightpotatoes.nexters.api.external

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.eightpotatoes.nexters"])
class ExternalAPIApplication

fun main(args: Array<String>) {
	runApplication<ExternalAPIApplication>(*args)
}
