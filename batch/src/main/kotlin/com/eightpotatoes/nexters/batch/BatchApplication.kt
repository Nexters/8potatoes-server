package com.eightpotatoes.nexters.batch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.eightpotatoes.nexters"])
class BatchApplication

fun main(args: Array<String>) {
	runApplication<BatchApplication>(*args)
}
