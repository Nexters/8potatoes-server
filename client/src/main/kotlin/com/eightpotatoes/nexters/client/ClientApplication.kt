package com.eightpotatoes.nexters.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.eightpotatoes.nexters"])
class ClientApplication

fun main(args: Array<String>) {
	runApplication<ClientApplication>(*args)
}
