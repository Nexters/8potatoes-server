package com.eightpotatoes.nexters.scraping

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.eightpotatoes.nexters"])
class ScrapingApplication

fun main(args: Array<String>) {
	runApplication<ScrapingApplication>(*args)
}
